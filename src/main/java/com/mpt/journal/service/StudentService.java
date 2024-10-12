package com.mpt.journal.service;

import com.mpt.journal.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface StudentService {

    Page<StudentEntity> findStudentsByFilter(String name, String lastName, String firstName, Pageable pageable);

    void addStudent(StudentEntity student);

    void updateStudent(StudentEntity student);

    void deleteStudent(int id);

    void deleteMultipleStudents(List<Integer> ids);

    void softDeleteStudent(int id);

    StudentEntity getStudentById(int id);
}
