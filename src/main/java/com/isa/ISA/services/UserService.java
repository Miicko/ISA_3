package com.isa.ISA.services;

import com.isa.ISA.exception.ResourceAlreadyExists;
import com.isa.ISA.model.User;
import com.isa.ISA.model.dtos.*;
import com.isa.ISA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDetails ud =  userRepository.findByLogin(email);
        return ud;
    }

    public UserDetails signUp(SignUpDto data) throws ResourceAlreadyExists {
        if (userRepository.findByLogin(data.email()) != null) {
            throw new ResourceAlreadyExists("Email already exists!");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.city(), data.companyInfo(), data.country(), data.email(), data.firstName(), data.lastName(), data.occupation(), encryptedPassword, data.phoneNumber(), data.role());
        return userRepository.save(newUser);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User save(User user){
        return userRepository.save(user);
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Optional<User> findById(long id){
        return userRepository.findById(id);
    }
    public void delete(User user){
        userRepository.delete(user);
    }
}
