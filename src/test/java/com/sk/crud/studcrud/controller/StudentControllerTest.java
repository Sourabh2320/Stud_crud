package com.sk.crud.studcrud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.crud.studcrud.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // CREATE
    @Test
    void shouldAddStudent() throws Exception {

        Student student = new Student();
        student.setName("Sourabh");
        student.setEmail("sourabh@gmail.com");
        student.setCourse("CSE");
        student.setAge(22);

        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sourabh"));
    }

    //  READ ALL
    @Test
    void shouldGetAllStudents() throws Exception {

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk());
    }

    //  READ BY ID
    @Test
    void shouldGetStudentById() throws Exception {

        Student student = new Student();
        student.setName("Rahul");
        student.setEmail("rahul@gmail.com");
        student.setCourse("IT");
        student.setAge(21);

        String response = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Student savedStudent = objectMapper.readValue(response, Student.class);

        mockMvc.perform(get("/students/" + savedStudent.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rahul"));
    }

    //  UPDATE
    @Test
    void shouldUpdateStudent() throws Exception {

        Student student = new Student();
        student.setName("Old Name");
        student.setEmail("old@gmail.com");
        student.setCourse("ECE");
        student.setAge(20);

        String response = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Student savedStudent = objectMapper.readValue(response, Student.class);

        Student updatedStudent = new Student();
        updatedStudent.setName("New Name");
        updatedStudent.setEmail("new@gmail.com");
        updatedStudent.setCourse("CSE");
        updatedStudent.setAge(22);

        mockMvc.perform(put("/students/" + savedStudent.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Name"));
    }

    //DELETE
    @Test
    void shouldDeleteStudent() throws Exception {

        Student student = new Student();
        student.setName("Delete Me");
        student.setEmail("delete@gmail.com");
        student.setCourse("ME");
        student.setAge(23);

        String response = mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Student savedStudent = objectMapper.readValue(response, Student.class);

        mockMvc.perform(delete("/students/" + savedStudent.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted with id " + savedStudent.getId()));
    }
}
