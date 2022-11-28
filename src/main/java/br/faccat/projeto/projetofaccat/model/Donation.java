/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import br.faccat.projeto.projetofaccat.enums.EDonationStatus;
import br.faccat.projeto.projetofaccat.enums.EDonationType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
/**
 *
 * @author tim
 */
@Entity
public class Donation implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JsonBackReference
    private User user;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date donationDate;
    
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    @NotNull
    private EDonationStatus status;
    
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    @NotNull
    private EDonationType type;

    public Donation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    public EDonationStatus getStatus() {
        return status;
    }

    public void setStatus(EDonationStatus status) {
        this.status = status;
    }

    public EDonationType getType() {
        return type;
    }

    public void setType(EDonationType type) {
        this.type = type;
    }
    
    
    
    
}
