package com.isa.ISA.controller;

import com.isa.ISA.exception.ResourceNotFoundException;
import com.isa.ISA.model.Company;
import com.isa.ISA.model.Equipment;
import com.isa.ISA.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    // get all companies
    @GetMapping("/companies")
    public List<Company> getAllCompanies(){
        return companyService.findAll();
    }

    // create company rest api
    @PostMapping("/companies")
    public Company createCompany(@RequestBody Company company) {
        return companyService.save(company);
    }

    // get company by id rest api
    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not exist with id :" + id));
        return ResponseEntity.ok(company);
    }

    // update company rest api

    @PutMapping("/companies/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company companyDetails){
        Company company = companyService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not exist with id :" + id));

        company.setCompanyName(companyDetails.getCompanyName());
        company.setAddress(companyDetails.getAddress());
        //company.setEquipment(companyDetails.getEquipment());

        Company updatedCompany = companyService.save(company);
        return ResponseEntity.ok(updatedCompany);
    }

    // delete company rest api
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCompany(@PathVariable Long id){
        Company company = companyService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not exist with id :" + id));

        companyService.delete(company);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
