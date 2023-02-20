package com.hdi.studentportal.Request.Student;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SaveStudentRequest {

  @NotBlank(message = "Name field can't be blank!")
  private String name;

  @NotBlank(message = "Surname field can't be blank!")
  private String surname;

}
