package com.estudo.demoapplication.services;

import com.estudo.demoapplication.interfaces.PersonServiceIntf;
import com.estudo.demoapplication.mapper.PersonMapper;
import com.estudo.demoapplication.models.Person;
import com.estudo.demodata.entities.PersonEntity;
import com.estudo.demodata.repository.PersonRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements PersonServiceIntf {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper = Mappers.getMapper(PersonMapper.class);

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person add(Person person) {
        PersonEntity personEntity = personMapper.toEntity(person);
        personEntity = personRepository.save(personEntity);
        return personMapper.toModel(personEntity);
    }

    @Override
    public List<Person> listAll() {
        return personRepository.findAll().stream()
                .map(personMapper::toModel)
                .toList();
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id)
                .map(personMapper::toModel)
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person update(Person person) {

        var existingPersonOptional = personRepository.findById(person.getId());
        if (existingPersonOptional.isEmpty()) {
            return null;
        }

        var personEntity = personMapper.toEntity(person);
        personEntity = personRepository.save(personEntity);

        return personMapper.toModel(personEntity);
    }
}