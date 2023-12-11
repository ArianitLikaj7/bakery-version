package com.arianit.bakery.services;

import com.arianit.bakery.dto.BakeryDTO;
import com.arianit.bakery.dto.BakeryProductDTO;
import com.arianit.bakery.entities.Bakery;
import com.arianit.bakery.entities.BakeryProduct;
import com.arianit.bakery.entities.Product;
import com.arianit.bakery.repositories.BakeryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BakeryProductService {

    private final BakeryProductRepository bakeryProductRepository;
    private final BakeryService bakeryService;
    private final ProductService productService;

    @Autowired
    public BakeryProductService(BakeryProductRepository bakeryProductRepository, BakeryService bakeryService, ProductService productService) {
        this.bakeryProductRepository = bakeryProductRepository;
        this.bakeryService = bakeryService;
        this.productService = productService;
    }

    public BakeryProduct createBakeryProduct(BakeryProductDTO bakeryProductDTO, Bakery bakery, Product product){
        BakeryProduct bakeryProduct = new BakeryProduct();
        bakeryProduct.setBakery(bakery);
        bakeryProduct.setProduct(product);
        return bakeryProduct;
    }

    public Optional<BakeryProductDTO> getById(Long bakeryProductId){
        return bakeryProductRepository.findById(bakeryProductId).map(BakeryProductDTO::fromEntity);
    }


    public List<BakeryProductDTO> getAllBakeryProduct(Iterable<Long> bakeryProducts){
        return bakeryProductRepository.findAllById(bakeryProducts).stream().map(BakeryProductDTO::fromEntity).collect(Collectors.toList());
    }

    public List<BakeryProductDTO> getAllBakeryProduct(){
        return bakeryProductRepository.findAll().stream().map(BakeryProductDTO::fromEntity).collect(Collectors.toList());
    }

   public boolean existsBakeryProductById(Long bakeryProductId){
        return bakeryProductRepository.existsById(bakeryProductId);
   }
    public void deleteById(Long bakeryProductId){
        bakeryProductRepository.deleteById(bakeryProductId);
    }
    public void delete (BakeryProductDTO bakeryProductDTO){
        BakeryProduct bakeryProduct = BakeryProductDTO.toEntity(bakeryProductDTO);
        bakeryProductRepository.delete(bakeryProduct);
    }

}
