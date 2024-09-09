package com.isa.ISA.controller;

import com.isa.ISA.exception.ResourceNotFoundException;
import com.isa.ISA.model.Appointment;
import com.isa.ISA.model.Company;
import com.isa.ISA.model.Equipment;
import com.isa.ISA.model.ReservedEquipment;
import com.isa.ISA.services.AppointmentService;
import com.isa.ISA.services.CompanyService;
import com.isa.ISA.services.EquipmentService;
import com.isa.ISA.services.ReservedEquipmentService;
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
public class ReservedEquipmentController {

    @Autowired
    private ReservedEquipmentService reservedEquipmentService;

    @Autowired
    private AppointmentService appointmentService;

    // get all reservedEquipment
    @GetMapping("/reservedequipments")
    public List<ReservedEquipment> getAllEquipments(){

        return reservedEquipmentService.findAll();
    }

    // create equipment rest api
    @PostMapping("/appointments/{appointmentId}/reservedequipments")
    public ResponseEntity<ReservedEquipment> createReservedEquipment(@PathVariable(value="appointmentId") Long appointmentId, @RequestBody ReservedEquipment reservedEquipment) {
        ReservedEquipment req = new ReservedEquipment(reservedEquipment.getId(), reservedEquipment.getEquipmentName(), reservedEquipment.getQuantity());
        Appointment a =  appointmentService.findById(appointmentId).orElseThrow(() -> new ResourceNotFoundException("Not found appointment with appointment id " + appointmentId));
        req.setAppointment(a);
        reservedEquipmentService.save(req);
        return new ResponseEntity<>(req, HttpStatus.CREATED);
    }

    // get equipment by id rest api
    /*@GetMapping("/equipments/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        Equipment equipment = equipmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not exist with id :" + id));
        return ResponseEntity.ok(equipment);
    }*/
    @GetMapping("/appointments/{id}/reservedequipments")
    public ResponseEntity<List<ReservedEquipment>> getAllReservedEquipmentByAppointmentId(@PathVariable Long id) {
        if(!appointmentService.existById(id))
            throw new ResourceNotFoundException("Appointment not exist with id :" + id);

        List<ReservedEquipment> reservedEquipment = reservedEquipmentService.findByAppointmentId(id);
        return ResponseEntity.ok(reservedEquipment);
    }

    // update equipment rest api

    @PutMapping("/reservedequipments/{id}")
    public ResponseEntity<ReservedEquipment> updateReservedEquipment(@PathVariable Long id, @RequestBody ReservedEquipment reservedEquipmentDetails){
        ReservedEquipment reservedEquipment = reservedEquipmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserved Equipment not exist with id :" + id));

        reservedEquipment.setEquipmentName(reservedEquipmentDetails.getEquipmentName());
        reservedEquipment.setQuantity(reservedEquipmentDetails.getQuantity());
        reservedEquipment.setAppointment(reservedEquipmentDetails.getAppointment());

        ReservedEquipment updatedReservedEquipment = reservedEquipmentService.save(reservedEquipment);
        return ResponseEntity.ok(reservedEquipment);
    }

    // delete equipment rest api
    @DeleteMapping("/reservedequipments/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteReservedEquipment(@PathVariable Long id){
        ReservedEquipment reservedEquipment = reservedEquipmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserved Equipment not exist with id :" + id));

        reservedEquipmentService.delete(reservedEquipment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
