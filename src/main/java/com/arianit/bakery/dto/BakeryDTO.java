package com.arianit.bakery.dto;

import com.arianit.bakery.entities.Bakery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BakeryDTO {
    private long bakeryId;
    private String bakeryName;
    private String bakeryAdres;

    public static BakeryDTO fromEntity(Bakery bakery){
        BakeryDTO bakeryDTO = new BakeryDTO();
        bakeryDTO.setBakeryName(bakery.getBakeryName());
        bakeryDTO.setBakeryAdres(bakery.getBakeryAddress());
        return bakeryDTO;
    }

    public static Bakery toEntity(BakeryDTO bakeryDTO){
        Bakery bakery = new Bakery();
        bakery.setBakeryName(bakeryDTO.getBakeryName());
        bakery.setBakeryAddress(bakeryDTO.getBakeryAdres());
        return bakery;
    }
}
