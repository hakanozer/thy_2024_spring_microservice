package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

    public ResponseEntity save(Product product) {
        try {
            //int i = 1 / 0;
            productRepository.save(product);
            return new ResponseEntity(product, HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity(product, HttpStatus.BAD_REQUEST);
        }
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> titleProduct(String title ) {
        if (title.equals("")) {
            return Optional.empty();
        }
        return productRepository.findByTitleEqualsIgnoreCase(title);
    }

    public List<Product> findTitle(String title) {
        return productRepository.findByTitle(title);
    }

}
