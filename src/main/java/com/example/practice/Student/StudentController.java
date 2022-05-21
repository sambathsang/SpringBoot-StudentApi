package com.example.practice.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {
	
	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping
	public void save(@Validated @RequestBody Student student) throws Exception {
		studentService.save(student);
	}
	
	@DeleteMapping(path="{studentId}")
	public String deleteStudent(@PathVariable("studentId") Long studentId) {
		return studentService.deleteStudent(studentId);
	}
	
	@PutMapping(path = "{studentId}")
	public void updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestParam(required = false) String name, 
			@RequestParam(required = false) String email) {
		studentService.updateStudent(studentId, name, email);
	}

}
