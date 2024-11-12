package com.works.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.works.entities.Product;
import com.works.feignRepositories.ICustomer;
import com.works.feignRepositories.IDummy;
import com.works.models.Customer;
import com.works.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    final DiscoveryClient discoveryClient;
    final ProductRepository productRepository;
    final RestTemplate restTemplateNotConfig;
    final ICustomer iCustomer;
    final IDummy iDummy;


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

    @HystrixCommand(fallbackMethod = "fncFallBackName")
    public String singleProduct(String id) {
        int i = 1 / 0;
        return iDummy.products(id);
    }

    public String fncFallBackName(String id) {
        return "Error Service : " + id;
    }



}
