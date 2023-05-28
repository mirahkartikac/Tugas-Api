package com.dom.demo.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Orders")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value={"createAt", "updateAt"}, allowGetters = true)

public class Orders {
    
    @Id
    int id;

    int buyer;
    int note;
    int total;
    int discount;
    boolean is_paid;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getBuyer() {
        return buyer;
    }
    public void setBuyer(int buyer) {
        this.buyer = buyer;
    }
    public int getNote() {
        return note;
    }
    public void setNote(int note) {
        this.note = note;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public boolean getIs_paid() {
        return is_paid;
    }
    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }
    
}
