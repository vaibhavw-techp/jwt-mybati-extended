package com.demo.jwt.JwtMybatisApplication.controller.mgcontroller;

import com.demo.jwt.JwtMybatisApplication.dto.mgdto.HostelAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.HostelDisplayDto;
import com.demo.jwt.JwtMybatisApplication.service.mgservice.HostelService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hostels")
public class HostelController {

    @Autowired
    private HostelService hostelService;

    @PostMapping
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<HostelDisplayDto> createHostel(@RequestBody HostelAdditionDto hostel) {
        return ResponseEntity.ok().body(hostelService.createHostel(hostel));
    }

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_MESS_OWNER"})
    public ResponseEntity<List<HostelDisplayDto>> getAllHostels() {
        List<HostelDisplayDto> hostels = hostelService.getAllHostels();
        return ResponseEntity.ok().body(hostels);
    }

}