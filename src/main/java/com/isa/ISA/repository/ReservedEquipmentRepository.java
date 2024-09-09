package com.isa.ISA.repository;

import com.isa.ISA.model.Equipment;
import com.isa.ISA.model.ReservedEquipment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservedEquipmentRepository extends JpaRepository<ReservedEquipment, Long> {

    List<ReservedEquipment> findByAppointmentId(Long appointmentId);
    @Transactional
    void deleteByAppointmentId(Long appointmentId);
}
