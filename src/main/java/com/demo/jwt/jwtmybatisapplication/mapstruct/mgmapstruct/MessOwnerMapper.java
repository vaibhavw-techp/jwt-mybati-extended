package com.demo.jwt.jwtmybatisapplication.mapstruct.mgmapstruct;

import com.demo.jwt.jwtmybatisapplication.dto.mgdto.MessOwnerAdditionDto;
import com.demo.jwt.jwtmybatisapplication.dto.mgdto.MessOwnerDisplayDto;
import com.demo.jwt.jwtmybatisapplication.model.mgmodel.MessOwnerEntity;
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
