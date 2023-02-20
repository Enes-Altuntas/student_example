package com.hdi.studentportal.Controller.Lesson;

import com.hdi.studentportal.Request.Lesson.SaveLessonRequest;
import com.hdi.studentportal.Request.Lesson.UpdateLessonRequest;
import com.hdi.studentportal.Response.Lesson.GetLessonResponse;
import com.hdi.studentportal.Response.Student.GetStudentResponse;
import com.hdi.studentportal.Service.Lesson.LessonService;
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
@RequestMapping("/api/v1/lesson")
@CrossOrigin(origins = {"*"})
public class LessonController {

  private final LessonService lessonService;

  public LessonController(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @PostMapping
  public ResponseEntity<GetLessonResponse> saveLesson(
      @RequestBody @Valid SaveLessonRequest saveLessonRequest) {

    GetLessonResponse getLessonResponse = lessonService.saveLesson(saveLessonRequest);

    return new ResponseEntity<>(getLessonResponse, HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<GetLessonResponse> updateLesson(
      @RequestBody @Valid UpdateLessonRequest updateLessonRequest) {

    GetLessonResponse getLessonResponse = lessonService.updateLesson(updateLessonRequest);

    return new ResponseEntity<>(getLessonResponse, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<GetLessonResponse>> getAllLessons() {

    List<GetLessonResponse> allLessons = lessonService.getAllLessons();

    return new ResponseEntity<>(allLessons, HttpStatus.OK);
  }

  @GetMapping("/{lessonId}/students")
  public ResponseEntity<List<GetStudentResponse>> getStudentsOfLesson(@PathVariable Long lessonId) {

    List<GetStudentResponse> allStudents = lessonService.getStudentsOfLesson(lessonId);

    return new ResponseEntity<>(allStudents, HttpStatus.OK);
  }
}
