package com.isa.ISA.controller;

import com.isa.ISA.exception.ResourceNotFoundException;
import com.isa.ISA.model.Appointment;
import com.isa.ISA.model.Company;
import com.isa.ISA.model.Equipment;
import com.isa.ISA.model.User;
import com.isa.ISA.services.AppointmentService;
import com.isa.ISA.services.CompanyService;
import com.isa.ISA.services.EquipmentService;
import com.isa.ISA.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @Autowired
    private UserService userService;


    // get all appointments
    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments(){

        return appointmentService.findAll();
    }
    @GetMapping("/appointments/reserved")
    public List<Appointment> getAllNoReservedAppointments(){
        List<Appointment> ret = appointmentService.findByIsReserved();
        List<Appointment> retFinal = new ArrayList<Appointment>();
        for(Appointment app : ret){
            if(!app.isDone())
                retFinal.add(app);
        }
        return retFinal;
    }

    // create appointment rest api
    @PostMapping("/companies/{companyId}/appointments")
    public ResponseEntity<Appointment> createAppointment(@PathVariable(value="companyId") Long companyId, @RequestBody Appointment appointment) {
        System.out.println(appointment.getStart());
        System.out.println(appointment.getEnd());
        Appointment ap = new Appointment(appointment.getStart(), appointment.getEnd());
        Company c =  companyService.findById(companyId).orElseThrow(() -> new ResourceNotFoundException("Not found company with company id " + companyId));
        ap.setCompany(c);
        ap.setUserId(-1);
        ap.setCId(c.getId());
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

    @GetMapping("/companies/{id}/appointmentsnotreserved")
    public ResponseEntity<List<Appointment>> getAllAppointmentsByCompanyIdNotReserved(@PathVariable Long id) {
        if(!companyService.existById(id))
            throw new ResourceNotFoundException("Company not exist with id :" + id);

        List<Appointment> appointments = appointmentService.findByCompanyId(id);
        List<Appointment> appointmentsNotReserved = new ArrayList<Appointment>();
        for(Appointment a : appointments){
            System.out.println(a);
            if(!a.isReserved())
                appointmentsNotReserved.add(a);
        }
        return ResponseEntity.ok(appointmentsNotReserved);
    }

    @GetMapping("/users/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointmentsByUserId(@PathVariable Long id) {
        if(!userService.existById(id))
            throw new ResourceNotFoundException("User not exist with id :" + id);

        List<Appointment> appointments = appointmentService.findByUserId(id);
        List<Appointment> retFinal = new ArrayList<Appointment>();
        for(Appointment app : appointments){
            if(!app.isDone())
                retFinal.add(app);
        }
        return ResponseEntity.ok(retFinal);
    }

    @GetMapping("/users/{id}/appointments/done")
    public ResponseEntity<List<Appointment>> getAllDoneAppointmentsByUserId(@PathVariable Long id) {
        if(!userService.existById(id))
            throw new ResourceNotFoundException("User not exist with id :" + id);

        List<Appointment> appointments = appointmentService.findByUserId(id);
        List<Appointment> retFinal = new ArrayList<Appointment>();
        for(Appointment app : appointments){
            if(app.isDone())
                retFinal.add(app);
        }
        return ResponseEntity.ok(retFinal);
    }
    @GetMapping("/users/{id}/appointments/done/{companyId}")
    public ResponseEntity<List<Appointment>> getAllDoneAppointmentsByUserIdAndCompanyId(@PathVariable Long id, @PathVariable Long companyId) {
        if(!userService.existById(id))
            throw new ResourceNotFoundException("User not exist with id :" + id);
        if(!companyService.existById(companyId))
            throw new ResourceNotFoundException("Company not exist with id :" + id);
        List<Appointment> appointments = appointmentService.findByUserId(id);
        List<Appointment> retFinal = new ArrayList<Appointment>();
        List<Appointment> retFinal2 = new ArrayList<Appointment>();
        for(Appointment app : appointments){
            if(app.isDone())
                retFinal.add(app);
        }
        for(Appointment app : retFinal){
            if(app.getCompany().getId() == companyId)
                retFinal2.add(app);
        }
        return ResponseEntity.ok(retFinal2);
    }
    // update appointment rest api

    @PutMapping("/appointments/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails){
        System.out.println("Updateuje app");
        Appointment appointment = appointmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with id :" + id));
        User u = userService.findById(appointmentDetails.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + appointmentDetails.getUserId()));
        appointment.setStart(appointmentDetails.getStart());
        appointment.setEnd(appointmentDetails.getEnd());
        appointment.setReserved(appointmentDetails.isReserved());
        appointment.setDone(appointmentDetails.isDone());
        //appointment.setCompany(appointmentDetails.getCompany());
        appointment.setUserId(u.getId());

        Appointment updatedAppointment = appointmentService.save(appointment);
        return ResponseEntity.ok(updatedAppointment);
    }
    @PutMapping("/appointments/cancel/{id}")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long id, @RequestBody Appointment appointmentDetails){
        System.out.println("Updateuje app");
        Appointment appointment = appointmentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not exist with id :" + id));
        appointment.setStart(appointmentDetails.getStart());
        appointment.setEnd(appointmentDetails.getEnd());
        appointment.setReserved(appointmentDetails.isReserved());
        //appointment.setCompany(appointmentDetails.getCompany());
        appointment.setUserId(appointmentDetails.getUserId());

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
