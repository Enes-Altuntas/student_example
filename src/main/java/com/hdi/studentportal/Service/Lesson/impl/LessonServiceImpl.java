package com.hdi.studentportal.Service.Lesson.impl;

import com.hdi.studentportal.Entity.Lesson.Lesson;
import com.hdi.studentportal.Mapper.Lesson.LessonResponseMapper;
import com.hdi.studentportal.Mapper.Student.StudentResponseMapper;
import com.hdi.studentportal.Repository.Lesson.LessonRepository;
import com.hdi.studentportal.Request.Lesson.SaveLessonRequest;
import com.hdi.studentportal.Request.Lesson.UpdateLessonRequest;
import com.hdi.studentportal.Response.Lesson.GetLessonResponse;
import com.hdi.studentportal.Response.Student.GetStudentResponse;
import com.hdi.studentportal.Service.Lesson.LessonService;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LessonServiceImpl implements LessonService {

  private final LessonRepository lessonRepository;
  private final LessonResponseMapper lessonResponseMapper;
  private final StudentResponseMapper studentResponseMapper;

  public LessonServiceImpl(LessonRepository lessonRepository,
      LessonResponseMapper lessonResponseMapper, StudentResponseMapper studentResponseMapper) {
    this.lessonRepository = lessonRepository;
    this.lessonResponseMapper = lessonResponseMapper;
    this.studentResponseMapper = studentResponseMapper;
  }

  @Override
  public GetLessonResponse saveLesson(SaveLessonRequest lessonRequest) {
    Lesson lesson = new Lesson();
    lesson.setLessonName(lessonRequest.getLessonName());
    lesson.setStudentQuota(lessonRequest.getStudentQuota());

    Lesson savedLesson = lessonRepository.save(lesson);

    return lessonResponseMapper.fromDTOToResponse(savedLesson);
  }

  @Override
  public GetLessonResponse updateLesson(UpdateLessonRequest lessonRequest) {
    Lesson currentLesson = lessonRepository.findById(lessonRequest.getId()).orElseThrow(() -> {
      throw new NoSuchElementException("Lesson not found!");
    });

    currentLesson.setLessonName(lessonRequest.getLessonName());
    currentLesson.setStudentQuota(lessonRequest.getStudentQuota());

    Lesson savedLesson = lessonRepository.save(currentLesson);

    return lessonResponseMapper.fromDTOToResponse(savedLesson);
  }

  @Override
  public List<GetStudentResponse> getStudentsOfLesson(Long lessonId) {
    Lesson currentLesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
      throw new NoSuchElementException("Lesson not found!");
    });

    return studentResponseMapper.fromDTOListToResponseList(
        new ArrayList<>(currentLesson.getStudentList()));
  }

  @Override
  public List<GetLessonResponse> getAllLessons() {
    List<Lesson> lessonList = lessonRepository.findAll();

    return lessonResponseMapper.fromDTOListToResponseList(lessonList);
  }
}
