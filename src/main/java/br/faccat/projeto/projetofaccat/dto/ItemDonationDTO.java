/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.dto;

import br.faccat.projeto.projetofaccat.model.ItemDonation;
import br.faccat.projeto.projetofaccat.model.Product;

/**
 *
 * @author tim
 */
public class ItemDonationDTO {
   
    private ProductDTO product;
    
    private int quantity;

    public ItemDonationDTO() {
    }
   
    
    public ItemDonationDTO(ItemDonation i) {
        this.product=new ProductDTO(i.getProduct());
        this.quantity=i.getQuantity();
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    
    
}
