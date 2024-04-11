package com.demo.jwt.JwtMybatisApplication.controller.mgcontroller;

import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerDisplayDto;
import com.demo.jwt.JwtMybatisApplication.service.mgservice.MessOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mess-owners")
public class MessOwnerController {

    @Autowired
    private MessOwnerService messOwnerService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MessOwnerDisplayDto createMessOwner(@RequestBody MessOwnerAdditionDto messOwnerAdditionDto) {
        return messOwnerService.createMessOwner(messOwnerAdditionDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<MessOwnerDisplayDto> getAllMessOwners() {
        return messOwnerService.getAllMessOwners();
    }


}