package com.works.repositories;

import com.works.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // select * from product where title = ?
    Optional<Product> findByTitleEqualsIgnoreCase(String title);

    //@Query(value = "call procedureName(?, ?)")
    @Query(value = "select  * from product where title = ?1", nativeQuery = true)
    List<Product> findByTitle(String title);

}