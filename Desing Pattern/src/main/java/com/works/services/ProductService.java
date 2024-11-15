package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page findAllProduct(int page) {
        Pageable pageable = PageRequest.of(page, 3);
        Page productPage = productRepository.findAll(pageable);
        return productPage;
    }

    @Cacheable("product")
    public Page findAllProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page productPage = productRepository.findAll(pageable);
        return productPage;
    }

}
