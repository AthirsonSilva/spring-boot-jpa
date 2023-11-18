package com.mongodb.mongodb.Student;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.mongodb.Student.Models.Student;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/student")
@AllArgsConstructor
public class StudentController {
	private final StudentService studentService;

	@GetMapping
	public List<Student> fetchAllStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping("{id}")
	public Student findStudentById(@PathVariable("id") String id) {
		return studentService.findStudentById(id);
	}

	@GetMapping("email/{email}")
	public Student findStudentByEmail(@PathVariable("email") String email) {
		return studentService.findStudentByEmail(email);
	}

	@PostMapping
	public Student createStudent(@RequestBody Student student) {
		return studentService.createStudent(student);
	}

	@PatchMapping("{id}")
	public Student updateStudent(@PathVariable("id") String id, @RequestBody Student student) {
		return studentService.updateStudent(id, student);
	}

	@DeleteMapping("{id}")
	public void deleteStudent(@PathVariable("id") String id) {
		studentService.deleteStudent(id);
	}
}
