package com.hdi.studentportal.Repository.Lesson;

import com.hdi.studentportal.Entity.Lesson.Lesson;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

  List<Lesson> findByStudentListId(Long studentId);
}
