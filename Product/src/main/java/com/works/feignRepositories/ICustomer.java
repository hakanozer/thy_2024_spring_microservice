package com.works.feignRepositories;

import com.works.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient( value = "customer" )
public interface ICustomer {

    @GetMapping("/customer/list")
    Customer[] customerList();

    @PostMapping("/customer/save")
    Customer saveCustomer(@RequestBody Customer customer);

    // /customer/find?id=100
    @GetMapping("/customer/find")
    Customer findCustomer(@RequestParam Integer id);

}
