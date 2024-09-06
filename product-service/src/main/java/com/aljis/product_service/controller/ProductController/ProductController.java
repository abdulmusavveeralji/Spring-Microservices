package com.aljis.product_service.controller.ProductController;

import com.aljis.product_service.dto.ProductRequest;
import com.aljis.product_service.dto.ProductResponse;
import com.aljis.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {

        ProductResponse product = productService.createProduct(productRequest);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        List<ProductResponse> allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping(path = "{productId}")
    public ResponseEntity<ProductResponse> getAllProduct(@PathVariable("productId") String productId) {
        ProductResponse productById = productService.getProductById(productId);
        return ResponseEntity.ok(productById);
    }
}
