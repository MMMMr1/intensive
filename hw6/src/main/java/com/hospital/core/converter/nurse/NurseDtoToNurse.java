package com.hospital.core.converter.nurse;

import com.hospital.core.dto.NurseDto;
import com.hospital.entity.Nurse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NurseDtoToNurse implements Converter<NurseDto, Nurse> {
    @Override
    public Nurse convert(NurseDto object) {
        Nurse nurse = new Nurse();
        nurse.setFirstName(object.getFirstName());
        nurse.setPosition(object.getPosition());
        nurse.setBlockcode(object.getBlockcode());
        nurse.setBlockfloor(object.getBlockfloor());
        nurse.setLastName(object.getLastName());
        nurse.setSurName(object.getSurName());
        return nurse;
    }
}
