package com.clg.project.semVI.Ecommerce.Project.Service;

import com.clg.project.semVI.Ecommerce.Project.Exception.ProductExceptions;
import com.clg.project.semVI.Ecommerce.Project.Model.Product;
import com.clg.project.semVI.Ecommerce.Project.Model.Review;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.Repository.ProductRepository;
import com.clg.project.semVI.Ecommerce.Project.Repository.ReviewRepository;
import com.clg.project.semVI.Ecommerce.Project.Request.ReviewRequest;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.ProductService;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductExceptions {

        Product product = productService.findProductById(req.getProductId());
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(req.getReview());
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}
