package com.jdbc.mapper.mapper;

import com.jdbc.dto.nurse.NurseReadDto;
import com.jdbc.entity.Nurse;
import com.jdbc.mapper.Mapper;


public class NurseReadMapper implements Mapper<Nurse, NurseReadDto> {

    @Override
    public NurseReadDto map(Nurse object) {
         return new NurseReadDto(
                object.getId(),
                 object.getLastName(),
                 object.getFirstName(),
                 object.getSurName(),
                 object.getPosition(),
                 object.getBlockfloor(),
                 object.getBlockcode(),
                 object.getDtCreated(),
                 object.getDtUpdated()
        );
    }
}
