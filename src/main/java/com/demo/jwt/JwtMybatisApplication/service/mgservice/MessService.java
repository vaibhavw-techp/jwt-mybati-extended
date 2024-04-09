package com.demo.jwt.JwtMybatisApplication.service.mgservice;

import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerDisplayInfoDto;
import com.demo.jwt.JwtMybatisApplication.mapstruct.mgmapstruct.MessMapper;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.MessEntity;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.MessOwnerEntity;
import com.demo.jwt.JwtMybatisApplication.repository.mgrepository.MessOwnerRepository;
import com.demo.jwt.JwtMybatisApplication.repository.mgrepository.MessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessService {

    @Autowired
    private MessRepository messRepository;

    @Autowired
    private MessMapper messMapper;

    @Autowired
    private MessOwnerRepository messOwnerRepository;

    public MessDisplayDto createMess(MessAdditionDto messAdditionDto) {
        MessEntity mess = messMapper.mapMessAdditionDtoToMessEntity(messAdditionDto);
        messRepository.save(mess);
        return messMapper.mapMessEntityToMessDisplayDto(mess);
    }

    public List<MessDisplayDto> getAllMess() {
        return messMapper.mapMessEntitiesToMessDisplayDtos(messRepository.findAll());
    }

    public MessOwnerDisplayInfoDto getOwnerByMessId(Long id) {
        MessEntity messEntity = messRepository.findMessById(id);
        List<MessOwnerEntity> messOwnerEntities = messOwnerRepository.findOwnerByMessId(id);




        return messMapper.mapToMessOwnerDisplayInfoDto(messEntity, messOwnerEntities);
    }

}

