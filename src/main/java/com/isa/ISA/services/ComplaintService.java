package com.isa.ISA.services;

import com.isa.ISA.model.Complaint;
import com.isa.ISA.model.Equipment;
import com.isa.ISA.repository.CompanyRepository;
import com.isa.ISA.repository.ComplaintRepository;
import com.isa.ISA.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {

    @Autowired
    ComplaintRepository complaintRepository;

    public List<Complaint> findAll(){
        return complaintRepository.findAll();
    }
    public Complaint save(Complaint complaint){
        return complaintRepository.save(complaint);
    }
    public Optional<Complaint> findById(long id){
        return complaintRepository.findById(id);
    }
    public void delete(Complaint complaint){
        complaintRepository.delete(complaint);
    }
    public List<Complaint> findByCompanyId(Long companyId) { return complaintRepository.findByCompanyId(companyId);}
    public List<Complaint> findByUserId(Long userId) { return complaintRepository.findByUserId(userId);}
}
