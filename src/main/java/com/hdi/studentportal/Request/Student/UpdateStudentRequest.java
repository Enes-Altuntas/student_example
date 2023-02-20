package com.hdi.studentportal.Request.Student;

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
public class UpdateStudentRequest {

  @NotNull(message = "Student id field can't be null!")
  private Long id;

  @NotBlank(message = "name field can't be blank!")
  private String name;

  @NotBlank(message = "Surname field can't be blank!")
  private String surname;

}
