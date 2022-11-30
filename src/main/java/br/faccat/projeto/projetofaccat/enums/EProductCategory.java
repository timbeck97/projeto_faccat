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
public enum EProductCategory {
    
    ELETRODOMESTIC("Eletrodoméstico"),
    TOYS("Brinquedos"),
    CLOTHES("Roupas"),
    KITCHEN("Cozinha"),
    BEDROOM("Quarto"),
    FURNITURE("Mobília");
    
    
    EProductCategory(String description){
        this.description=description;
    }
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
