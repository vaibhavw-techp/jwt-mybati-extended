package com.demo.jwt.JwtMybatisApplication.service.mgservice;

import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerDisplayInfoDto;
import com.demo.jwt.JwtMybatisApplication.exceptions.UnauthorizedAccessException;
import com.demo.jwt.JwtMybatisApplication.mapstruct.mgmapstruct.MessMapper;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.MessEntity;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.MessOwnerEntity;
import com.demo.jwt.JwtMybatisApplication.repository.mgrepository.MessOwnerRepository;
import com.demo.jwt.JwtMybatisApplication.repository.mgrepository.MessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
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

    public MessOwnerDisplayInfoDto getOwnerByMessId(Long id, Principal principal) {
        MessEntity messEntity = messRepository.findMessById(id);
        List<MessOwnerEntity> messOwnerEntities = messOwnerRepository.findOwnerByMessId(id);

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) principal;
        Jwt jwt = jwtAuthenticationToken.getToken();
        String role = (String) jwt.getClaims().get("Role");

        if (role.equals("ROLE_MESS_OWNER")) {
            Long messOwnerId = Long.parseLong(jwt.getClaims().get("assc_id").toString());
            if (id == messOwnerId) {
                return messMapper.mapToMessOwnerDisplayInfoDto(messEntity, messOwnerEntities);
            } else {
                throw new UnauthorizedAccessException("Mess Owner");
            }
        } else {
            return messMapper.mapToMessOwnerDisplayInfoDto(messEntity, messOwnerEntities);
        }
    }

}

