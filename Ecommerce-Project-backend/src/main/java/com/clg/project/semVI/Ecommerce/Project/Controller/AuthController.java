package com.clg.project.semVI.Ecommerce.Project.Controller;


import com.clg.project.semVI.Ecommerce.Project.Model.Cart;
import com.clg.project.semVI.Ecommerce.Project.Model.User;
import com.clg.project.semVI.Ecommerce.Project.Repository.UserRepository;
import com.clg.project.semVI.Ecommerce.Project.Request.LoginRequest;
import com.clg.project.semVI.Ecommerce.Project.Response.AuthResponse;
import com.clg.project.semVI.Ecommerce.Project.Service.CustomUserServiceImplementation;
import com.clg.project.semVI.Ecommerce.Project.Service.JwtProvider;
import com.clg.project.semVI.Ecommerce.Project.Exception.UserException;
import com.clg.project.semVI.Ecommerce.Project.ServiceInterfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider  jwtProvider;
     @Autowired
    private PasswordEncoder passwordEncoder;

     @Autowired
     private CartService cartService; 

    @Autowired
    private  CustomUserServiceImplementation customUserServiceImplementation;
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user)throws UserException{
        String email = user.getEmail();
        String password = user.getPassword();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String role = user.getRole();
        //  Find By Email and Role --> change krna hai
        User isEmailExist =  userRepository.findByEmail(email);

        if(isEmailExist != null){
            throw new UserException("Email is Already Used with Another Account");
        }
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstName);
        createdUser.setLastName(lastName);
        createdUser.setRole(role);
        User savedUser = userRepository.save(createdUser);
        Cart cart = cartService.createCart(savedUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
          String token  = jwtProvider.generateToken(authentication); 

            AuthResponse authResponse = new AuthResponse();
            authResponse.setJwt(token);
            authResponse.setMessage("signup Success...");

            return new ResponseEntity<>(authResponse, HttpStatus.CREATED); 
    }

    @PostMapping("/signin")
    public  ResponseEntity<AuthResponse>loginUserHanler(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token  = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signin Success...");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
        
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(username);

        if(userDetails == null){
            throw  new BadCredentialsException("Invalid Username...");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
           throw new  BadCredentialsException("Invalid Password..."); 
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
    }
}

