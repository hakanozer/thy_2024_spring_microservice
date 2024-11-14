package com.works.services;

import com.works.entities.Product;
import com.works.feignRepositories.ICustomer;
import com.works.feignRepositories.IDummy;
import com.works.models.Customer;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    final DiscoveryClient discoveryClient;
    final ProductRepository productRepository;
    final RestTemplate restTemplateNotConfig;
    final ICustomer iCustomer;
    final IDummy iDummy;
    final CircuitBreakerFactory circuitBreakerFactory;
    final Resilience4JCircuitBreakerFactory globalCustomConfiguration;
    final JmsTemplate jmsTemplate;


    public ResponseEntity save(Product product) {
        try {
            //int i = 1 / 0;
            //productRepository.save(product);
            Runnable rn = () -> {
                for (int i = 0; i < 1000000; i++) {
                    Product p = new Product();
                    p.setTitle(UUID.randomUUID().toString());
                    p.setPrice(new Random().nextInt(10000));
                    p.setDetail("Pro Detail");
                    saveProduct(product);
                }
            };
            new Thread(rn).start();

            return new ResponseEntity("İşleme Alındı", HttpStatus.OK);
        }catch (Exception ex) {
            return new ResponseEntity(product, HttpStatus.BAD_REQUEST);
        }
    }

    // jsm -> send fnc
    public void saveProduct( Product product ) {
        jmsTemplate.convertAndSend("productDest", product);
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

    public void allCustomer() {
        List<ServiceInstance> ls = discoveryClient.getInstances("customer");
        if( ls != null && !ls.isEmpty() ) {
            ServiceInstance serviceInstance = ls.get(0);

            // String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
            String url = serviceInstance.getUri().toString();
            url = url + "/customer/list";
            System.out.println(url);
            ResponseEntity<Customer[]> responseEntity = restTemplateNotConfig.getForEntity(url, Customer[].class);
            Customer[] customers = responseEntity.getBody();
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        }
    }

    public Customer[] findAllCustomer() {
        return iCustomer.customerList();
    }

    public String singleProduct(String id) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker.run(() ->
                iDummy.products(id), // Başarılı işlem
                throwable ->fncFallBackName(id) // Hata durumunda fallback fonksiyonu
        );
    }

    public String fncFallBackName(String id) {
        return "Error Service : " + id;
    }



}
