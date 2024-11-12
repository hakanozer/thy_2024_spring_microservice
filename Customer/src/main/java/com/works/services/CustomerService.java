package com.works.services;

import com.works.entities.Customer;
import com.works.projections.ICustomerAddres;
import com.works.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository cRepo;

    public Customer saveCustomer(Customer customer) {
        return cRepo.save(customer);
    }

    public List<Customer> findAllCustomer() {
        return cRepo.findAll();
    }

    public List<ICustomerAddres> getCustomerJoin() {
        return cRepo.getCustomerJoin();
    }

}
