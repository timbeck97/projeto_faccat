/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.service;

import br.faccat.projeto.projetofaccat.dto.DonationDTO;
import br.faccat.projeto.projetofaccat.dto.ItemDonationDTO;
import br.faccat.projeto.projetofaccat.model.Donation;
import br.faccat.projeto.projetofaccat.repository.DonationRepository;
import br.faccat.projeto.projetofaccat.repository.ItemDonationRepository;
import br.faccat.projeto.projetofaccat.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tim
 */
@Service
public class DonationService {
    
    @Autowired
    private DonationRepository donationRepository;
    @Autowired
    private ItemDonationRepository itemDonationRepository;
    @Autowired
    private UserRepository userRepository;
    
    public DonationDTO getDonationDTO(Donation d){
        List<ItemDonationDTO> items=itemDonationRepository.findByDonation(d).stream().map(x->new ItemDonationDTO(x)).collect(Collectors.toList());
        return new DonationDTO(d, items);
    }
}
