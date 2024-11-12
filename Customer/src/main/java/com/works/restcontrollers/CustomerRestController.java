package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.projections.ICustomerAddres;
import com.works.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer")
public class CustomerRestController {

    final CustomerService customerService;

    @PostMapping("save")
    public Customer save(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }

    @GetMapping("list")
    public List<ICustomerAddres> list(){
        return customerService.getCustomerJoin();
    }

}
