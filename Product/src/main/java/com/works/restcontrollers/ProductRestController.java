package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.models.Customer;
import com.works.services.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductRestController {

    final ProductService productService;

    @PostMapping("save")
    public ResponseEntity save(@Valid @RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("list")
    public List<Product> list() {
        return productService.findAll();
    }

    @GetMapping("titleSearch")
    public Optional<Product> titleSearch  (@NotEmpty @RequestParam String title) {
        return productService.titleProduct(title);
    }

    @GetMapping("title")
    public List<Product> title (@NotEmpty @RequestParam String title) {
        return productService.findTitle(title);
    }

    @GetMapping("getCustomer")
    public Customer[] getCustomer() {
        return productService.findAllCustomer();
    }

    @GetMapping("getCustomerFind")
    public String getCustomer(@RequestParam String id) {
        return productService.singleProduct(id);
    }


}
