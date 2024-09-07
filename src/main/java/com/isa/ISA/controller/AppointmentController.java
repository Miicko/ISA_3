package com.isa.ISA.controller;

import com.isa.ISA.exception.ResourceNotFoundException;
import com.isa.ISA.model.Appointment;
import com.isa.ISA.model.Company;
import com.isa.ISA.model.Equipment;
import com.isa.ISA.services.AppointmentService;
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
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private CompanyService companyService;

    // get all appointments
    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments(){

        return appointmentService.findAll();
    }

    // create appointment rest api
    @PostMapping("/companies/{companyId}/appointments")
    public ResponseEntity<Appointment> createAppointment(@PathVariable(value="companyId") Long companyId, @RequestBody Appointment appointment) {
        System.out.println(appointment.getStart());
        System.out.println(appointment.getEnd());
        Appointment ap = new Appointment(appointment.getStart(), appointment.getEnd());
        Company c =  companyService.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Not found company with company id " + companyId));
        ap.setCompany(c);
        appointmentService.save(ap);
        return new ResponseEntity<>(ap, HttpStatus.CREATED);
    }

    // get appointment by id rest api
    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with id :" + id));
        return ResponseEntity.ok(appointment);
    }
    @GetMapping("/companies/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointmentsByCompanyId(@PathVariable Long id) {
        if(!companyService.existById(id))
            throw new ResourceNotFoundException("Company not exist with id :" + id);

        List<Appointment> appointments = appointmentService.findByCompanyId(id);
        return ResponseEntity.ok(appointments);
    }

    // update appointment rest api

    @PutMapping("/appointments/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails){
        Appointment appointment = appointmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with id :" + id));

        appointment.setStart(appointmentDetails.getStart());
        appointment.setEnd(appointmentDetails.getEnd());
        appointment.setReserved(appointmentDetails.isReserved());
        appointment.setCompany(appointmentDetails.getCompany());
        //appointment.setUser(appointmentDetails.getUser());

        Appointment updatedAppointment = appointmentService.save(appointment);
        return ResponseEntity.ok(updatedAppointment);
    }

    // delete appointment rest api
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteAppointment(@PathVariable Long id){
        Appointment appointment = appointmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with id :" + id));

        appointmentService.delete(appointment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
