package com.arianit.bakery.repositories;

import com.arianit.bakery.entities.Bakery;
import com.arianit.bakery.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);
    @Query("SELECT p FROM Product p WHERE p.productId IN :ids")
    List<Product> findAllById(@Param("ids") Iterable<Long> productIds);

    List<Product> findAll();

    boolean existsById(Long productId);

    long count();

    void deleteById(Long productId);

    void delete(Product product);

    Optional<Product> findByProductName(String name);

    @Modifying
    @Query("UPDATE Product p SET p.productName = :newName WHERE p.productId = :productId")
    int updateProductName(@Param("productId")Long productId, @Param("newName")String newName);
}
