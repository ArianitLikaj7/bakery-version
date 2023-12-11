package com.arianit.bakery.dto;

import com.arianit.bakery.entities.Bakery;
import com.arianit.bakery.entities.BakeryProduct;
import com.arianit.bakery.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BakeryProductDTO {

    private long bakeryProductId;
    private BakeryDTO bakery;  // Change the type to BakeryDTO
    private Product product;

    public static BakeryProductDTO fromEntity(BakeryProduct bakeryProduct) {
        BakeryProductDTO bakeryProductDTO = new BakeryProductDTO();
        bakeryProductDTO.setBakeryProductId(bakeryProduct.getBakeryProductId());
        bakeryProductDTO.setBakery(BakeryDTO.fromEntity(bakeryProduct.getBakery()));  // Use BakeryDTO's fromEntity
        bakeryProductDTO.setProduct(bakeryProduct.getProduct());
        return bakeryProductDTO;
    }

    public static BakeryProduct toEntity(BakeryProductDTO bakeryProductDTO) {
        BakeryProduct bakeryProduct = new BakeryProduct();
        bakeryProduct.setBakeryProductId(bakeryProductDTO.getBakeryProductId());
        bakeryProduct.setBakery(BakeryDTO.toEntity(bakeryProductDTO.getBakery()));  // Use BakeryDTO's toEntity
        bakeryProduct.setProduct(bakeryProductDTO.getProduct());
        return bakeryProduct;
    }
}
