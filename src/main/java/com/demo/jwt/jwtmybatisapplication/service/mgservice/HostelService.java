package com.demo.jwt.jwtmybatisapplication.service.mgservice;

import com.demo.jwt.jwtmybatisapplication.dto.mgdto.HostelAdditionDto;
import com.demo.jwt.jwtmybatisapplication.dto.mgdto.HostelDisplayDto;
import com.demo.jwt.jwtmybatisapplication.mapstruct.mgmapstruct.HostelMapper;
import com.demo.jwt.jwtmybatisapplication.model.mgmodel.HostelEntity;
import com.demo.jwt.jwtmybatisapplication.repository.mgrepository.HostelRepository;
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
