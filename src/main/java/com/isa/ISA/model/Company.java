package com.isa.ISA.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "location_lat")
    private double location_lat;

    @Column(name = "location_long")
    private double location_long;

    //@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    //private List<Equipment> equipment;


    public Company(){}

    public Company(String address, String companyName, long id) {
        this.address = address;
        this.companyName = companyName;
        //this.equipment = equipment;
        this.id = id;
        this.location_lat = 0;
        this.location_long = 0;
    }
    public Company(String address, String companyName) {
        this.address = address;
        this.companyName = companyName;
        this.location_lat = 0;
        this.location_long = 0;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /*public List<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLocation_lat() {
        return location_lat;
    }

    public void setLocation_lat(double location_lat) {
        this.location_lat = location_lat;
    }

    public double getLocation_long() {
        return location_long;
    }

    public void setLocation_long(double location_long) {
        this.location_long = location_long;
    }

    @Override
    public String toString() {
        return "Company{" +
                "address='" + address + '\'' +
                ", id=" + id +
                ", companyName='" + companyName + '\'' +
                ", location_lat=" + location_lat +
                ", location_long=" + location_long +
                '}';
    }
}
