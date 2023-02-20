package com.hdi.studentportal.Mapper;

import java.util.List;

public interface BaseResponseMapper<Response, Entity> {

  Response fromDTOToResponse(Entity dto);

  List<Response> fromDTOListToResponseList(List<Entity> dtoList);

}
