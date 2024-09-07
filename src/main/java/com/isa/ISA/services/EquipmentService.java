package com.isa.ISA.services;

import com.isa.ISA.model.Company;
import com.isa.ISA.model.Equipment;
import com.isa.ISA.repository.CompanyRepository;
import com.isa.ISA.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    @Autowired
    EquipmentRepository equipmentRepository;

    public List<Equipment> findAll(){
        return equipmentRepository.findAll();
    }
    public Equipment save(Equipment equipment){
        return equipmentRepository.save(equipment);
    }
    public Optional<Equipment> findById(long id){
        return equipmentRepository.findById(id);
    }
    public void delete(Equipment equipment){
        equipmentRepository.delete(equipment);
    }
    public List<Equipment> findByCompanyId(Long companyId) { return equipmentRepository.findByCompanyId(companyId);}
}
