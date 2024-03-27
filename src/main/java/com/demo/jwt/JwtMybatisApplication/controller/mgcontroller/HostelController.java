package com.demo.jwt.JwtMybatisApplication.controller.mgcontroller;

import com.demo.jwt.JwtMybatisApplication.dto.mgdto.HostelAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.HostelDisplayDto;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.HostelEntity;
import com.demo.jwt.JwtMybatisApplication.service.mgservice.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hostels")
public class HostelController {

    @Autowired
    private HostelService hostelService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<HostelDisplayDto> createHostel(@RequestBody HostelAdditionDto hostel) {
        return ResponseEntity.ok().body(hostelService.createHostel(hostel));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MESS_OWNER')")
    public ResponseEntity<List<HostelDisplayDto>> getAllHostels() {
        List<HostelDisplayDto> hostels = hostelService.getAllHostels();
        return ResponseEntity.ok().body(hostels);
    }

}
