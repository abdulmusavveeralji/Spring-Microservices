package com.aljis.product_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    @Id
//    @SequenceGenerator(name = "product_id_sequence",sequenceName = "product_id_sequence")
//    @GeneratedValue(generator = "product_id_sequence", strategy = GenerationType.SEQUENCE)
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}
