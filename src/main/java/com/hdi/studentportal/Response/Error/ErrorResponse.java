package com.hdi.studentportal.Response.Error;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

  private String message;

  private List<String> details;

}
