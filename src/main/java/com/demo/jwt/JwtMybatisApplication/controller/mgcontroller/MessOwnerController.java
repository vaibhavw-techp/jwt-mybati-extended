package com.demo.jwt.JwtMybatisApplication.controller.mgcontroller;

import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerDisplayDto;
import com.demo.jwt.JwtMybatisApplication.service.mgservice.MessOwnerService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mess-owners")
public class MessOwnerController {

    @Autowired
    private MessOwnerService messOwnerService;


    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<MessOwnerDisplayDto> createMessOwner(@RequestBody MessOwnerAdditionDto messOwnerAdditionDto) {
        return ResponseEntity.ok().body(messOwnerService.createMessOwner(messOwnerAdditionDto));
    }

    @GetMapping
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<List<MessOwnerDisplayDto>> getAllMessOwners() {
        return ResponseEntity.ok().body(messOwnerService.getAllMessOwners());
    }


}