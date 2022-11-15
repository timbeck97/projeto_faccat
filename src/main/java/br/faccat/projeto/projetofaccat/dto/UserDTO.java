/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.dto;

import br.faccat.projeto.projetofaccat.model.User;
import java.util.stream.Collectors;

/**
 *
 * @author tim
 */
public class UserDTO {
  
    private String name;
    private String username;
    private AddressDTO address;
    private String roles="";

    public UserDTO(User user) {

        this.name = user.getName();
        this.username = user.getUsername();
        this.address = new AddressDTO(user.getAddress());
        System.out.println("roles -> "+user.getRoles().toString());
        this.roles=this.roles.join(", ", user.getRoles().stream().map(x->x.getName()).collect(Collectors.toList()));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
    
    
    
}
