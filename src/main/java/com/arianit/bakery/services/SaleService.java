package com.arianit.bakery.services;

import com.arianit.bakery.dto.BakeryDTO;
import com.arianit.bakery.dto.ProductDTO;
import com.arianit.bakery.dto.SaleDTO;
import com.arianit.bakery.entities.Bakery;
import com.arianit.bakery.entities.Product;
import com.arianit.bakery.entities.Sale;
import com.arianit.bakery.repositories.SaleRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository){
        this.saleRepository = saleRepository;
    }

    public Sale createSale(SaleDTO saleDTO, Bakery bakery, Product product) {
        Sale sale = new Sale();
        sale.setBakery(bakery);
        sale.setProduct(product);
        sale.setQuantitySold(saleDTO.getQuantitySold());
        sale.setSaleDate(LocalDateTime.now()); // You may want to set the sale date based on the DTO if available
        sale.setProfit(calculateProfit(product.getProductPrice(), saleDTO.getQuantitySold()));
        return saleRepository.save(sale);
    }

    private BigDecimal calculateProfit(BigDecimal productPrice, long quantitySold) {
        BigDecimal revenue = productPrice.multiply(BigDecimal.valueOf(quantitySold));
        return revenue;
    }
    public Optional<SaleDTO> getById(Long saleId){
        return saleRepository.findById(saleId).map(SaleDTO::fromEntity);
    }
   public List<SaleDTO> getAllSalesById(Iterable<Long> saleIds){
        return saleRepository.findAllById(saleIds).stream().map(SaleDTO::fromEntity).collect(Collectors.toList());
   }
    public List<SaleDTO> getAll(){
        return saleRepository.findAll().stream().map(SaleDTO::fromEntity).collect(Collectors.toList());
    }

    public boolean existsSaleById(Long saleId){
        return saleRepository.existsById(saleId);
    }

    public void deleteSaleById(Long saleId){
        saleRepository.deleteById(saleId);
    }
    public void delete (SaleDTO saleDTO){
        Sale sale = SaleDTO.toEntity(saleDTO);
        saleRepository.delete(sale);
    }

    public List<SaleDTO> findByBakery(BakeryDTO bakeryDTO){
        Bakery bakery = BakeryDTO.toEntity(bakeryDTO);
        return saleRepository.findByBakery(bakery).stream().map(SaleDTO::fromEntity).collect(Collectors.toList());
    }
   public List<SaleDTO> findByProduct(ProductDTO productDTO){
        Product product = ProductDTO.toEntity(productDTO);
        return saleRepository.findByProduct(product).stream().map(SaleDTO::fromEntity).collect(Collectors.toList());
    }
    public List<SaleDTO> findBySaleDate(LocalDateTime saleDate){
        return saleRepository.findBySaleDate(saleDate).stream().map(SaleDTO::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    public boolean updateQuantitySold(Long userId, Long updateQuantity) {
        int updatedCount = saleRepository.updateQuantitySold(userId, updateQuantity);
        return updatedCount > 0;
    }

}
