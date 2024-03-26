package com.demo.jwt.JwtMybatisApplication.service.mgservice;

import com.demo.jwt.JwtMybatisApplication.dto.mgdto.HostelAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.HostelDisplayDto;
import com.demo.jwt.JwtMybatisApplication.mapstruct.mgmapstruct.HostelMapper;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.HostelEntity;
import com.demo.jwt.JwtMybatisApplication.repository.mgrepository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostelService {

    @Autowired
    private HostelRepository hostelRepository;

    @Autowired
    private HostelMapper hostelMapper;

    public HostelDisplayDto createHostel(HostelAdditionDto hostel) {
        HostelEntity hostelEntity = hostelMapper.mapHostelAdditionDtoToHostelEntity(hostel);
        hostelRepository.save(hostelEntity);
        return hostelMapper.mapHostelEntityToHostelDisplayDto(hostelEntity);
    }

    public List<HostelDisplayDto> getAllHostels() {
        return hostelMapper.mapHostelEntitiesToHostelDisplayDtos(hostelRepository.findAll());
    }

}