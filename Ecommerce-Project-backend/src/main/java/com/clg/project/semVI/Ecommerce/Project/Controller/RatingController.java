package com.clg.project.semVI.Ecommerce.Project.Controller;

import com.clg.project.semVI.Ecommerce.Project.Exception.ProductExceptions;
import com.clg.project.semVI.Ecommerce.Project.Exception.UserException;
import com.clg.project.semVI.Ecommerce.Project.Model.Rating;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.Request.RatingRequest;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.RatingService;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating>createRating(@RequestBody RatingRequest req, @RequestHeader("Authorization")String jwt)throws  UserException, ProductExceptions{
        User user = userService.findUserProfileByJwt(jwt);
        Rating rating = ratingService.createRating(req, user);

        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED); 
    }

    @GetMapping("/product/{productId}")
    public  ResponseEntity<List<Rating>> getProductRating(@PathVariable Long productId, @RequestHeader("Authorization")String jwt) throws  UserException, ProductExceptions{
                User user = userService.findUserProfileByJwt(jwt);
                List<Rating> ratings = ratingService.getProductsRating(productId);

                return new ResponseEntity<>(ratings, HttpStatus.CREATED);
    }
    
}
