package com.demo.jwt.JwtMybatisApplication.controller.mgcontroller;

import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerDisplayInfoDto;
import com.demo.jwt.JwtMybatisApplication.service.mgservice.MessService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mess")
public class MessController {

    @Autowired
    private MessService messService;

    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    public MessDisplayDto createMess(@RequestBody MessAdditionDto mess) {
        return messService.createMess(mess);
    }

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_MESS_OWNER"})
    public List<MessDisplayDto> getAllMess() {
        return messService.getAllMess();
    }

    @GetMapping("{messId}/owners")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_MESS_OWNER"})
    public MessOwnerDisplayInfoDto getOwnerByMessId(@PathVariable Long messId){
        MessOwnerDisplayInfoDto messOwner = messService.getOwnerByMessId(messId);
        return messOwner;
    }

}