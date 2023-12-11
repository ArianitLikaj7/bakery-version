package com.arianit.bakery.controllers;

import com.arianit.bakery.dto.BakeryProductDTO;
import com.arianit.bakery.entities.Bakery;
import com.arianit.bakery.entities.BakeryProduct;
import com.arianit.bakery.services.BakeryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bakeryProduct")
public class BakeryProductController {

    private final BakeryProductService bakeryProductService;

    @Autowired
    public BakeryProductController(BakeryProductService bakeryProductService){
        this.bakeryProductService = bakeryProductService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BakeryProductDTO>> getAllBakeryProduct() {
        List<BakeryProductDTO> bakeryProductDTOS = bakeryProductService.getAllBakeryProduct();
        return new ResponseEntity<>(bakeryProductDTOS, HttpStatus.OK);
    }

    @GetMapping("/get/{bakeryProductId}")
    public ResponseEntity<BakeryProductDTO> getBakeryProductById(@PathVariable Long bakeryProductId) {
        return bakeryProductService.getById(bakeryProductId)
                .map(bakeryProductDTO -> new ResponseEntity<>(bakeryProductDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/post")
    public ResponseEntity<BakeryProductDTO> createBakeryProduct(@RequestBody BakeryProductDTO bakeryProductDTO) {
        BakeryProduct createdBakeryProduct = bakeryProductService.createBakeryProduct(bakeryProductDTO, Bakery.fromDTO(bakeryProductDTO.getBakery()),
                bakeryProductDTO.getProduct());
        BakeryProductDTO createdBakeryProductDTO = BakeryProductDTO.fromEntity(createdBakeryProduct);
        return new ResponseEntity<>(createdBakeryProductDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{bakeryProductId}")
    public ResponseEntity<Void> deleteBakeryProduct(@PathVariable Long bakeryProductId) {
        if (bakeryProductService.existsBakeryProductById(bakeryProductId)) {
            bakeryProductService.deleteById(bakeryProductId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
