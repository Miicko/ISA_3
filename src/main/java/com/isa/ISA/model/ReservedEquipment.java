package com.isa.ISA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "reserved_equipments")
public class ReservedEquipment {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    //@ManyToOne(cascade = CascadeType.ALL)
    //fetch eager da vrati i tutorial
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="appointment_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Appointment appointment;

    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "quantity")
    private int quantity;

    public ReservedEquipment(){}

    public ReservedEquipment(long id, Appointment appointment, String equipmentName,  int quantity) {

        this.appointment = appointment;
        this.equipmentName = equipmentName;
        this.id = id;
        this.quantity = quantity;
    }
    public ReservedEquipment(long id, String equipmentName,  int quantity) {

        this.appointment = appointment;
        this.equipmentName = equipmentName;
        this.id = id;
        this.quantity = quantity;
    }
    public ReservedEquipment(String equipmentName, int quantity) {
        this.equipmentName = equipmentName;
        this.quantity = quantity;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ReservedEquipment{" +
                "appointment=" + appointment +
                ", id=" + id +
                ", equipmentName='" + equipmentName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
