package com.studentakm.controller;

import com.studentakm.dto.RegistrationDTO;
import com.studentakm.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<RegistrationDTO> registerStudent(
            @RequestParam Long studentId,
            @RequestParam Long courseId) {
        log.info("POST /api/registrations - Register student {} for course {}", studentId, courseId);
        RegistrationDTO registration = registrationService.registerStudentForCourse(studentId, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(registration);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<RegistrationDTO>> getStudentRegistrations(@PathVariable Long studentId) {
        log.info("GET /api/registrations/student/{} - Get student registrations", studentId);
        List<RegistrationDTO> registrations = registrationService.getStudentRegistrations(studentId);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/course/{courseName}")
    public ResponseEntity<List<RegistrationDTO>> getStudentsByCourse(@PathVariable String courseName) {
        log.info("GET /api/registrations/course/{} - Get students by course", courseName);
        List<RegistrationDTO> registrations = registrationService.getStudentsByCourse(courseName);
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/course-id/{courseId}")
    public ResponseEntity<List<RegistrationDTO>> getStudentsByCourseId(@PathVariable Long courseId) {
        log.info("GET /api/registrations/course-id/{} - Get students by course ID", courseId);
        List<RegistrationDTO> registrations = registrationService.getStudentsByCourseId(courseId);
        return ResponseEntity.ok(registrations);
    }

    @DeleteMapping("/{registrationId}")
    public ResponseEntity<Void> removeRegistration(@PathVariable Long registrationId) {
        log.info("DELETE /api/registrations/{} - Remove registration", registrationId);
        registrationService.removeRegistration(registrationId);
        return ResponseEntity.noContent().build();
    }

}
