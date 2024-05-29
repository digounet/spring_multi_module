package com.estudo.demoapplication.interfaces;

import com.estudo.demoapplication.models.Person;

import java.util.List;

public interface PersonServiceIntf {
    Person add(Person person);

    List<Person> listAll();

    Person findById(Long id);

    void delete(Long id);

    Person update(Person person);
}
