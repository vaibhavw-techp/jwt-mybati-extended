package com.demo.jwt.jwtmybatisapplication.controller.mgcontroller;

import com.demo.jwt.jwtmybatisapplication.dto.mgdto.HostelAdditionDto;
import com.demo.jwt.jwtmybatisapplication.dto.mgdto.HostelDisplayDto;
import com.demo.jwt.jwtmybatisapplication.service.mgservice.HostelService;
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
@RequestMapping("/hostels")
public class HostelController {

    @Autowired
    private HostelService hostelService;

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<HostelDisplayDto> createHostel(@RequestBody HostelAdditionDto hostel) {
        return ResponseEntity.ok().body(hostelService.createHostel(hostel));
    }

    @GetMapping
    @RolesAllowed({"ADMIN", "MESS_OWNER"})
    public ResponseEntity<List<HostelDisplayDto>> getAllHostels() {
        List<HostelDisplayDto> hostels = hostelService.getAllHostels();
        return ResponseEntity.ok().body(hostels);
    }

}
