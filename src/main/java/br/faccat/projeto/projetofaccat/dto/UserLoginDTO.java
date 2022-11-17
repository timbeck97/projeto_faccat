package br.faccat.projeto.projetofaccat.dto;

import br.faccat.projeto.projetofaccat.model.User;

import java.util.stream.Collectors;

public class UserLoginDTO {
    private String name;
    private String username;
    private AddressDTO address;
    private String roles="";
    private String token;
    private String refreshToken;

    public UserLoginDTO(User user, String token, String refreshToken) {
        this.name = user.getName();
        this.username = user.getUsername();
        this.address = new AddressDTO(user.getAddress());
        this.roles=this.roles.join(", ", user.getRoles().stream().map(x->x.getName()).collect(Collectors.toList()));
        this.token=token;
        this.refreshToken=refreshToken;
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
