package com.arianit.bakery.controllers;

import com.arianit.bakery.dto.BakeryDTO;
import com.arianit.bakery.dto.ProductDTO;
import com.arianit.bakery.dto.SaleDTO;
import com.arianit.bakery.entities.Bakery;
import com.arianit.bakery.entities.Product;
import com.arianit.bakery.entities.Sale;
import com.arianit.bakery.services.BakeryService;
import com.arianit.bakery.services.SaleService;
import jakarta.persistence.EntityNotFoundException;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;
    private BakeryService bakeryService;

    @Autowired
    public SaleController(SaleService saleService,BakeryService bakeryService) {
        this.saleService = saleService;
        this.bakeryService = bakeryService;
    }

    @GetMapping("/get/{saleId}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable Long saleId){
       return saleService.getById(saleId)
               .map(saleDTO -> new ResponseEntity<>(saleDTO, HttpStatus.OK))
               .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SaleDTO>> getAllSales(){
        List<SaleDTO> sales = saleService.getAll();
        return new ResponseEntity<>(sales,HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<SaleDTO> createSale(@RequestBody SaleDTO saleDTO){
        BakeryDTO bakery = saleDTO.getBakeryDTO();
        ProductDTO product = saleDTO.getProductDTO();

        Product product1 = ProductDTO.toEntity(product);
        Bakery bakery1 = BakeryDTO.toEntity(bakery);

        if ( bakery1.getBakeryId() == 0) {
            bakery1 = bakeryService.saveBakery(bakery1);
        }
        Sale sale = saleService.createSale(saleDTO, bakery1, product1);
        SaleDTO createdSaleDTO = SaleDTO.fromEntity(sale);
        return new ResponseEntity<>(createdSaleDTO, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{saleId}")
    public ResponseEntity<SaleDTO> deleteSale(@PathVariable Long saleId){
        try {
            saleService.deleteSaleById(saleId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateQuantitySold/{saleId}")
    public ResponseEntity<Void> updateSale(@PathVariable Long saleId, @RequestBody Map<String, Long> request) {
        Long updateQuantitySold = request.get("updateQuantitySold");

        if (updateQuantitySold == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        boolean updated = saleService.updateQuantitySold(saleId, updateQuantitySold);
        return updated ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
