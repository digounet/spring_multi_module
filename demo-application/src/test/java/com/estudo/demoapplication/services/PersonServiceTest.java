package com.estudo.demoapplication.services;

import com.estudo.demoapplication.models.Person;
import com.estudo.democore.enums.GenderEnum;
import com.estudo.demodata.entities.PersonEntity;
import com.estudo.demodata.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddPerson() {
        var person = Person.builder()
                .id(1L)
                .name("John Doe")
                .gender(GenderEnum.MALE)
                .build();

        var personEntity = PersonEntity.builder()
                .id(1L)
                .name("John Doe")
                .gender(GenderEnum.MALE)
                .build();

        when(personRepository.save(any(PersonEntity.class))).thenReturn(personEntity);

        Person result = personService.add(person);

        assertEquals(person.getId(), result.getId());
        assertEquals(person.getName(), result.getName());
        assertEquals(person.getGender(), result.getGender());
    }

    @Test
    void shouldFindPersonById() {
        var personEntity = PersonEntity.builder()
                .id(1L)
                .name("John Doe")
                .gender(GenderEnum.MALE)
                .build();

        when(personRepository.findById(1L)).thenReturn(Optional.of(personEntity));

        Person result = personService.findById(1L);

        assertEquals(personEntity.getId(), result.getId());
        assertEquals(personEntity.getName(), result.getName());
        assertEquals(personEntity.getGender(), result.getGender());
    }

    @Test
    void shouldReturnNullWhenPersonNotFound() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        Person result = personService.findById(1L);

        assertNull(result);
    }

    @Test
    void shouldDeletePerson() {
        doNothing().when(personRepository).deleteById(1L);

        personService.delete(1L);

        verify(personRepository, times(1)).deleteById(1L);
    }
}