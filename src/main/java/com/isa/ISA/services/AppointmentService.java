package com.isa.ISA.services;

import com.isa.ISA.model.Appointment;
import com.isa.ISA.model.Equipment;
import com.isa.ISA.repository.AppointmentRepository;
import com.isa.ISA.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }
    public Appointment save(Appointment appointment){
        return appointmentRepository.save(appointment);
    }
    public Optional<Appointment> findById(long id){
        return appointmentRepository.findById(id);
    }
    public void delete(Appointment appointment){
        appointmentRepository.delete(appointment);
    }
    public List<Appointment> findByCompanyId(Long companyId) { return appointmentRepository.findByCompanyId(companyId);}
    //public List<Appointment> findByUserId(Long userId) { return appointmentRepository.findByUserId(userId);}
}
