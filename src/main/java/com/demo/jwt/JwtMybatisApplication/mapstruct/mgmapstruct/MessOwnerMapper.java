package com.demo.jwt.JwtMybatisApplication.mapstruct.mgmapstruct;

import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerAdditionDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerDisplayDto;
import com.demo.jwt.JwtMybatisApplication.dto.mgdto.MessOwnerDisplayInfoDto;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.MessEntity;
import com.demo.jwt.JwtMybatisApplication.model.mgmodel.MessOwnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessOwnerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "messOwnerAdditionDto.name")
    @Mapping(target = "contactNumber", source = "messOwnerAdditionDto.contactNumber")
    MessOwnerEntity mapMessOwnerAdditionDtoToMessOwnerEntity(MessOwnerAdditionDto messOwnerAdditionDto);

    @Mapping(target = "id", source = "messOwnerEntity.id")
    @Mapping(target = "name", source = "messOwnerEntity.name")
    @Mapping(target = "contactNumber", source = "messOwnerEntity.contactNumber")
    @Mapping(target = "messId", source = "messOwnerEntity.id")
    MessOwnerDisplayDto mapMessOwnerEntityToMessOwnerDisplayDto(MessOwnerEntity messOwnerEntity);

    List<MessOwnerDisplayDto> mapMessOwnerEntitiesToMessDisplayDtos(List<MessOwnerEntity> messOwnerEntities);
}
