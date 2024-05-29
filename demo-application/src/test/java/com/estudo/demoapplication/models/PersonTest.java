package com.estudo.demoapplication.models;

import com.estudo.demoapplication.mapper.PersonMapper;
import com.estudo.demoapplication.models.Person;
import com.estudo.democore.enums.GenderEnum;
import com.estudo.demodata.entities.PersonEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class PersonMapperTest {

    private PersonMapper personMapper;

    @BeforeEach
    void setUp() {
        personMapper = Mappers.getMapper(PersonMapper.class);
    }

    @Test
    void shouldMapPersonToPersonEntity() {
        var person = Person.builder()
                .id(1L)
                .name("John Doe")
                .gender(GenderEnum.MALE)
                .build();

        PersonEntity personEntity = personMapper.toEntity(person);

        assertNotNull(personEntity);
        assertEquals(person.getId(), personEntity.getId());
        assertEquals(person.getName(), personEntity.getName());
        assertEquals(person.getGender(), personEntity.getGender());
    }

    @Test
    void shouldMapPersonEntityToPerson() {
        var personEntity = PersonEntity.builder()
                .id(1L)
                .name("John Doe")
                .gender(GenderEnum.MALE)
                .build();

        Person person = personMapper.toModel(personEntity);

        assertNotNull(person);
        assertEquals(personEntity.getId(), person.getId());
        assertEquals(personEntity.getName(), person.getName());
        assertEquals(personEntity.getGender(), person.getGender());
    }

    @Test
    void shouldHandleNullValuesWhenMappingToPersonEntity() {
        var person = Person.builder().build();

        PersonEntity personEntity = personMapper.toEntity(person);

        assertNotNull(personEntity);
        assertNull(personEntity.getId());
        assertNull(personEntity.getName());
        assertNull(personEntity.getGender());
    }

    @Test
    void shouldHandleNullValuesWhenMappingToPerson() {
        PersonEntity personEntity = new PersonEntity();

        Person person = personMapper.toModel(personEntity);

        assertNotNull(person);
        assertNull(person.getId());
        assertNull(person.getName());
        assertNull(person.getGender());
    }
}