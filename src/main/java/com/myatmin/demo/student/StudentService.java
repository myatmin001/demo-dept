package com.myatmin.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    public List<Student> getStudent(){
		return studentRepository.findAll();
	}

    public void addStudent(Student student){
        Optional<Student> getEmailByStudent = studentRepository.findStudentByEmail(student.getEmail());
        if(getEmailByStudent.isPresent()){
            throw new IllegalStateException("Email already exist");
        }
        studentRepository.save(student);
    }

    public void deleteByStudentId(Long studentId){
        boolean exist = studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException("Student id "+ studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudentById(Long studentId,String name,String email){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exit"));
        if(name!=null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if(email!=null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> getStudentEmail = studentRepository.findStudentByEmail(email);
            if(getStudentEmail.isPresent()){
                throw new IllegalStateException("Email already taken");
            }
            student.setEmail(email);
        }
    }
}
