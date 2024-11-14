package com.works.configs;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

@Configuration
@RequiredArgsConstructor
public class UseJmsListener {

    final ProductRepository productRepository;

    @JmsListener(destination = "productDest", containerFactory = "productContainerFactory")
    public void productSave(Product product) {
        System.out.println(product);
        productRepository.save(product);
    }

}
