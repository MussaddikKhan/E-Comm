package com.clg.project.semVI.Ecommerce.Project.Service;

import com.clg.project.semVI.Ecommerce.Project.Model.Category;
import com.clg.project.semVI.Ecommerce.Project.Model.Product;
import com.clg.project.semVI.Ecommerce.Project.Repository.CategoryRepository;
import com.clg.project.semVI.Ecommerce.Project.Repository.ProductRepository;
import com.clg.project.semVI.Ecommerce.Project.Request.CreateProductRequest;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.ProductService;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.UserService;
import com.clg.project.semVI.Ecommerce.Project.Exception.ProductExceptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements ProductService {
     

    private  ProductRepository productRepository;

    private  CategoryRepository categoryRepository;

    private UserService userService;

    public  ProductServiceImplementation(ProductRepository productRepository,CategoryRepository categoryRepository, UserService userService ){
        this. productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    } 
    @Override
    public Product createProduct(CreateProductRequest req) {
        Category topLevel = categoryRepository.findByName(req.getTopLevelCategory());
        if(topLevel == null){
            Category topLevelCategory = new Category( );
            topLevelCategory.setName(req.getTopLevelCategory());
            topLevelCategory.setLevel(1);
            topLevel = categoryRepository.save(topLevelCategory);
        }
        Category secondLevel = categoryRepository.findByNameAndParent(req.getSecondLevelCategory(),topLevel.getName());
        if(secondLevel == null){
            Category secondLevelCategory = new Category();
            secondLevelCategory.setName(req.getSecondLevelCategory());
            secondLevelCategory.setParentCategory(topLevel);
            secondLevelCategory.setLevel(2);
            secondLevel = categoryRepository.save(secondLevelCategory);
        }
        Category thirdLevel = categoryRepository.findByNameAndParent(req.getThirdLevelCategory(),secondLevel.getName());
        if(thirdLevel == null){
            Category thirdLevelCategory = new Category();
            thirdLevelCategory.setName(req.getThirdLevelCategory());
            thirdLevelCategory.setParentCategory(secondLevel);
            thirdLevelCategory.setLevel(3);
            thirdLevel = categoryRepository.save(thirdLevelCategory);
        }
        Product product = new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDiscountedPercent(req.getDiscountedPercent());
        product.setImageUrl(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setSizes(req.getSizes());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());

        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public String deleteProduct(Long productId) throws ProductExceptions {
        Product product = findProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product deleted Successfully";
    }

    @Override
    public Product updateProduct(Long productId, Product req) throws ProductExceptions {
         Product product = findProductById(productId);
         if(req.getQuantity() != 0){
             product.setQuantity(req.getQuantity());
         }
        return productRepository.save(product);
    }

    @Override
    public Product findProductById(long id) throws ProductExceptions {
        Optional<Product> opt = productRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw  new ProductExceptions("Product not find with id" + id);
    }
    @Override
    public List<Product> findProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
         Pageable pageable  = PageRequest.of(pageNumber, pageSize);

         List<Product>products = productRepository.filterProduct(category, minPrice, maxPrice, minDiscount, sort);

         // Color is not empty
         if(!colors.isEmpty()){
             //Jo bhi Color Request me bheja gaya hai woh is color ke product se match krta hai kya ? to list bheji hogi then usme se kisi bhi match krta hai to woh sab project hame yaha pr chahiye    
            products=products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
         }
         if(stock != null){
             if(stock.equals("in_stock")) {
                 products = products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
             }
             else if(stock.equals("out_of_stock")) {
                    products = products.stream().filter(p->p.getQuantity() < 1).collect(Collectors.toList());
             }
         }
         int startIndex = (int)pageable.getOffset();
         int endIndex = Math.min(startIndex+pageable.getPageSize(), products.size());

         List<Product>pageContent = products.subList(startIndex, endIndex);
         Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());

         return filteredProducts;
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }
}
