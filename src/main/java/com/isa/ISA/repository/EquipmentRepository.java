package com.isa.ISA.repository;

import com.isa.ISA.model.Equipment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    List<Equipment> findByCompanyId(Long companyId);
    @Transactional
    void deleteByCompanyId(long companyId);
}
