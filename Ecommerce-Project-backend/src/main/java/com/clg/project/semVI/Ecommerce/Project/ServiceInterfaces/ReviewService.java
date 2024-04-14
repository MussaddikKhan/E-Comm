package com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces;

import com.clg.project.semVI.Ecommerce.Project.Exception.ProductExceptions;
import com.clg.project.semVI.Ecommerce.Project.Model.Review;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.Request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest req, User user)throws ProductExceptions;
    public List<Review>getAllReviews(Long productId);

}
