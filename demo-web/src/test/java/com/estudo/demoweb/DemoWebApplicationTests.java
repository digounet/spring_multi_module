package com.estudo.demoweb;

import com.estudo.demoapplication.interfaces.PersonServiceIntf;
import com.estudo.demodata.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DemoWebApplicationTests {

	@Autowired
	private PersonServiceIntf personService;

	@Autowired
	private PersonRepository personRepository;

	@Test
	void contextLoads() {
		assertNotNull(personService, "PersonServiceIntf should be created");
		assertNotNull(personRepository, "PersonRepository should be created");
	}

}
