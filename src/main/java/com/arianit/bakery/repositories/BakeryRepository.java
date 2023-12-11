package com.arianit.bakery.repositories;
import com.arianit.bakery.dto.BakeryDTO;
import com.arianit.bakery.entities.Bakery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BakeryRepository extends JpaRepository<Bakery, Long> {

    Optional<Bakery> findById(Long bakeryId);
    @Query("SELECT bp FROM Bakery bp WHERE bp.bakeryId IN :ids")
    List<Bakery> findBakeriesById(@Param("ids")Iterable<Long> bakeryIds);

    List<Bakery> findAll();
    boolean existsById(Long bakeryId);
    long count();
    void deleteById(Long bakeryId);
    void delete(Bakery bakery);
    Optional<Bakery> findByBakeryName(String bakeryName);
    @Modifying
    @Query("UPDATE Bakery b SET b.bakeryName = :newName WHERE b.bakeryId = :bakeryId")
    int updateBakeryName(@Param("bakeryId") Long bakeryId, @Param("newName") String newName);

}
