package com.hdi.studentportal.Response.Lesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GetLessonResponse {

  private Long id;

  private String lessonName;

  private int studentQuota;

}
