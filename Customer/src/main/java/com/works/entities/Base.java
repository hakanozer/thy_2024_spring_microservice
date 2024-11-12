package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class Base {

    @JsonIgnore
    @CreatedDate
    private Long createdDate;

    @JsonIgnore
    @LastModifiedDate
    private Long lastModifiedDate;

}
