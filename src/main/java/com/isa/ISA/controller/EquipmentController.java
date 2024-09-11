package com.isa.ISA.controller;

import com.isa.ISA.exception.ResourceNotFoundException;
import com.isa.ISA.model.Company;
import com.isa.ISA.model.Equipment;
import com.isa.ISA.services.CompanyService;
import com.isa.ISA.services.EquipmentService;
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
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private CompanyService companyService;

    // get all companies
    @GetMapping("/equipments")
    public List<Equipment> getAllEquipments(){

        return equipmentService.findAll();
    }

    // create equipment rest api
    @PostMapping("/companies/{companyId}/equipments")
    public ResponseEntity<Equipment> createEquipment(@PathVariable(value="companyId") Long companyId, @RequestBody Equipment equipment) {
        Equipment eq = new Equipment(equipment.getEquipmentName(), equipment.getQuantity());
        Company c =  companyService.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Not found company with company id " + companyId));
        eq.setCompany(c);
        equipmentService.save(eq);
        return new ResponseEntity<>(eq, HttpStatus.CREATED);
    }

    // get equipment by id rest api
    @GetMapping("/equipments/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        Equipment equipment = equipmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not exist with id :" + id));
        return ResponseEntity.ok(equipment);
    }
    @GetMapping("/companies/{id}/equipments")
    public ResponseEntity<List<Equipment>> getAllEquipmentByCompanyId(@PathVariable Long id) {
        if(!companyService.existById(id))
            throw new ResourceNotFoundException("Company not exist with id :" + id);

        List<Equipment> equipment = equipmentService.findByCompanyId(id);
        return ResponseEntity.ok(equipment);
    }

    // update equipment rest api

    @PutMapping("/companies/{companyId}/equipments/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Long companyId, @PathVariable Long id, @RequestBody Equipment equipmentDetails){
        System.out.println("Updateuje eqq");
        Equipment equipment = equipmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not exist with id :" + id));
        Company c =  companyService.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Not found company with company id " + companyId));
        equipment.setEquipmentName(equipmentDetails.getEquipmentName());
        equipment.setQuantity(equipmentDetails.getQuantity());
        equipment.setCompany(c);

        Equipment updatedEquipment = equipmentService.save(equipment);
        return ResponseEntity.ok(updatedEquipment);
    }

    // delete equipment rest api
    @DeleteMapping("/equipments/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEquipment(@PathVariable Long id){
        Equipment equipment = equipmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not exist with id :" + id));

        equipmentService.delete(equipment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
