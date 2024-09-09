package com.isa.ISA.services;

import com.isa.ISA.model.Equipment;
import com.isa.ISA.model.ReservedEquipment;
import com.isa.ISA.repository.EquipmentRepository;
import com.isa.ISA.repository.ReservedEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservedEquipmentService {

    @Autowired
    ReservedEquipmentRepository reservedEquipmentRepository;

    public List<ReservedEquipment> findAll(){
        return reservedEquipmentRepository.findAll();
    }
    public ReservedEquipment save(ReservedEquipment equipment){
        return reservedEquipmentRepository.save(equipment);
    }
    public Optional<ReservedEquipment> findById(long id){
        return reservedEquipmentRepository.findById(id);
    }
    public void delete(ReservedEquipment equipment){
        reservedEquipmentRepository.delete(equipment);
    }
    public List<ReservedEquipment> findByAppointmentId(Long appointmentId) { return reservedEquipmentRepository.findByAppointmentId(appointmentId);}
}
