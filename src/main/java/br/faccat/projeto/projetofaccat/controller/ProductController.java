/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.faccat.projeto.projetofaccat.controller;

import br.faccat.projeto.projetofaccat.dto.ProductDTO;
import br.faccat.projeto.projetofaccat.model.Product;
import br.faccat.projeto.projetofaccat.repository.ProductRepository;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tim
 */
@RestController
@RequestMapping(value = "/api/products")
@Api(value = "Rest API - Products")
public class ProductController {
    
    private final ProductRepository productRepository;
    
    
    public ProductController(ProductRepository productRepository){
        this.productRepository=productRepository;
    }
    
    
    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.status(200).body(productRepository.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProducts(@PathVariable Long id){
        return productRepository.findById(id).map(p->ResponseEntity.status(200).body(p)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
       
    }
    
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody ProductDTO product){
        Product p = new Product();
        p.setCategory(product.getCategory());
        p.setDescription(product.getDescription());
        return ResponseEntity.status(201).body(productRepository.save(p));
    }
    
}
