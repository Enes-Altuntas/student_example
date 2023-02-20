package com.hdi.studentportal.Request.Lesson;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SaveLessonRequest {

  @NotBlank(message = "Lesson name field can't be blank!")
  private String lessonName;

  @NotNull(message = "Student quota field can't be null!")
  private int studentQuota;

}
