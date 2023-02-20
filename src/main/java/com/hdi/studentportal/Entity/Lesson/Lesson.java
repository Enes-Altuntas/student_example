package com.hdi.studentportal.Entity.Lesson;

import com.hdi.studentportal.Entity.Student.Student;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LESSONS")
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "studentList")
public class Lesson {

  @Id
  @Column(name = "ID", nullable = false, updatable = false)
  @SequenceGenerator(name = "GEN_STUDENT", sequenceName = "SEQ_STUDENT", allocationSize = 1)
  @GeneratedValue(generator = "GEN_STUDENT", strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "LESSON_NAME", nullable = false)
  private String lessonName;

  @Column(name = "STUDENT_QUOTA", nullable = false)
  private int studentQuota;

  @ManyToMany
  @JoinTable(name = "LESSONS_AND_STUDENTS", joinColumns = @JoinColumn(name = "LESSON_ID"), inverseJoinColumns = @JoinColumn(name = "STUDENT_ID"))
  private Set<Student> studentList = new HashSet<>();

}
