INSERT INTO STUDENTS (ID, NAME, SURNAME)
VALUES (101, 'Benjamin', 'Franklin'),
       (102, 'Abraham', 'Lincoln'),
       (103, 'George', 'Washington');

INSERT INTO LESSONS (ID, LESSON_NAME, STUDENT_QUOTA)
VALUES (101, 'Matematik', 2),
       (102, 'Türkçe', 2);

INSERT INTO LESSONS_AND_STUDENTS (LESSON_ID, STUDENT_ID)
VALUES (101, 101),
       (102, 101),
       (101, 102);