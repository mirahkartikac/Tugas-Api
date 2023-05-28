package com.dom.demo.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Review")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value={"createAt", "updateAt"}, allowGetters = true)

public class Review {
    
    @Id
    int ordert;

    int star;
    String description;
    public int getOrdert() {
        return ordert;
    }
    public void setOrdert(int ordert) {
        this.ordert = ordert;
    }
    public int getStar() {
        return star;
    }
    public void setStar(int star) {
        this.star = star;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
}
