package com.studentakm.controller;

import com.studentakm.dto.CourseDTO;
import com.studentakm.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        log.info("POST /api/courses - Create course");
        CourseDTO createdCourse = courseService.createCourse(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        log.info("GET /api/courses - Get all courses");
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/active")
    public ResponseEntity<List<CourseDTO>> getActiveCourses() {
        log.info("GET /api/courses/active - Get active courses");
        List<CourseDTO> courses = courseService.getActiveCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        log.info("GET /api/courses/{} - Get course by ID", id);
        CourseDTO course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
        log.info("PUT /api/courses/{} - Update course", id);
        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.info("DELETE /api/courses/{} - Delete course", id);
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/by-name")
    public ResponseEntity<List<CourseDTO>> searchByName(@RequestParam String name) {
        log.info("GET /api/courses/search/by-name - Search courses by name: {}", name);
        List<CourseDTO> courses = courseService.searchByName(name);
        return ResponseEntity.ok(courses);
    }

}
