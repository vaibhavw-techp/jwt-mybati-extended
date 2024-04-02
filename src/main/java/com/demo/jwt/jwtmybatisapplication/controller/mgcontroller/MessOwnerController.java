package com.demo.jwt.jwtmybatisapplication.controller.mgcontroller;

import com.demo.jwt.jwtmybatisapplication.dto.mgdto.MessOwnerAdditionDto;
import com.demo.jwt.jwtmybatisapplication.dto.mgdto.MessOwnerDisplayDto;
import com.demo.jwt.jwtmybatisapplication.service.mgservice.MessOwnerService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mess-owners")
public class MessOwnerController {

    @Autowired
    private MessOwnerService messOwnerService;


    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<MessOwnerDisplayDto> createMessOwner(@RequestBody MessOwnerAdditionDto messOwnerAdditionDto) {
        return ResponseEntity.ok().body(messOwnerService.createMessOwner(messOwnerAdditionDto));
    }

    @GetMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<MessOwnerDisplayDto>> getAllMessOwners() {
        return ResponseEntity.ok().body(messOwnerService.getAllMessOwners());
    }

}
