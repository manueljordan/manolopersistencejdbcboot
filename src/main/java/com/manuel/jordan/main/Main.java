package com.manuel.jordan.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.manuel.jordan.domain.Person;
import com.manuel.jordan.model.service.PersonService;
import com.manuel.jordan.model.repository.PersonRepository;

/**
 * <p>Simple Main class.</p>
 * 
 * @author Manuel Jordan - dr_pompeii
 * 
 */
@SuppressWarnings("unused")
public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args){
		
		String classpath = "classpath:/com/manuel/jordan/springframework/beans/central/springframework-central.xml";
		
		ApplicationContext context = new ClassPathXmlApplicationContext(classpath);
		
		Person person01 = new Person();
		person01.setId("001");
		person01.setFirstName("manuel");
		person01.setLastName("jordan");
		
		Person person02 = new Person();
		person02.setId("002");
		person02.setFirstName("leonardo");
		person02.setLastName("jordan");
		
		PersonRepository personCustomRepository = context.getBean(PersonRepository.class);
		
		PersonService personServiceImplVersion01 = (PersonService) context.getBean("personService01");
		
		PersonService personServiceImplVersion02 = context.getBean("personService02", PersonService.class );
		
		logger.info("Inserting two persons");
		personServiceImplVersion01.insertPerson(person01);
		personServiceImplVersion02.insertPerson(person02);
		
		logger.info("Retrieving all persons");
		logger.info("{}",personServiceImplVersion01.getAllPerson().toString());
		logger.info("{}",personServiceImplVersion02.getAllPerson().toString());
		
		logger.info("Retrieving one person by id");
		logger.info("{}", personServiceImplVersion01.getPersonById("001").toString());
		logger.info("{}", personServiceImplVersion02.getPersonById("002").toString());
		
		logger.info("Updating two persons");
		person01.setFirstName("Manuel");
		person01.setLastName("Jordan");
		
		person02.setFirstName("Leonardo");
		person02.setLastName("Jordan");
		
		personServiceImplVersion01.updatePerson(person01);
		personServiceImplVersion02.updatePerson(person02);
		
		logger.info("Retrieving one person by id");
		logger.info("{}", personServiceImplVersion01.getPersonById("001").toString());
		logger.info("{}", personServiceImplVersion02.getPersonById("002").toString());
		
		logger.info("Deleting one person by id");
		personServiceImplVersion01.deletePersonById("001");
		personServiceImplVersion02.deletePersonById("002");
		
		logger.info("Retrieving all persons");
		logger.info("{}",personServiceImplVersion01.getAllPerson().toString());
		logger.info("{}",personServiceImplVersion02.getAllPerson().toString());
		
		logger.info("Retrieving one person by id");
		try{
			logger.info("{}", personServiceImplVersion01.getPersonById("001"));	
		}
		catch(Exception e){
			logger.error("{}", e.getMessage());
		}
		try{
			logger.info("{}", personServiceImplVersion02.getPersonById("002"));	
		}
		catch(Exception e){
			logger.error("{}", e.getMessage());
		}
		
		((ClassPathXmlApplicationContext) context).close();
		
	}
	
}
