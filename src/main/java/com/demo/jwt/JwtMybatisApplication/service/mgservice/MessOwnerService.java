package com.demo.jwt.JwtMybatisApplication.service.mgservice;

import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerDisplayDto;
import com.demo.jwt.JwtMybatisApplication.mapstruct.mgmapstruct.MessOwnerMapper;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.MessOwnerEntity;
import com.demo.jwt.JwtMybatisApplication.repository.mgrepository.MessOwnerRepository;
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

