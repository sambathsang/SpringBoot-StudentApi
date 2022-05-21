package com.example.practice.Student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	
	public void save(Student student) throws Exception {
		Optional<Student> email = studentRepository.findByEmail(student.getEmail());
		if(email.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		studentRepository.save(student);
	}

	public String deleteStudent(Long studentId) {
		if (studentRepository.existsById(studentId)) {
			studentRepository.deleteById(studentId);
			return "Student deleted";
		} else {
			return "Student doesn't exist";
		}
		
	}
	
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		Optional<Student> student = studentRepository.findById(studentId);
		if(student.isPresent()) {
			student.get().setName(name);
			student.get().setEmail(email);
		}
		
	}
}
