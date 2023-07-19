package com.jdbc.mapper.mapper;

import com.jdbc.dto.doctor.DoctorEditDto;
import com.jdbc.dto.nurse.NurseEditDto;
import com.jdbc.entity.Doctor;
import com.jdbc.entity.Nurse;
import com.jdbc.mapper.Mapper;


public class NurseEditMapper implements Mapper<NurseEditDto, Nurse> {

    @Override
    public Nurse map(NurseEditDto object) {
        Nurse nurse = new Nurse();
        copy(object, nurse);
        return nurse;
    }

    private void copy(NurseEditDto object, Nurse nurse) {
        nurse.setId(object.getUuid());
        nurse.setFirstName(object.getFirstName());
        nurse.setPosition(object.getPosition());
        nurse.setLastName(object.getLastName());
        nurse.setSurName(object.getSurName());
        nurse.setBlockfloor(object.getBlockfloor());
        nurse.setBlockcode(object.getBlockcode());
    }

}
