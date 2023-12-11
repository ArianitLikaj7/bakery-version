package com.arianit.bakery.controllers;

import com.arianit.bakery.dto.ProductDTO;
import com.arianit.bakery.entities.Product;
import com.arianit.bakery.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    final private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/get/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId){
        return productService.getById(productId)
                .map(productDTO -> new ResponseEntity<>(productDTO, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> productDTOs = productService.getAllProducts();
        return new ResponseEntity<>(productDTOs,HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<ProductDTO> createProduct (@RequestBody ProductDTO productDTO){
        Product productSaved = productService.createProduct(productDTO);
        ProductDTO createdProduct = ProductDTO.fromEntity(productSaved);
        return new ResponseEntity<>(createdProduct,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteById(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateName/{productId}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long productId, @RequestBody String newName){
        boolean updated = productService.updateProductName(productId,newName);
        return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
