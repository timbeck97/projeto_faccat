/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.enums;

/**
 *
 * @author tim
 */
public enum EDonationType {

    PICKUP("PICKUP"),
    DELIVERY("DELIVERY");
    
    
    private EDonationType(String description) {
        this.description = description;
    }
    

    private String description;
}
