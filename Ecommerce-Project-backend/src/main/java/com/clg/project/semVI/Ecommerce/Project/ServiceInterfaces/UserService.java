package com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces;

import com.clg.project.semVI.Ecommerce.Project.Model.User;

import com.clg.project.semVI.Ecommerce.Project.Exception.UserException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public  User findUserProfileByJwt(String jwt) throws  UserException;
    
}
