package com.isa.ISA.services;

import com.isa.ISA.exception.ResourceAlreadyExists;
import com.isa.ISA.model.Company;
import com.isa.ISA.model.User;
import com.isa.ISA.model.dtos.SignUpDto;
import com.isa.ISA.repository.CompanyRepository;
import com.isa.ISA.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public List<Company> findAll(){
        return companyRepository.findAll();
    }
    public Company save(Company company){
        return companyRepository.save(company);
    }
    public Optional<Company> findById(long id){
        return companyRepository.findById(id);
    }
    public void delete(Company company){
        companyRepository.delete(company);
    }
    public boolean existById(Long id) { return companyRepository.existsById(id); }
}
