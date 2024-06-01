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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    void shouldListAllPersons() {
        var personEntity1 = PersonEntity.builder()
                .id(1L)
                .name("John Doe")
                .gender(GenderEnum.MALE)
                .build();

        var personEntity2 = PersonEntity.builder()
                .id(2L)
                .name("Jane Doe")
                .gender(GenderEnum.FEMALE)
                .build();

        when(personRepository.findAll()).thenReturn(List.of(personEntity1, personEntity2));

        List<Person> result = personService.listAll();

        assertEquals(2, result.size());
        assertEquals(personEntity1.getId(), result.get(0).getId());
        assertEquals(personEntity2.getId(), result.get(1).getId());
    }

    @Test
    void shouldReturnNullWhenNoPersons() {
        when(personRepository.findAll()).thenReturn(List.of());

        List<Person> result = personService.listAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldDeletePerson() {
        doNothing().when(personRepository).deleteById(1L);

        personService.delete(1L);

        verify(personRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldUpdatePersonWhenExists() {
        var person = Person.builder()
                .id(1L)
                .name("John Doe Updated")
                .gender(GenderEnum.MALE)
                .build();

        var personEntity = PersonEntity.builder()
                .id(1L)
                .name("John Doe Updated")
                .gender(GenderEnum.MALE)
                .build();

        when(personRepository.findById(1L)).thenReturn(Optional.of(personEntity));
        when(personRepository.save(any(PersonEntity.class))).thenReturn(personEntity);

        Person result = personService.update(person);

        assertEquals(person.getId(), result.getId());
        assertEquals(person.getName(), result.getName());
        assertEquals(person.getGender(), result.getGender());
    }

    @Test
    void shouldReturnNullWhenUpdatingNonExistingPerson() {
        var person = Person.builder()
                .id(1L)
                .name("John Doe Updated")
                .gender(GenderEnum.MALE)
                .build();

        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        Person result = personService.update(person);

        assertNull(result);
    }
}