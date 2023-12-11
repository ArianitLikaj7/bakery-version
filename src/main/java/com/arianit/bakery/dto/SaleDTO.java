package com.arianit.bakery.dto;

import com.arianit.bakery.entities.Bakery;
import com.arianit.bakery.entities.Product;
import com.arianit.bakery.entities.Sale;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SaleDTO {

    private BakeryDTO bakeryDTO;
    private ProductDTO productDTO;
    private long quantitySold;
    private LocalDateTime saleDate;
    private long saleId;

        public static SaleDTO fromEntity(Sale sale){
            SaleDTO saleDTO = new SaleDTO();
            saleDTO.setSaleId(sale.getSaleId());
            System.out.println("SaleDTO saleId: " + saleDTO.getSaleId());
            saleDTO.setBakeryDTO(BakeryDTO.fromEntity(sale.getBakery()));
            saleDTO.setProductDTO(ProductDTO.fromEntity(sale.getProduct()));
            saleDTO.setQuantitySold(sale.getQuantitySold());
            saleDTO.setSaleDate(sale.getSaleDate());
            return saleDTO;
        }

    public static Sale toEntity(SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setSaleId(saleDTO.getSaleId());
        sale.setBakery(Bakery.fromDTO(saleDTO.getBakeryDTO()));
        sale.setProduct(Product.fromDTO(saleDTO.getProductDTO()));
        sale.setQuantitySold(saleDTO.getQuantitySold());
        sale.setSaleDate(saleDTO.getSaleDate());
        return sale;
    }



}
