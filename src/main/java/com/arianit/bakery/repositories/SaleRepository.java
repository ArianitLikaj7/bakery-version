package com.arianit.bakery.repositories;

import com.arianit.bakery.dto.BakeryDTO;
import com.arianit.bakery.dto.ProductDTO;
import com.arianit.bakery.dto.SaleDTO;
import com.arianit.bakery.entities.Bakery;
import com.arianit.bakery.entities.Product;
import com.arianit.bakery.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    Optional<Sale> findById (Long saleId);

    @Query("SELECT bp FROM Sale bp WHERE bp.saleId IN :ids")
    List<Sale> findAllById(@Param("ids") Long saleIds);
    List<Sale> findAll();

    boolean existsById(Long id);

    void deleteById(Long saleId);
    void delete (Sale sale);

    List<Sale> findByBakery(Bakery bakery);
    List<Sale> findByProduct(Product product);
    List<Sale> findBySaleDate(LocalDateTime saleDate);

    @Modifying
    @Transactional
    @Query("UPDATE Sale s SET s.quantitySold = :quantitySold WHERE s.saleId = :saleId")
    int updateQuantitySold(@Param("saleId") Long saleId, @Param("quantitySold") Long quantitySold);
}
