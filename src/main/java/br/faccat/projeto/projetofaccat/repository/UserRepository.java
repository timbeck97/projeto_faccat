/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.repository;


import br.faccat.projeto.projetofaccat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tim
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
