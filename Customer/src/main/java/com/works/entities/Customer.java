package com.works.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Customer extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private String name;
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    List<Address> addressList;


}
