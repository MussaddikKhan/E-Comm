package com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces;

import com.clg.project.semVI.Ecommerce.Project.Exception.ProductExceptions;
import com.clg.project.semVI.Ecommerce.Project.Model.Rating;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.Request.RatingRequest;

import java.util.List;

public interface RatingService {
    public Rating createRating(RatingRequest req, User user) throws ProductExceptions;

    public List<Rating> getProductsRating(Long productId);

}
