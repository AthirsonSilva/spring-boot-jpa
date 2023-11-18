package com.mongodb.mongodb;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.mongodb.Student.StudentRepository;
import com.mongodb.mongodb.Student.Models.Address;
import com.mongodb.mongodb.Student.Models.Gender;
import com.mongodb.mongodb.Student.Models.Student;

@SpringBootApplication
public class SpringMongoDBApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoDBApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate) {
		return args -> {
			Address address = new Address(
					"USA",
					"New York",
					"Wall Street",
					"10005");

			Student student = new Student(
					"John",
					"Doe",
					"johndoe@gmail.com",
					"password",
					Gender.MALE,
					address,
					List.of("Math", "Computer Science"),
					BigDecimal.TEN);

			studentRepository.findStudentByEmail(student.getEmail())
					.ifPresentOrElse(foundStudent -> {
						System.out.println("Student already exists: " + foundStudent);
					}, () -> {
						System.out.println("Inserting student: " + student);
						studentRepository.insert(student);
					});
		};
	}

}
