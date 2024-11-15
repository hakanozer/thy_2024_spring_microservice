package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    final CacheManager cacheManager;
    final ProductRepository productRepository;

    public Product saveProduct(Product product) {
        productRepository.save(product);
        cacheManager.getCache("product").clear();
        return product;
    }

    @Cacheable("product")
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

}
