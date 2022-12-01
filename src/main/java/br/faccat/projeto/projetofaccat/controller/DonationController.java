/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.controller;

import br.faccat.projeto.projetofaccat.dto.DonationDTO;
import br.faccat.projeto.projetofaccat.dto.ItemDonationDTO;
import br.faccat.projeto.projetofaccat.dto.UserDTO;
import br.faccat.projeto.projetofaccat.enums.EDonationStatus;
import br.faccat.projeto.projetofaccat.exceptions.DataNotFoundException;
import br.faccat.projeto.projetofaccat.model.Donation;
import br.faccat.projeto.projetofaccat.model.ItemDonation;
import br.faccat.projeto.projetofaccat.model.Product;
import br.faccat.projeto.projetofaccat.model.User;
import br.faccat.projeto.projetofaccat.repository.DonationRepository;
import br.faccat.projeto.projetofaccat.repository.ItemDonationRepository;
import br.faccat.projeto.projetofaccat.repository.ProductRepository;
import br.faccat.projeto.projetofaccat.repository.UserRepository;
import br.faccat.projeto.projetofaccat.service.DonationService;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tim
 */
@RestController
@RequestMapping(value = "/donations")
@Api(value = "Rest API - Donations")
public class DonationController {
    
    
    private final DonationRepository donationRepository;
    private final ItemDonationRepository itemDonationRepository;
    private final UserRepository userRepository;
    private final DonationService donationService;
    
    public DonationController(DonationRepository donationRepository, ItemDonationRepository itemDonationRepository, UserRepository userRepository,DonationService donationService){
        this.donationRepository=donationRepository;
        this.itemDonationRepository=itemDonationRepository;
        this.userRepository=userRepository;
        this.donationService=donationService;
    }
    
    
    @GetMapping
    public ResponseEntity<List<DonationDTO>> getDonations(){
      
        return ResponseEntity.status(200).body(donationRepository.findAll().stream()
        .map(d->donationService.getDonationDTO(d))
        .sorted((x,y)->x.getId().compareTo(y.getId()))
        .collect(Collectors.toList()));
    }
    @GetMapping(value = "/{login}")
    public ResponseEntity<List<DonationDTO>> getDonation(@PathVariable String login){
        User user=userRepository.findByUsername(login);
        if(user==null){
            throw new DataNotFoundException("User not found");
        }
        return ResponseEntity.status(200).body(donationRepository.findByLogin(login)
        .stream()
        .map(x->donationService.getDonationDTO(x))
        .collect(Collectors.toList()));
       
    }
    
    @PostMapping
    public ResponseEntity<DonationDTO> saveDonation(@RequestBody DonationDTO donation){
        Donation d=new Donation();
        d.setDonationDate(donation.getDonationDate());
        d.setRequestDate(donation.getRequestDate());
        d.setStatus(EDonationStatus.PENDING);
//        d.setType(donation.getType());
        User user=userRepository.findByUsername(donation.getUserName());
        d.setUser(user);
        d=donationRepository.save(d);

        for(ItemDonationDTO item: donation.getItens()){
            ItemDonation newItem=new ItemDonation(new Product(item.getProduct()), d, item.getQuantity());
            itemDonationRepository.save(newItem);
        }
        
        
        return ResponseEntity.status(201).body(donationService.getDonationDTO(d));
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<DonationDTO> confirmDonation(@PathVariable Long id){
       Donation d=donationRepository.findById(id).get();
       d.setStatus(EDonationStatus.CONCLUDED);
       
        
        return ResponseEntity.status(201).body(donationService.getDonationDTO(donationRepository.save(d)));
    }
    
    
}
