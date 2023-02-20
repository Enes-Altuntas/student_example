package com.hdi.studentportal.Response.Student;

import com.hdi.studentportal.Response.Lesson.GetLessonResponse;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GetStudentResponse {

  private Long id;

  private String name;

  private String surname;

  private Set<GetLessonResponse> lessonList;

}
