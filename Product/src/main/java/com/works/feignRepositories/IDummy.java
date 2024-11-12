package com.works.feignRepositories;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "iDummy", url = "https://dummyjson.com/")
public interface IDummy {

    @GetMapping("products/{id}")
    String products(@PathVariable String id);

}
