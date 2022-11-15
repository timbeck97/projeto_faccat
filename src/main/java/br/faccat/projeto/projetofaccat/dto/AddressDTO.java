/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.dto;

import br.faccat.projeto.projetofaccat.model.Address;

/**
 *
 * @author tim
 */
public class AddressDTO {

    private String city;

    private String state;

    private String addressNumber;
    
    private String complement;

    public AddressDTO(Address address) {
        this.city = address.getCity();
        this.state = address.getState();
        this.addressNumber = address.getAddressNumber();
        this.complement = address.getComplement();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
    
    
    
}
