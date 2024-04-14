package com.clg.project.semVI.Ecommerce.Project.Repository;

import com.clg.project.semVI.Ecommerce.Project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    
}
