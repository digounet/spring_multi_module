package com.estudo.democore.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GenderEnumTest {

    @Test
    void shouldReturnMaleWhenValueIsMale() {
        assertEquals(GenderEnum.MALE, GenderEnum.valueOf("MALE"));
    }

    @Test
    void shouldReturnFemaleWhenValueIsFemale() {
        assertEquals(GenderEnum.FEMALE, GenderEnum.valueOf("FEMALE"));
    }

    @Test
    void shouldReturnOtherWhenValueIsOther() {
        assertEquals(GenderEnum.OTHER, GenderEnum.valueOf("OTHER"));
    }

    @Test
    void shouldThrowExceptionWhenValueIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> GenderEnum.valueOf("INVALID"));
    }
}