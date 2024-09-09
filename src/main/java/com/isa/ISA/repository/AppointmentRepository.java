package com.isa.ISA.repository;

import com.isa.ISA.model.Appointment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByCompanyId(Long companyId);
    List<Appointment> findByUserId(Long userId);
    List<Appointment> findByIsReserved(boolean isReserved);
    @Transactional
    void deleteByCompanyId(Long companyId);
}
