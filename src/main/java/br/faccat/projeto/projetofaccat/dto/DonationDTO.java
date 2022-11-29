/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.dto;

import br.faccat.projeto.projetofaccat.enums.EDonationStatus;
import br.faccat.projeto.projetofaccat.enums.EDonationType;
import br.faccat.projeto.projetofaccat.model.Donation;
import br.faccat.projeto.projetofaccat.model.ItemDonation;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tim
 */
public class DonationDTO implements Serializable {

    private Long id;
    
    private String userName;
    
    private Date requestDate;
    
    private Date donationDate;
    
    private EDonationStatus status;
    
    private EDonationType type;
    
    private List<ItemDonationDTO> itens;
    
    public DonationDTO() {
    }

    public DonationDTO(Donation d, List<ItemDonationDTO> itens) {
        this.userName = d.getUser().getUsername();
        this.requestDate = d.getRequestDate();
        this.donationDate = d.getDonationDate();
        this.status = d.getStatus();
        this.type = d.getType();
        this.itens=itens;
        this.id=d.getId();
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

    public List<ItemDonationDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemDonationDTO> itens) {
        this.itens = itens;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
