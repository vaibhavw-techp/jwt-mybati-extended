package com.demo.jwt.jwtmybatisapplication.mapstruct.mgmapstruct;

import com.demo.jwt.jwtmybatisapplication.dto.mgdto.HostelAdditionDto;
import com.demo.jwt.jwtmybatisapplication.dto.mgdto.HostelDisplayDto;
import com.demo.jwt.jwtmybatisapplication.model.mgmodel.HostelEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HostelMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "hostelAdditionDto.name")
    @Mapping(target = "address", source = "hostelAdditionDto.address")
    @Mapping(target = "capacity", source = "hostelAdditionDto.capacity")
    @Mapping(target = "numRooms", source = "hostelAdditionDto.numRooms")
    HostelEntity mapHostelAdditionDtoToHostelEntity(HostelAdditionDto hostelAdditionDto);

    @Mapping(target = "name", source = "hostelEntity.name")
    @Mapping(target = "address", source = "hostelEntity.address")
    @Mapping(target = "capacity", source = "hostelEntity.capacity")
    @Mapping(target = "numRooms", source = "hostelEntity.numRooms")
    HostelDisplayDto mapHostelEntityToHostelDisplayDto(HostelEntity hostelEntity);

    List<HostelDisplayDto> mapHostelEntitiesToHostelDisplayDtos(List<HostelEntity> hostelEntities);
}
