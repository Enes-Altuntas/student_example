package com.hdi.studentportal.Controller.Lesson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.hdi.studentportal.Request.Lesson.SaveLessonRequest;
import com.hdi.studentportal.Request.Lesson.UpdateLessonRequest;
import com.hdi.studentportal.Response.Lesson.GetLessonResponse;
import com.hdi.studentportal.Response.Student.GetStudentResponse;
import com.hdi.studentportal.Service.Lesson.LessonService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class LessonControllerTest {

  private static final Long LESSON_ID = 1L;
  private static final Long STUDENT_ID = 1L;

  @Mock
  private LessonService lessonService;

  @InjectMocks
  private LessonController lessonController;

  @Test
  void saveLesson_shouldReturnSavedLesson() {
    SaveLessonRequest request = new SaveLessonRequest("Math", 30);
    GetLessonResponse response = new GetLessonResponse(1L, "Math", 30);
    when(lessonService.saveLesson(request)).thenReturn(response);

    ResponseEntity<GetLessonResponse> result = lessonController.saveLesson(request);

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(response, result.getBody());
  }

  @Test
  void updateLesson_shouldReturnUpdatedLesson() {
    UpdateLessonRequest request = new UpdateLessonRequest(LESSON_ID, "Physics", 2);
    GetLessonResponse response = new GetLessonResponse(LESSON_ID, "Physics", 2);
    
    when(lessonService.updateLesson(request)).thenReturn(response);

    ResponseEntity<GetLessonResponse> result = lessonController.updateLesson(request);

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(response, result.getBody());
  }

  @Test
  void getAllLessons_shouldReturnAllLessons() {
    List<GetLessonResponse> response = new ArrayList<>();
    response.add(new GetLessonResponse(1L, "Math", 3));
    response.add(new GetLessonResponse(2L, "Physics", 2));

    when(lessonService.getAllLessons()).thenReturn(response);

    ResponseEntity<List<GetLessonResponse>> result = lessonController.getAllLessons();

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(response, result.getBody());
  }

  @Test
  void getStudentsOfLesson_shouldReturnAllStudentsOfTheLesson() {
    List<GetStudentResponse> response = new ArrayList<>();
    response.add(new GetStudentResponse(STUDENT_ID, "John", "Doe"));
    response.add(new GetStudentResponse(2L, "Jane", "Doe"));

    when(lessonService.getStudentsOfLesson(LESSON_ID)).thenReturn(response);

    ResponseEntity<List<GetStudentResponse>> result = lessonController.getStudentsOfLesson(
        LESSON_ID);

    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals(response, result.getBody());
  }
}