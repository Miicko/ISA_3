package com.isa.ISA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    //@ManyToOne(cascade = CascadeType.ALL)
    //fetch eager da vrati i tutorial
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="company_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Company company;

    @Column(name = "start_time")
    private Date start;
    @Column(name = "end_time")
    private Date end;
    @Column(name = "isReserved")
    private boolean isReserved;
    @Column(name = "userId", nullable = true)
    private long userId;

    /*@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name="user_id", nullable = true)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;*/

    public Appointment(){}
    public Appointment(Date start, Date end) {
        this.end = end;
        this.start = start;
        this.isReserved = false;
        this.userId = -1;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    /*public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    @Override
    public String toString() {
        return "Appointment{" +
                "company=" + company +
                ", id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", isReserved=" + isReserved +
                ", userid=" + userId +
                '}';
    }
}
