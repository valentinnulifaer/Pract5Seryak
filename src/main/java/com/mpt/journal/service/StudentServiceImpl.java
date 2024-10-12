package com.mpt.journal.service;

import com.mpt.journal.entity.StudentEntity;
import com.mpt.journal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Page<StudentEntity> findStudentsByFilter(String name, String lastName, String firstName, Pageable pageable) {
        return studentRepository.findByIsDeletedFalseAndNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(
                name, lastName, firstName, pageable);
    }

    @Override
    public void addStudent(@Valid StudentEntity student) {
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(@Valid StudentEntity student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void deleteMultipleStudents(List<Integer> ids) {
        studentRepository.deleteAllById(ids);
    }

    @Override
    public void softDeleteStudent(int id) {
        Optional<StudentEntity> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            StudentEntity student = optionalStudent.get();
            student.setDeleted(true);
            studentRepository.save(student);
        }
    }

    @Override
    public StudentEntity getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }
}
