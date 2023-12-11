package com.arianit.bakery.services;

import com.arianit.bakery.dto.BakeryDTO;
import com.arianit.bakery.dto.UserDTO;
import com.arianit.bakery.entities.Bakery;
import com.arianit.bakery.entities.User;
import com.arianit.bakery.repositories.BakeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BakeryService {

    private final BakeryRepository bakeryRepository;

    @Autowired
    public BakeryService(BakeryRepository bakeryRepository){
        this.bakeryRepository = bakeryRepository;
    }

    public Bakery createBakery(BakeryDTO bakeryDTO, User user) {
        Bakery bakery = new Bakery();
        bakery.setBakeryName(bakeryDTO.getBakeryName());
        bakery.setBakeryAddress(bakeryDTO.getBakeryAdres());
        bakery.setUser(user);
        return bakeryRepository.save(bakery);
    }


    public Optional<BakeryDTO> getById(Long bakeyId){
        return  bakeryRepository.findById(bakeyId).map(BakeryDTO::fromEntity);
    }

    public List<BakeryDTO> getBakeriesById (Iterable<Long> bakeryIds){
        return bakeryRepository.findBakeriesById(bakeryIds).stream().map(BakeryDTO::fromEntity).collect(Collectors.toList());
    }

    public List<BakeryDTO> getAll(){
        return bakeryRepository.findAll().stream().map(BakeryDTO::fromEntity).collect(Collectors.toList());
    }

    public boolean existBakeryById(long bakeryId){
        return bakeryRepository.existsById(bakeryId);
    }

    public long countBakeries(){
        return bakeryRepository.count();
    }

    public void deleteBakeryById(Long bakeryId){
        bakeryRepository.deleteById(bakeryId);
    }
    @Transactional
    public boolean updateBakeryName(Long bakeryId, String newName) {
        int affectedRows = bakeryRepository.updateBakeryName(bakeryId, newName);
       return affectedRows > 0;
    }

    public void deleteBakery(BakeryDTO bakeryDTO){
        Bakery bakery = BakeryDTO.toEntity(bakeryDTO);
        bakeryRepository.delete(bakery);
    }
    public Optional<BakeryDTO> getByBakeryName(String bakeryName){
        return bakeryRepository.findByBakeryName(bakeryName).map(BakeryDTO::fromEntity);
    }

    public Bakery saveBakery(Bakery bakery1) {
        bakeryRepository.save(bakery1);
        return bakery1;
    }
}
