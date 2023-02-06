package com.example.assgnement1techmaster.controller;

import com.example.assgnement1techmaster.model.dto.StudentDTO;
import com.example.assgnement1techmaster.model.entity.Student;
import com.example.assgnement1techmaster.repository.ClaszRepositpory;
import com.example.assgnement1techmaster.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClaszController {
    @Autowired
    private ClaszRepositpory claszRepositpory;
    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/clasz/getstudentDTO")
    public ResponseEntity<List<StudentDTO>> getAllStudentDTO() {
        ModelMapper mapper = new ModelMapper();
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = students.stream()
                .map(student -> mapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOS);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clasz/getstudent")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/clasz/addstudent", consumes = "application/json")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student s = new Student();
        s.setNameStudent(student.getNameStudent());
        s.setAge(student.getAge());
        s.setLevel(student.getLevel());
        studentRepository.save(s);
        return ResponseEntity.ok(s);
    }
}
























