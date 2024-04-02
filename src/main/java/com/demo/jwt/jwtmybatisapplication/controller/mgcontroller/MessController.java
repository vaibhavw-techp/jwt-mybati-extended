package com.demo.jwt.jwtmybatisapplication.controller.mgcontroller;

import com.demo.jwt.jwtmybatisapplication.dto.mgdto.MessAdditionDto;
import com.demo.jwt.jwtmybatisapplication.dto.mgdto.MessDisplayDto;
import com.demo.jwt.jwtmybatisapplication.dto.mgdto.MessOwnerDisplayInfoDto;
import com.demo.jwt.jwtmybatisapplication.service.mgservice.MessService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mess")
public class MessController {

    @Autowired
    private MessService messService;

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<MessDisplayDto> createMess(@RequestBody MessAdditionDto mess) {
        return ResponseEntity.ok().body(messService.createMess(mess));
    }

    @GetMapping
    @RolesAllowed({"ADMIN", "MESS_OWNER"})
    public ResponseEntity<List<MessDisplayDto>> getAllMess() {
        return ResponseEntity.ok().body(messService.getAllMess());
    }

    @GetMapping("{messId}/owners")
    @RolesAllowed({"ADMIN", "MESS_OWNER"})
    public ResponseEntity<MessOwnerDisplayInfoDto> getOwnerByMessId(@PathVariable Long messId) {
        MessOwnerDisplayInfoDto messOwner = messService.getOwnerByMessId(messId);
        return ResponseEntity.ok().body(messOwner);
    }

}
