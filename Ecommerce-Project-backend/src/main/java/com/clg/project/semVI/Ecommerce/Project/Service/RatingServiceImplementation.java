package com.clg.project.semVI.Ecommerce.Project.Service;

import com.clg.project.semVI.Ecommerce.Project.Exception.ProductExceptions;
import com.clg.project.semVI.Ecommerce.Project.Model.Product;
import com.clg.project.semVI.Ecommerce.Project.Model.Rating;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.Repository.RatingRepository;
import com.clg.project.semVI.Ecommerce.Project.Request.RatingRequest;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.ProductService;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImplementation implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ProductService productService;
    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductExceptions {
        Product product = productService.findProductById(req.getProductId());
        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}
