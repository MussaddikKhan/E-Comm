package com.clg.project.semVI.Ecommerce.Project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    @Column(name = "first_name")
    private  String firstName;
    @Column(name = "last_name")
    private  String lastName;

    @Column(name = "street_address")
    private String streetAddress;
    
    private String city;
    private String state;
    @Column(name = "zip_code")
    private  String zipCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String mobile; 



}
