package com.demo.jwt.JwtMybatisApplication.controller.mgcontroller;

import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerDisplayInfoDto;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.MessOwnerEntity;
import com.demo.jwt.JwtMybatisApplication.service.mgservice.MessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mess")
public class MessController {

    @Autowired
    private MessService messService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<MessDisplayDto> createMess(@RequestBody MessAdditionDto mess) {
        return ResponseEntity.ok().body(messService.createMess(mess));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MESS_OWNER')")
    public ResponseEntity<List<MessDisplayDto>> getAllMess() {
        return ResponseEntity.ok().body(messService.getAllMess());
    }

    @GetMapping("{messId}/owners")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MESS_OWNER')")
    public ResponseEntity<MessOwnerDisplayInfoDto> getOwnerByMessId(@PathVariable Long messId){
        MessOwnerDisplayInfoDto messOwner = messService.getOwnerByMessId(messId);
        return ResponseEntity.ok().body(messOwner);
    }

}