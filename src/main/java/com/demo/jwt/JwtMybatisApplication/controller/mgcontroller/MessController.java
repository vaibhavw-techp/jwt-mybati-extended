package com.demo.jwt.JwtMybatisApplication.controller.mgcontroller;

import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerDisplayInfoDto;
import com.demo.jwt.JwtMybatisApplication.service.mgservice.MessService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/mess")
public class MessController {

    @Autowired
    private MessService messService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MessDisplayDto createMess(@RequestBody MessAdditionDto mess) {
        return messService.createMess(mess);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MESS_OWNER')")
    public List<MessDisplayDto> getAllMess() {
        return messService.getAllMess();
    }

    @GetMapping("{messId}/owners")
    @PreAuthorize("hasRole('ROLE_ADMIN') or (hasRole('ROLE_MESS_OWNER') and #messId == authentication.token.claims['assc_id'])")
    public MessOwnerDisplayInfoDto getOwnerByMessId(@PathVariable Long messId){
        MessOwnerDisplayInfoDto messOwner = messService.getOwnerByMessId(messId);
        return messOwner;
    }

}