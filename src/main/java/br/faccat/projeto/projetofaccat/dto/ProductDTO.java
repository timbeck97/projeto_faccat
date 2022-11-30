/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.dto;

import br.faccat.projeto.projetofaccat.enums.EProductCategory;
import br.faccat.projeto.projetofaccat.model.Product;

/**
 *
 * @author tim
 */
public class ProductDTO {
    private Long id;
    private String description;
    private EProductCategory category;
    private boolean enabled;
    
    public ProductDTO() {
    }

    public ProductDTO(String description, EProductCategory category, boolean enabled) {
        this.description = description;
        this.category = category;
        this.enabled=enabled;
    }

    public ProductDTO(Product p) {
        this.id = p.getId();
        this.description = p.getDescription();
        this.category = p.getCategory();
        this.enabled = p.isEnabled();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    
}
