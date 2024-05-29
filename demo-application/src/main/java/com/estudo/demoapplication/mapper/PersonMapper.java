package com.estudo.demoapplication.mapper;

import com.estudo.demoapplication.models.Person;
import com.estudo.demodata.entities.PersonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper {
    PersonEntity toEntity(Person person);

    Person toModel(PersonEntity personEntity);
}
