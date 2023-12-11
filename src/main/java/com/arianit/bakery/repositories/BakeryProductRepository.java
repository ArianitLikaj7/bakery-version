package com.arianit.bakery.repositories;

import com.arianit.bakery.dto.BakeryProductDTO;
import com.arianit.bakery.entities.Bakery;
import com.arianit.bakery.entities.BakeryProduct;
import com.arianit.bakery.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BakeryProductRepository extends JpaRepository<BakeryProduct, Long> {

    Optional<BakeryProduct> findById(Long bakeryProductId);
    @Query("SELECT bp FROM BakeryProduct bp WHERE bp.bakeryProductId IN :ids")
    List<BakeryProduct> findAllById(@Param("ids") Iterable<Long> ids);

    List<BakeryProduct> findAll();
    boolean existsById(Long bakeryProductId);
    void deleteById(Long bakeryProductId);
    void delete (BakeryProduct bakeryProduct);
}
