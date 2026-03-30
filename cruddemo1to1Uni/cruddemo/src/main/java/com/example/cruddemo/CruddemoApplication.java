package com.example.cruddemo;

import com.example.cruddemo.dao.AppDAO;
import com.example.cruddemo.entity.Instructor;
import com.example.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			createInstructor(appDAO);
			findInstructore(appDAO);
			deleteInstructor(appDAO);
		};
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		appDAO.deleteInstructorById(theId);
	}

	private void findInstructore(AppDAO appDAO) {
		int theId = 1;
		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println(instructor);
		System.out.println(instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor instructor = new Instructor("Lilly", "Potter", "lpotter@hogwards.com");
		InstructorDetail instructorDetail = new InstructorDetail("http://www.some/youtube.com", "some hobby");

		instructor.setInstructorDetail(instructorDetail);
		appDAO.save(instructor);
	}
}
