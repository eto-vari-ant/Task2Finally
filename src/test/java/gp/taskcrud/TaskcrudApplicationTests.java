package gp.taskcrud;

import gp.taskcrud.model.Developer;
import gp.taskcrud.service.DeveloperService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskcrudApplicationTests {

	@Autowired
	DeveloperService developerService;

	@Test
	@DisplayName("Create Developer")
	void createDev1(){
		Developer dev1 = new Developer();
		dev1.setName("John");
		dev1.setEmail("john@mail.ru");
		developerService.saveOrUpdate(dev1);
	}

	@Test
	@DisplayName("Get List of developers first time")
	void getList1time(){
		Developer dev1 = new Developer();
		dev1.setName("John");
		dev1.setEmail("john@mail.ru");
		developerService.saveOrUpdate(dev1);
		developerService.findAll();
	}


	@Test
	@DisplayName("Try to create developer with not valid name (starts with number)")
	void createNotValidName(){
		Developer dev2 = new Developer();
		dev2.setName("44Bob");
		dev2.setEmail("bob@mail.ru");
		developerService.saveOrUpdate(dev2);
	}


	@Test
	@DisplayName("Try to create developer with not unique email (john@mail.ru again)")
	void createNotUniqueEmail(){
		Developer dev1 = new Developer();
		dev1.setName("John");
		dev1.setEmail("john@mail.ru");
		developerService.saveOrUpdate(dev1);
		Developer dev3 = new Developer();
		dev3.setName("Bob");
		dev3.setEmail("john@mail.ru");
		developerService.saveOrUpdate(dev3);
	}

	@Test
	@DisplayName("Create valid developer again(he is the second)")
	void createDeveloperAgain(){
		Developer dev4 = new Developer();
		dev4.setName("Bob");
		dev4.setEmail("bob@mail.ru");
		developerService.saveOrUpdate(dev4);
	}

	@Test
	@DisplayName("Change the name of the first developer in DB")
	void updateFirstDeveloper(){
		Developer dev1 = new Developer();
		dev1.setName("John");
		dev1.setEmail("john@mail.ru");
		developerService.saveOrUpdate(dev1);
		Developer dev5 = developerService.findDeveloperById((long)1);
		dev5.setName("Andrew");
		developerService.saveOrUpdate(dev5);
	}


	@Test
	@DisplayName("Delete the first developer")
	void deleteFirstDeveloper(){
		Developer dev1 = new Developer();
		dev1.setName("John");
		dev1.setEmail("john@mail.ru");
		developerService.saveOrUpdate(dev1);
		developerService.deleteById((long)1);
	}

}
