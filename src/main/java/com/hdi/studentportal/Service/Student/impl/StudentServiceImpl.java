package com.hdi.studentportal.Service.Student.impl;

import com.hdi.studentportal.Entity.Lesson.Lesson;
import com.hdi.studentportal.Entity.Student.Student;
import com.hdi.studentportal.Mapper.Lesson.LessonResponseMapper;
import com.hdi.studentportal.Mapper.Student.StudentResponseMapper;
import com.hdi.studentportal.Repository.Lesson.LessonRepository;
import com.hdi.studentportal.Repository.Student.StudentRepository;
import com.hdi.studentportal.Request.Student.SaveStudentRequest;
import com.hdi.studentportal.Request.Student.UpdateStudentRequest;
import com.hdi.studentportal.Response.Lesson.GetLessonResponse;
import com.hdi.studentportal.Response.Student.GetStudentResponse;
import com.hdi.studentportal.Service.Student.StudentService;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final LessonRepository lessonRepository;
  private final StudentResponseMapper studentResponseMapper;
  private final LessonResponseMapper lessonResponseMapper;

  public StudentServiceImpl(StudentRepository studentRepository,
      LessonRepository lessonRepository, StudentResponseMapper studentResponseMapper,
      LessonResponseMapper lessonResponseMapper) {
    this.studentRepository = studentRepository;
    this.lessonRepository = lessonRepository;
    this.studentResponseMapper = studentResponseMapper;
    this.lessonResponseMapper = lessonResponseMapper;
  }

  @Override
  public List<GetStudentResponse> getAllStudents() {
    List<Student> studentList = studentRepository.findAll();

    return studentResponseMapper.fromDTOListToResponseList(studentList);
  }

  @Override
  public GetStudentResponse saveStudent(SaveStudentRequest studentRequest) {
    Student student = new Student();
    student.setName(studentRequest.getName());
    student.setSurname(studentRequest.getSurname());

    studentRepository.saveAndFlush(student);

    return studentResponseMapper.fromDTOToResponse(student);
  }

  @Override
  public GetStudentResponse updateStudent(UpdateStudentRequest studentRequest) {
    Student currentStudent = studentRepository.findById(studentRequest.getId()).orElseThrow(() -> {
      throw new NoSuchElementException("Student not found!");
    });

    currentStudent.setName(studentRequest.getName());
    currentStudent.setSurname(studentRequest.getSurname());

    studentRepository.saveAndFlush(currentStudent);

    return studentResponseMapper.fromDTOToResponse(currentStudent);
  }

  @Override
  public synchronized GetStudentResponse assignLessonToStudent(Long studentId, Long lessonId) {
    Student student = studentRepository.findById(studentId).orElseThrow(() -> {
      throw new NoSuchElementException("Student not found!");
    });

    Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
      throw new NoSuchElementException("Lesson not found!");
    });

    if (lesson.getStudentQuota() <= lesson.getStudentList().size()) {
      throw new RuntimeException("Lesson limit is exceeding!");
    }

    lesson.getStudentList().add(student);

    lessonRepository.saveAndFlush(lesson);

    return studentResponseMapper.fromDTOToResponse(student);
  }

  @Override
  public synchronized GetStudentResponse removeLessonFromStudent(Long studentId, Long lessonId) {
    Student student = studentRepository.findById(studentId).orElseThrow(() -> {
      throw new NoSuchElementException("Student not found!");
    });

    Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> {
      throw new NoSuchElementException("Lesson not found!");
    });

    lesson.getStudentList().remove(student);

    lessonRepository.saveAndFlush(lesson);

    return studentResponseMapper.fromDTOToResponse(student);
  }

  @Override
  public List<GetLessonResponse> listLessonsOfStudent(Long studentId) {
    Student student = studentRepository.findById(studentId).orElseThrow(() -> {
      throw new NoSuchElementException("Student not found!");
    });

    return lessonResponseMapper.fromDTOListToResponseList(new ArrayList<>(student.getLessonList()));
  }

}
