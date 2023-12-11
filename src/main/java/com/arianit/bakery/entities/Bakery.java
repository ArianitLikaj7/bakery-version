package com.arianit.bakery.entities;

import com.arianit.bakery.dto.BakeryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "bakeries")
public class Bakery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bakeryId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String bakeryName;

    @Column(name = "bakery_address",length = 100)
    private String bakeryAddress;

    public static Bakery fromDTO(BakeryDTO bakeryDTO) {
        Bakery bakery = new Bakery();
        bakery.setBakeryId(bakeryDTO.getBakeryId());
        bakery.setBakeryName(bakeryDTO.getBakeryName());
        bakery.setBakeryAddress(bakeryDTO.getBakeryAdres());
        return bakery;
    }

    public BakeryDTO toDTO() {
        return BakeryDTO.builder()
                .bakeryId(this.bakeryId)
                .bakeryName(this.bakeryName)
                .bakeryAdres(this.bakeryAddress)
                .build();
    }
}
