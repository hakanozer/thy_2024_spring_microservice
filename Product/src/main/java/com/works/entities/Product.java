package com.works.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Size(min = 3, max = 200)
    @NotEmpty
    @NotNull
    private String title;

    @Size(min = 3, max = 500)
    @NotEmpty
    @NotNull
    private String detail;

    @Max(1000000)
    @Min(0)
    @NotNull
    private Integer price;

}