/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.controller;

import br.faccat.projeto.projetofaccat.dto.ProductDTO;
import br.faccat.projeto.projetofaccat.enums.EProductCategory;
import br.faccat.projeto.projetofaccat.model.Product;
import br.faccat.projeto.projetofaccat.repository.ProductRepository;
import io.swagger.annotations.Api;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tim
 */
@RestController
@RequestMapping(value = "/products")
@Api(value = "Rest API - Products")
public class ProductController {
    
    private final ProductRepository productRepository;
    
    
    public ProductController(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return ResponseEntity.status(200).body(productRepository.findAll()
        .stream()
        .map(x->new ProductDTO(x))
        .collect(Collectors.toList()));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> getProducts(@PathVariable Long id){
        return productRepository.findById(id).map(p->ResponseEntity.status(200).body(new ProductDTO(p))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
       
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO product){
        Product p = new Product();
        p.setCategory(EProductCategory.valueOf(product.getCategory()));
        p.setDescription(product.getDescription());
        p.setEnabled(product.isEnabled());
        return ResponseEntity.status(201).body(new ProductDTO(productRepository.save(p)));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Product> saveProduct(@PathVariable Long id, @RequestParam(required = true) boolean enable){
        Product p = productRepository.findById(id).get();
        p.setEnabled(enable);
        return ResponseEntity.status(201).body(productRepository.save(p));
    }
    
}
