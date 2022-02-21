package gp.taskcrud;

import gp.taskcrud.exception.DeveloperNotFoundException;
import gp.taskcrud.model.Developer;
import gp.taskcrud.repository.DeveloperRepository;
import gp.taskcrud.service.DeveloperService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class TaskcrudApplicationTests {

	@Autowired
	DeveloperService service;

	@Autowired
	DeveloperRepository repository;

	@Test
	@DisplayName("The method to save the developer should save the developer to the database if the name and email are correct.")
	void methodToSaveDeveloperShouldSaveDeveloperToDatabaseIfNameAndEmailAreCorrect() {
		Developer developer = new Developer();

		developer.setName("Varya Petrova");
		developer.setEmail("varya.petrova@gmail.com");

		repository.save(developer);

		Developer developerInDB = repository.getById(developer.getId());

		Assertions.assertNotNull(developerInDB);
		Assertions.assertEquals(developerInDB.getName(), developer.getName());
		Assertions.assertEquals(developerInDB.getEmail(), developer.getEmail());
	}

	@Test
	@DisplayName("The method for getting a developer by id must return a previously saved developer with that id.")
	void methodForGettingDeveloperByIdMustReturnPreviouslySavedDeveloperWithThatId() {
		Developer developer = new Developer();

		developer.setName("Varya Petrova");
		developer.setEmail("varya.petrova@gmail.com");

		repository.save(developer);

		Developer developerInDB = service.findDeveloperById(developer.getId());

		Assertions.assertNotNull(developerInDB);
		Assertions.assertEquals(developerInDB.getName(), developer.getName());
		Assertions.assertEquals(developerInDB.getEmail(), developer.getEmail());
	}

	@Test
	@DisplayName("The method to update the developer must update the previously saved developer in the database if the new name and email are correct.")
	void methodToUpdateDeveloperMustUpdatePreviouslySavedDeveloperInDatabaseIfNewNameAndEmailAreCorrect() {
		Developer developer = new Developer();

		developer.setName("Varya Letrova");
		developer.setEmail("varya.letrova@gmail.com");

		repository.save(developer);

		Developer updatedDeveloper = new Developer();
		updatedDeveloper.setId(developer.getId());
		updatedDeveloper.setName("Varya Petrova");
		updatedDeveloper.setEmail("varya.petrova@gmail.com");

		service.saveOrUpdate(updatedDeveloper);

		Developer developerInDB = repository.getById(developer.getId());

		Assertions.assertNotNull(developerInDB);
		Assertions.assertEquals(developerInDB.getName(), updatedDeveloper.getName());
		Assertions.assertEquals(developerInDB.getEmail(), updatedDeveloper.getEmail());
	}



	@Test
	@DisplayName("The method to get list of the developers must return all the developers from the database")
	void methodToGetListOfDevelopersMustReturnAllTheDevelopersOfDB() {
		Developer developer = new Developer();

		developer.setName("Varya Petrova");
		developer.setEmail("varya.petrova@gmail.com");

		repository.save(developer);

		Developer developer1 = new Developer();

		developer1.setName("John Johnson");
		developer1.setEmail("john.johnson@gmail.com");

		repository.save(developer1);

		Developer developer2 = new Developer();
		developer2.setName("Bob Bobson");
		developer2.setEmail("bob.bobson@gmail.com");

		repository.save(developer2);

		List<Developer> listDevs = new ArrayList<>();
		listDevs.add(developer);
		listDevs.add(developer1);
		listDevs.add(developer2);

		List<Developer> listDevsFromDB = service.findAll();

		Assertions.assertNotNull(listDevsFromDB);
		Assertions.assertEquals(listDevs, listDevsFromDB);
	}


	@Test
	@DisplayName("The method to delete the developer must delete developer from the database and return false from the method existsById")
	void methodToDeleteDeveloperMustDeleteDeveloperFromDBAndReturnFalse() {
		Developer developer = new Developer();

		developer.setName("Varya Petrova");
		developer.setEmail("varya.petrova@gmail.com");

		repository.save(developer);

		Long id = developer.getId();

		service.deleteById(id);

		Assertions.assertFalse(repository.existsById(id));
	}


	@Test
	@DisplayName("The method to delete the developer must delete developer from the database and throw DeveloperNotFoundException")
	void methodToDeleteDeveloperMustDeleteDeveloperFromDBAndThrowDeveloperNotFoundException() {
		Developer developer = new Developer();

		developer.setName("Varya Petrova");
		developer.setEmail("varya.petrova@gmail.com");

		repository.save(developer);

		Long id = developer.getId();

		service.deleteById(id);

		Assertions.assertThrows(DeveloperNotFoundException.class,()->service.findDeveloperById(id));
	}


	@Test
	@DisplayName("The method to save the developer with not valid name (starts with number) must throw the ConstraintViolationException")
	void methodToSaveTheDeveloperWithNotValidNameMustThrowConstraintViolationException() {
		Developer developer = new Developer();

		developer.setName("5arya Petrova");
		developer.setEmail("varya.petrova@gmail.com");

		Assertions.assertThrows(ConstraintViolationException.class,()->service.saveOrUpdate(developer));
	}

	@Test
	@DisplayName("The method to save the developer with not unique email must throw the DataIntegrityViolationException")
	void methodToSaveTheDeveloperWithNotUniqueEmailMustThrowDataIntegrityViolationException() {
		Developer developer = new Developer();

		developer.setName("Varya Petrova");
		developer.setEmail("varya.petrova@gmail.com");

		repository.save(developer);

		Developer developerWithTheSameEmail = new Developer();

		developerWithTheSameEmail.setName("Katya Ivanova");
		developerWithTheSameEmail.setEmail("varya.petrova@gmail.com");

		Assertions.assertThrows(DataIntegrityViolationException.class,()->service.saveOrUpdate(developerWithTheSameEmail));
	}


}
