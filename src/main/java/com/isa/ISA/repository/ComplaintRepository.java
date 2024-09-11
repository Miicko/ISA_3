package com.isa.ISA.repository;

import com.isa.ISA.model.Complaint;
import com.isa.ISA.model.Equipment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByUserId(Long userId);
    List<Complaint> findByCompanyId(Long companyId);
    @Transactional
    void deleteByUserId(Long userId);
}
