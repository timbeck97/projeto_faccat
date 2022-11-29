/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.repository;

import br.faccat.projeto.projetofaccat.model.Donation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author tim
 */
public interface DonationRepository extends JpaRepository<Donation, Long> {
    
    @Query(value = "SELECT d from Donation d WHERE d.user.username=:userName")
    public List<Donation> findByLogin(@Param("userName")String userName);
}
