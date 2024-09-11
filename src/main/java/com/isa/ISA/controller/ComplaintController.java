package com.isa.ISA.controller;

import com.isa.ISA.exception.ResourceNotFoundException;
import com.isa.ISA.model.Company;
import com.isa.ISA.model.Complaint;
import com.isa.ISA.model.Equipment;
import com.isa.ISA.model.User;
import com.isa.ISA.services.CompanyService;
import com.isa.ISA.services.ComplaintService;
import com.isa.ISA.services.EquipmentService;
import com.isa.ISA.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    // get all companies
    @GetMapping("/complaints")
    public List<Complaint> getAllComplaints(){

        return complaintService.findAll();
    }

    // create equipment rest api
    @PostMapping("/users/{userId}/complaints")
    public ResponseEntity<Complaint> createComplaint(@PathVariable(value="userId") Long userId, @RequestBody Complaint complaint) {
        Complaint co = new Complaint(complaint.getComplaintText(), complaint.getResponseText(), complaint.getCompanyId());
        Company c =  companyService.findById(complaint.getCompanyId()).orElseThrow(() -> new ResourceNotFoundException("Not found company with company id " + complaint.getCompanyId()));
        User u = userService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Not found user with user id " + userId));
        co.setUser(u);
        complaintService.save(co);
        return new ResponseEntity<>(co, HttpStatus.CREATED);
    }

    // get equipment by id rest api
    @GetMapping("/complaints/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable Long id) {
        Complaint complaint = complaintService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not exist with id :" + id));
        return ResponseEntity.ok(complaint);
    }
    @GetMapping("/users/{id}/complaints")
    public ResponseEntity<List<Complaint>> getAllComplaintsByUserId(@PathVariable Long id) {
        if(!userService.existById(id))
            throw new ResourceNotFoundException("User not exist with id :" + id);

        List<Complaint> complaints = complaintService.findByUserId(id);
        return ResponseEntity.ok(complaints);
    }

    // update equipment rest api

    @PutMapping("/users/{userId}/equipments/{id}")
    public ResponseEntity<Complaint> updateComplaint(@PathVariable Long userId, @PathVariable Long id, @RequestBody Complaint complaintDetails){
        Complaint complaint = complaintService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not exist with id :" + id));
        User u =  userService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Not found user with user id " + userId));
        complaint.setComplaintText(complaintDetails.getComplaintText());
        complaint.setResponseText(complaintDetails.getResponseText());

        Complaint updatedComplaint = complaintService.save(complaint);
        return ResponseEntity.ok(updatedComplaint);
    }

    // delete equipment rest api
    @DeleteMapping("/complaints/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteComplaint(@PathVariable Long id){
        Complaint complaint = complaintService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint not exist with id :" + id));

        complaintService.delete(complaint);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
