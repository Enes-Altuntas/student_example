package com.hdi.studentportal.Entity.Student;

import com.hdi.studentportal.Entity.Lesson.Lesson;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "STUDENTS")
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "lessonList")
public class Student {

  @Id
  @Column(name = "ID", nullable = false, updatable = false)
  @SequenceGenerator(name = "GEN_STUDENT", sequenceName = "SEQ_STUDENT", allocationSize = 1)
  @GeneratedValue(generator = "GEN_STUDENT", strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "SURNAME", nullable = false)
  private String surname;

  @ManyToMany(mappedBy = "studentList")
  private Set<Lesson> lessonList = new HashSet<>();

}
