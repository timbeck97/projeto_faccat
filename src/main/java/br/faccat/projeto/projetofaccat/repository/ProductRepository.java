/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.repository;

import br.faccat.projeto.projetofaccat.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tim
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByEnabled(boolean enabled);
}
