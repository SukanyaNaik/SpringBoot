package com.example.cruddemo;

import com.example.cruddemo.dao.StudentDAO;
import com.example.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			Student student = createStudent(studentDAO);
			readStudent(studentDAO, student);
			readAll(studentDAO);
			//findByLastName(studentDAO, "Doe");
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			//deleteAll(studentDAO);
		};
	}

	public Student createStudent(StudentDAO studentDAO) {
		Student student = new Student("John", "Doe", "John.Doe@example.com");
		studentDAO.save(student);
		System.out.println("Saved student: Generated id: " + student.getStudent_id());
		return student;
	}

	public void readStudent(StudentDAO studentDAO, Student student) {
		Student st = studentDAO.findById(student.getStudent_id());
		System.out.println("Saved student: id: " + st.getStudent_id());
	}

	public void readAll(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();
		for(Student student : students) {
			System.out.println(student);
		}
	}

	public void findByLastName(StudentDAO studentDAO, String lastName) {
		List<Student> students = studentDAO.findByLastName(lastName);
		for(Student student : students) {
			System.out.println(student);
		}
	}

	public void updateStudent(StudentDAO studentDAO){
		Student student = studentDAO.findById(1);
		student.setLastName("Smith");
		studentDAO.update(student);
		System.out.println("Updated student - " + student);
	}

	public void deleteStudent(StudentDAO studentDAO) {
		studentDAO.delete(4);
	}

	public void deleteAll(StudentDAO studentDAO) {
		int no = studentDAO.deleteAll();
		System.out.println(no + " records deleted.");
	}
}
