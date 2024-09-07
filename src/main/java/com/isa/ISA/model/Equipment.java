package com.isa.ISA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "equipments")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    //@ManyToOne(cascade = CascadeType.ALL)
    //fetch eager da vrati i tutorial
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="company_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Company company;

    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "quantity")
    private int quantity;

    public Equipment(){}

    public Equipment(Company company, String equipmentName, long id, int quantity) {
        this.company = company;
        this.equipmentName = equipmentName;
        this.id = id;
        this.quantity = quantity;
    }
    public Equipment(String equipmentName, int quantity) {
        this.equipmentName = equipmentName;
        this.quantity = quantity;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        return "Equipment{" +
                "company=" + company +
                ", id=" + id +
                ", equipmentName='" + equipmentName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
