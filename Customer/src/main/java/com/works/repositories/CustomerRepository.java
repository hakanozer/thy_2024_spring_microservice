package com.works.repositories;

import com.works.entities.Customer;
import com.works.projections.ICustomerAddres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select CUSTOMER.CID, CUSTOMER.EMAIL, CUSTOMER.NAME, A.CITY from PUBLIC.CUSTOMER inner join\n" +
            "    PUBLIC.CUSTOMER_ADDRESS_LIST CAL on CUSTOMER.CID = CAL.CUSTOMER_CID\n" +
            "    inner join PUBLIC.ADDRESS A on A.AID = CAL.ADDRESS_LIST_AID", nativeQuery = true)
    List<ICustomerAddres> getCustomerJoin();

}