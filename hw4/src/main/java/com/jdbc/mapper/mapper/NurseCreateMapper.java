package com.jdbc.mapper.mapper;

import com.jdbc.dto.nurse.NurseCreateDto;
import com.jdbc.entity.Nurse;
import com.jdbc.mapper.Mapper;

public class NurseCreateMapper implements Mapper<NurseCreateDto, Nurse> {
    @Override
    public Nurse map(NurseCreateDto object) {
        Nurse nurse = new Nurse();
        copy(object, nurse);
        return nurse;
    }

    private void copy(NurseCreateDto object, Nurse nurse) {
        nurse.setFirstName(object.getFirstName());
        nurse.setPosition(object.getPosition());
        nurse.setBlockcode(object.getBlockcode());
        nurse.setBlockfloor(object.getBlockfloor());
        nurse.setLastName(object.getLastName());
        nurse.setSurName(object.getSurName());
    }

}
