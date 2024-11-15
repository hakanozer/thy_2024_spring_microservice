package com.works.restcontroller;

import com.works.entities.Product;
import com.works.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductRestController {

    final ProductService productService;

    @PostMapping("save")
    public Product save(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @GetMapping("list")
    public Page list(@RequestParam Integer page) {
        return productService.findAllProduct(page);
    }

    @GetMapping("listFind")
    public Page list(@RequestParam Integer page, @RequestParam Integer size) {
        return productService.findAllProduct(page,size);
    }

}
