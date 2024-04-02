package com.demo.jwt.jwtmybatisapplication.mapstruct.mgmapstruct;

import com.demo.jwt.jwtmybatisapplication.dto.mgdto.MessAdditionDto;
import com.demo.jwt.jwtmybatisapplication.dto.mgdto.MessDisplayDto;
import com.demo.jwt.jwtmybatisapplication.dto.mgdto.MessOwnerDisplayInfoDto;
import com.demo.jwt.jwtmybatisapplication.model.mgmodel.MessEntity;
import com.demo.jwt.jwtmybatisapplication.model.mgmodel.MessOwnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = MessOwnerMapper.class)
public interface MessMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "messAdditionDto.name")
    @Mapping(target = "capacity", source = "messAdditionDto.capacity")
    @Mapping(target = "location", source = "messAdditionDto.location")
    MessEntity mapMessAdditionDtoToMessEntity(MessAdditionDto messAdditionDto);

    @Mapping(target = "id", source = "mess.id")
    @Mapping(target = "name", source = "mess.name")
    @Mapping(target = "capacity", source = "mess.capacity")
    @Mapping(target = "location", source = "mess.location")
    MessDisplayDto mapMessEntityToMessDisplayDto(MessEntity mess);

    @Mappings({
            @Mapping(target = "name", source = "messEntity.name"),
            @Mapping(target = "owner", source = "messOwnerEntities")
    })
    MessOwnerDisplayInfoDto mapToMessOwnerDisplayInfoDto(MessEntity messEntity, List<MessOwnerEntity> messOwnerEntities);

    List<MessDisplayDto> mapMessEntitiesToMessDisplayDtos(List<MessEntity> messEntities);

}
