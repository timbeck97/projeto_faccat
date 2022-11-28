/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.dto;

import br.faccat.projeto.projetofaccat.enums.EProductCategory;

/**
 *
 * @author tim
 */
public class ProductDTO {
    private String description;
    private EProductCategory category;

    public ProductDTO() {
    }

    public ProductDTO(String description, EProductCategory category) {
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EProductCategory getCategory() {
        return category;
    }

    public void setCategory(EProductCategory category) {
        this.category = category;
    }
    
    
}
