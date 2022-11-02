/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.service;


import br.faccat.projeto.projetofaccat.model.Role;
import br.faccat.projeto.projetofaccat.model.User;
import java.util.List;

/**
 *
 * @author tim
 */
public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    List<User> getUsers();
    User getUser(String username);
}
