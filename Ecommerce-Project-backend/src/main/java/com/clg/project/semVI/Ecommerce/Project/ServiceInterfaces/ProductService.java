package com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces;

import com.clg.project.semVI.Ecommerce.Project.Request.CreateProductRequest;
import com.clg.project.semVI.Ecommerce.Project.Model.Product;
import com.clg.project.semVI.Ecommerce.Project.Exception.ProductExceptions;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest req);
    public  String deleteProduct(Long productId) throws ProductExceptions;

    public  Product updateProduct(Long productId, Product req) throws ProductExceptions;

    public  Product findProductById(long id) throws  ProductExceptions;

    public List<Product> findProductByCategory(String category);

    public Page<Product> getAllProduct(String category, List<String>colors,List<String>sizes, Integer minPrice,Integer maxPrice,Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

    public List<Product> findAllProduct(); 
}
