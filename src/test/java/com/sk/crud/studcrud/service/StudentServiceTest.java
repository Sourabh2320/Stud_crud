package com.sk.crud.studcrud.service;

import com.sk.crud.studcrud.model.Student;
import com.sk.crud.studcrud.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class StudentServiceTest {

    @MockBean
    private StudentRepository studentRepository; // mocked repository

    @Autowired
    private StudentService studentService; // real service

    // CREATE
    @Test
    void saveStudentTest() {

        Student student = new Student();
        student.setId(1L);
        student.setName("Sourabh");
        student.setEmail("sourabh@gmail.com");
        student.setCourse("CSE");
        student.setAge(22);

        Mockito.when(studentRepository.save(student))
                .thenReturn(student);

        Student savedStudent = studentService.saveStudent(student);

        Assertions.assertNotNull(savedStudent);
        Assertions.assertEquals("Sourabh", savedStudent.getName());
    }

    // READ ALL
    @Test
    void getAllStudentsTest() {

        Student s1 = new Student();
        s1.setId(1L);
        s1.setName("A");
        s1.setEmail("a@gmail.com");
        s1.setCourse("IT");
        s1.setAge(21);

        Student s2 = new Student();
        s2.setId(2L);
        s2.setName("B");
        s2.setEmail("b@gmail.com");
        s2.setCourse("CSE");
        s2.setAge(22);

        Mockito.when(studentRepository.findAll())
                .thenReturn(List.of(s1, s2));

        List<Student> result = studentService.getAllStudents();

        Assertions.assertEquals(2, result.size());
    }

    //  READ BY ID
    @Test
    void getStudentByIdTest() {

        Student student = new Student();
        student.setId(1L);
        student.setName("Rahul");
        student.setEmail("rahul@gmail.com");
        student.setCourse("ECE");
        student.setAge(23);

        Mockito.when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Rahul", result.getName());
    }

    //  UPDATE
    @Test
    void updateStudentTest() {

        Student existingStudent = new Student();
        existingStudent.setId(1L);
        existingStudent.setName("Old Name");
        existingStudent.setEmail("old@gmail.com");
        existingStudent.setCourse("IT");
        existingStudent.setAge(20);

        Student updatedStudent = new Student();
        updatedStudent.setName("New Name");
        updatedStudent.setEmail("new@gmail.com");
        updatedStudent.setCourse("CSE");
        updatedStudent.setAge(22);

        Mockito.when(studentRepository.findById(1L))
                .thenReturn(Optional.of(existingStudent));

        Mockito.when(studentRepository.save(existingStudent))
                .thenReturn(existingStudent);

        Student result = studentService.updateStudent(1L, updatedStudent);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("New Name", result.getName());
        Assertions.assertEquals("new@gmail.com", result.getEmail());
        Assertions.assertEquals("CSE", result.getCourse());
        Assertions.assertEquals(22, result.getAge());
    }

    // DELETE 
    @Test
    void deleteStudentTest() {

        Long id = 1L;

        Mockito.doNothing()
                .when(studentRepository)
                .deleteById(id);

        studentService.deleteStudent(id);

        Mockito.verify(studentRepository, Mockito.times(1))
                .deleteById(id);
    }
}
