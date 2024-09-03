package com.isa.ISA.controller;

import com.isa.ISA.config.TokenProvider;
import com.isa.ISA.exception.ResourceAlreadyExists;
import com.isa.ISA.model.User;
import com.isa.ISA.model.dtos.JwtDto;
import com.isa.ISA.model.dtos.SignInDto;
import com.isa.ISA.model.dtos.SignUpDto;
import com.isa.ISA.repository.UserRepository;
import com.isa.ISA.exception.ResourceNotFoundException;
import com.isa.ISA.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    TokenProvider tokenService;
    @Autowired
    private UserService userService;

    // get all employees
    @GetMapping("/userspace/users")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @PostMapping("/authentication/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto data) {
        UserDetails ud = userService.signUp(data);
        return ResponseEntity.ok(ud);
    }

    @PostMapping("/authentication/login")
    public ResponseEntity<JwtDto> signIn(@RequestBody SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var accessToken = tokenService.generateAccessToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JwtDto(accessToken));
    }
    // create employee rest api
    @PostMapping("/userspace/users")
    public User createUser(@RequestBody User user) {
        User u = userService.findByEmail(user.getEmail());
        if(u != null)
            throw new ResourceAlreadyExists("User with email: " + u.getEmail() + " is already registered!");
        return userService.save(user);
    }

    // get employee by id rest api
    @GetMapping("/userspace/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        return ResponseEntity.ok(user);
    }
    //aa
    /*@GetMapping("/users/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user == null){
            throw new ResourceNotFoundException("User not exist with email :" + email);
        }
        return ResponseEntity.ok(user);
    }*/

    // update employee rest api

    @PutMapping("/userspace/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setCity(userDetails.getCity());
        user.setCountry(userDetails.getCountry());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setOccupation(userDetails.getOccupation());
        user.setCompanyInfo(userDetails.getCompanyInfo());

        User updatedUser = userService.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // delete employee rest api
    @DeleteMapping("/userspace/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

        userService.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
