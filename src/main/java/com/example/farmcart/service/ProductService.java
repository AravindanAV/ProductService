package com.example.farmcart.service;

import com.example.farmcart.dto.ProductRequest;
import com.example.farmcart.dto.ProductResponse;
import com.example.farmcart.model.Product;
import com.example.farmcart.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {


    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
       Product product = Product.builder()
               .name(productRequest.getName())
               .description(productRequest.getDescription())
               .price(productRequest.getPrice())
               .build();

        productRepository.save(product);
        log.info("Product {} is saved",product.getId());
    }

    public List<ProductResponse> getAllProducts(){
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .name(product.getName())
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

}
