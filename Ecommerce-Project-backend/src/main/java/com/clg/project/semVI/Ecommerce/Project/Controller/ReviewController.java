package com.clg.project.semVI.Ecommerce.Project.Controller;

import com.clg.project.semVI.Ecommerce.Project.Exception.ProductExceptions;
import com.clg.project.semVI.Ecommerce.Project.Exception.UserException;
import com.clg.project.semVI.Ecommerce.Project.Model.Review;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.Request.ReviewRequest;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.ReviewService;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Review>createReview(@RequestBody ReviewRequest req,  @RequestHeader("Authorization")String jwt)throws UserException, ProductExceptions{
        User user = userService.findUserProfileByJwt(jwt);
        Review review = reviewService.createReview(req, user);

        return  new ResponseEntity<>(review, HttpStatus.CREATED); 
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>>getProductsReview(@PathVariable Long productId )throws UserException, ProductExceptions{
        List<Review> reviews = reviewService.getAllReviews(productId);

        return  new ResponseEntity<>(reviews, HttpStatus.CREATED);
    }
    
}
