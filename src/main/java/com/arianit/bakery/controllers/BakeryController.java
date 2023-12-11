package com.arianit.bakery.controllers;

import com.arianit.bakery.dto.BakeryDTO;
import com.arianit.bakery.dto.UserDTO;
import com.arianit.bakery.entities.Bakery;
import com.arianit.bakery.services.BakeryService;
import com.arianit.bakery.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bakeries")
public class BakeryController {

    private final BakeryService bakeryService;
    private final UserService userService;

    @Autowired
    public BakeryController(BakeryService bakeryService, UserService userService) {
        this.bakeryService = bakeryService;
        this.userService = userService;
    }

    @GetMapping("/get/{bakeryId}")
    public ResponseEntity<BakeryDTO> getBakeryById(@PathVariable Long bakeryId){
        return bakeryService.getById(bakeryId)
                .map(bakeryDTO -> new ResponseEntity<>(bakeryDTO, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.OK));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<BakeryDTO>> getAllBakeries(){
        List<BakeryDTO> bakeryDTOS = bakeryService.getAll();
        return new ResponseEntity<>(bakeryDTOS,HttpStatus.OK);
    }

    @PostMapping("/post/{userId}")
    public ResponseEntity<?> createBakery(
            @RequestBody BakeryDTO bakeryDTO,
            @PathVariable Long userId
    ) {
        try {
            UserDTO userDTO = userService.getUserById(userId)
                    .orElseThrow(() -> new Exception("User not found with ID: " + userId));
            Bakery bakery = bakeryService.createBakery(bakeryDTO, UserDTO.toEntity(userDTO));
            BakeryDTO createdBakeryDTO = BakeryDTO.fromEntity(bakery);
            return new ResponseEntity<>(createdBakeryDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions if necessary
            return new ResponseEntity<>("An error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{bakeryId}")
    public ResponseEntity<BakeryDTO> deleteBakery(@PathVariable Long bakeryId){
        try {
            bakeryService.deleteBakeryById(bakeryId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateName/{bakeryId}")
    public ResponseEntity<BakeryDTO> updateBakeryName(@PathVariable Long bakeryId, @RequestBody String newName){
        bakeryService.updateBakeryName(bakeryId,newName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
