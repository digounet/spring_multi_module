package com.estudo.demoweb.controllers;

import com.estudo.demoapplication.interfaces.PersonServiceIntf;
import com.estudo.demoapplication.models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonServiceIntf personService;

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person();
        person.setId(1L);
        person.setName("John Doe");
    }

    @Test
    void createPersonReturnsCreated() throws Exception {
        when(personService.add(any(Person.class))).thenReturn(person);

        mockMvc.perform(post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllPersonsReturnsOk() throws Exception {
        List<Person> persons = Collections.singletonList(person);
        when(personService.listAll()).thenReturn(persons);

        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk());
    }

    @Test
    void getPersonByIdReturnsOk() throws Exception {
        when(personService.findById(anyLong())).thenReturn(person);

        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getPersonByIdReturnsNotFound() throws Exception {
        when(personService.findById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/persons/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatePersonReturnsOk() throws Exception {
        when(personService.update(any(Person.class))).thenReturn(person);

        mockMvc.perform(put("/persons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk());
    }

    @Test
    void deletePersonReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/persons/1"))
                .andExpect(status().isNoContent());
    }
}