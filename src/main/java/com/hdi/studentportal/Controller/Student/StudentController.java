package com.hdi.studentportal.Controller.Student;

import com.hdi.studentportal.Request.Student.SaveStudentRequest;
import com.hdi.studentportal.Request.Student.UpdateStudentRequest;
import com.hdi.studentportal.Response.Lesson.GetLessonResponse;
import com.hdi.studentportal.Response.Student.GetStudentResponse;
import com.hdi.studentportal.Service.Student.StudentService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin(origins = {"*"})
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @PostMapping
  public ResponseEntity<GetStudentResponse> saveStudent(
      @RequestBody @Valid SaveStudentRequest saveStudentRequest) {

    GetStudentResponse getStudentResponse = studentService.saveStudent(saveStudentRequest);

    return new ResponseEntity<>(getStudentResponse, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<GetStudentResponse> updateStudent(
      @RequestBody @Valid UpdateStudentRequest updateStudentRequest) {

    GetStudentResponse getStudentResponse = studentService.updateStudent(updateStudentRequest);

    return new ResponseEntity<>(getStudentResponse, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<GetStudentResponse>> getAllStudents() {

    List<GetStudentResponse> allStudents = studentService.getAllStudents();

    return new ResponseEntity<>(allStudents, HttpStatus.OK);
  }

  @GetMapping("/{studentId}/assign/lesson/{lessonId}")
  public ResponseEntity<GetStudentResponse> assignLessonToStudent(@PathVariable Long studentId,
      @PathVariable Long lessonId) {

    GetStudentResponse getStudentResponse = studentService.assignLessonToStudent(studentId,
        lessonId);

    return new ResponseEntity<>(getStudentResponse, HttpStatus.OK);
  }

  @GetMapping("/{studentId}/remove/lesson/{lessonId}")
  public ResponseEntity<GetStudentResponse> removeLessonFromStudent(@PathVariable Long studentId,
      @PathVariable Long lessonId) {

    GetStudentResponse getStudentResponse = studentService.removeLessonFromStudent(studentId,
        lessonId);

    return new ResponseEntity<>(getStudentResponse, HttpStatus.OK);
  }

  @GetMapping("/{studentId}/lessons")
  public ResponseEntity<List<GetLessonResponse>> listLessonsOfStudent(
      @PathVariable Long studentId) {

    List<GetLessonResponse> getLessonResponses = studentService.listLessonsOfStudent(studentId);

    return new ResponseEntity<>(getLessonResponses, HttpStatus.OK);
  }
}
