package com.works.restcontrolllers;

import com.works.entities.Admin;
import com.works.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin")
public class AdminRestController {

    final AdminService adminService;

    @PostMapping("register")
    public Admin register(@RequestBody Admin admin) {
        return adminService.register(admin);
    }

}
