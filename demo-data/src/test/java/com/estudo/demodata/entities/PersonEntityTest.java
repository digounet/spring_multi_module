package com.estudo.demodata.entities;

import com.estudo.democore.enums.GenderEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonEntityTest {

    @Test
    void builderShouldSetIdNameAndGender() {
        var person = PersonEntity.builder()
                .id(2L)
                .name("John Doe")
                .gender(GenderEnum.MALE)
                .build();

        assertEquals(2, person.getId());
        assertEquals("John Doe", person.getName());
        assertEquals(GenderEnum.MALE, person.getGender());
    }

    @Test
    void builderShouldHandleNullNameAndGender() {
        var person = PersonEntity.builder()
                .id(3L)
                .name(null)
                .gender(null)
                .build();

        assertEquals(3, person.getId());
        assertNull(person.getName());
        assertNull(person.getGender());
    }

    @Test
    void builderShouldHandleNegativeId() {
        var person = PersonEntity.builder()
                .id(-1L)
                .name("Negative Id")
                .gender(GenderEnum.FEMALE)
                .build();

        assertEquals(-1, person.getId());
        assertEquals("Negative Id", person.getName());
        assertEquals(GenderEnum.FEMALE, person.getGender());
    }
}