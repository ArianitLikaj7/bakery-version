package com.arianit.bakery.services;

import com.arianit.bakery.dto.ProductDTO;
import com.arianit.bakery.entities.Bakery;
import com.arianit.bakery.entities.Product;
import com.arianit.bakery.repositories.ProductRepository;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;  // Assuming you have a UserService to get the authenticated user

    @Autowired
    public ProductService(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }


    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setProductPrice(productDTO.getProductPrice());
        return productRepository.save(product);
    }
    // Get product By id;
    public Optional<ProductDTO> getById(Long productId){
        return productRepository.findById(productId).map(ProductDTO::fromEntity);
    }

    public List<ProductDTO> getAllById(Iterable<Long> productIds){
        return productRepository.findAllById(productIds).stream().map(ProductDTO::fromEntity).collect(Collectors.toList());
    }

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(ProductDTO::fromEntity).collect(Collectors.toList());
    }

   public boolean existsProductById(Long productId){
        return productRepository.existsById(productId);
   }

   public long countProducts(){
        return productRepository.count();
   }

   @Transactional
   public boolean updateProductName (Long productId,String newName){
        int affectedRows = productRepository.updateProductName(productId,newName);
        return affectedRows > 0;
   }

    public void deleteById(Long productId){
        productRepository.deleteById(productId);
    }
    public void delete(ProductDTO productDTO){
       Product product = ProductDTO.toEntity(productDTO);
       productRepository.delete(product);
    }

    public Optional<ProductDTO> getByProductName(String name){
        return productRepository.findByProductName(name).map(ProductDTO::fromEntity);
    }

}
