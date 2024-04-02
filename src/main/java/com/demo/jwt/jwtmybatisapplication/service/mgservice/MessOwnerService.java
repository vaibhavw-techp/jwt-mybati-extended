package com.demo.jwt.jwtmybatisapplication.service.mgservice;

import com.demo.jwt.jwtmybatisapplication.dto.mgdto.MessOwnerAdditionDto;
import com.demo.jwt.jwtmybatisapplication.dto.mgdto.MessOwnerDisplayDto;
import com.demo.jwt.jwtmybatisapplication.mapstruct.mgmapstruct.MessOwnerMapper;
import com.demo.jwt.jwtmybatisapplication.model.mgmodel.MessOwnerEntity;
import com.demo.jwt.jwtmybatisapplication.repository.mgrepository.MessOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessOwnerService {

    @Autowired
    private MessOwnerRepository messOwnerRepository;

    @Autowired
    private MessOwnerMapper messOwnerMapper;


    public MessOwnerDisplayDto createMessOwner(MessOwnerAdditionDto messOwnerAdditionDto) {
        MessOwnerEntity messOwnerEntity = messOwnerMapper.mapMessOwnerAdditionDtoToMessOwnerEntity(messOwnerAdditionDto);
        return messOwnerMapper.mapMessOwnerEntityToMessOwnerDisplayDto(messOwnerEntity);
    }

    public List<MessOwnerDisplayDto> getAllMessOwners() {
        return messOwnerMapper.mapMessOwnerEntitiesToMessDisplayDtos(messOwnerRepository.findAll());
    }

}

