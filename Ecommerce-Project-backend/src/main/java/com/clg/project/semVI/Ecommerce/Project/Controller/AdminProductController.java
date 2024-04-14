package com.clg.project.semVI.Ecommerce.Project.Controller;

import com.clg.project.semVI.Ecommerce.Project.Exception.ProductExceptions;
import com.clg.project.semVI.Ecommerce.Project.Model.Product;
import com.clg.project.semVI.Ecommerce.Project.Request.CreateProductRequest;
import com.clg.project.semVI.Ecommerce.Project.Response.ApiResponse;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req){
        Product product = productService.createProduct(req);
        return  new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @DeleteMapping("/{productId}/delete")
    public  ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductExceptions{
        productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse();
        res.setMessage("product deleted successfully");
        res.setStatus(true);
        return  new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct(){
           List<Product>products = productService.findAllProduct();
           return new ResponseEntity<>(products,HttpStatus.OK); 
    }

    @PutMapping("/{productId}/update")
    public ResponseEntity<Product>updateProduct(@RequestBody Product req, @PathVariable Long productId) throws ProductExceptions{
        Product product = productService.updateProduct(productId, req);
        return  new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PostMapping("/creates")
    public  ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] req){
        for(CreateProductRequest productRequest : req){
            productService.createProduct(productRequest); 
        }
        ApiResponse res = new ApiResponse();
        res.setMessage("product created successfully");
        res.setStatus(true);

        return  new ResponseEntity<>(res, HttpStatus.CREATED); 
    }

}
