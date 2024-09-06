package com.aljis.product_service.service;

import com.aljis.product_service.dto.ProductRequest;
import com.aljis.product_service.dto.ProductResponse;
import com.aljis.product_service.model.Product;
import com.aljis.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription()).price(productRequest.getPrice()).build();


        Product save = productRepository.save(product);

        log.info("Product {} is saved", save.getId());
        return ProductResponse.builder().id(save.getId()).name(save.getName()).description(save.getDescription()).price(save.getPrice()).build();

    }

    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll().stream().map(product ->
                ProductResponse.builder()
                        .id(product.getId())
                        .description(product.getDescription())
                        .name(product.getName())
                        .price(product.getPrice())
                        .build()
        ).collect(Collectors.toList());
    }

    public ProductResponse getProductById(String productId) {
        Product product = productRepository.findById(productId).get();
        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
