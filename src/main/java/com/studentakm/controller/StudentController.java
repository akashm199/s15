package com.studentakm.controller;

import com.studentakm.dto.StudentDTO;
import com.studentakm.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        log.info("POST /api/students - Create student");
        StudentDTO createdStudent = studentService.createStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        log.info("GET /api/students - Get all students");
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        log.info("GET /api/students/{} - Get student by ID", id);
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
        log.info("PUT /api/students/{} - Update student", id);
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.info("DELETE /api/students/{} - Delete student", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/by-name")
    public ResponseEntity<List<StudentDTO>> searchByName(@RequestParam String name) {
        log.info("GET /api/students/search/by-name - Search students by name: {}", name);
        List<StudentDTO> students = studentService.searchByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/search/by-city")
    public ResponseEntity<List<StudentDTO>> findByCity(@RequestParam String city) {
        log.info("GET /api/students/search/by-city - Find students by city: {}", city);
        List<StudentDTO> students = studentService.findByCity(city);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/search/by-state")
    public ResponseEntity<List<StudentDTO>> findByState(@RequestParam String state) {
        log.info("GET /api/students/search/by-state - Find students by state: {}", state);
        List<StudentDTO> students = studentService.findByState(state);
        return ResponseEntity.ok(students);
    }

}
