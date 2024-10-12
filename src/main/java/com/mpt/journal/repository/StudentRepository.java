package com.mpt.journal.repository;

import com.mpt.journal.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    Page<StudentEntity> findByIsDeletedFalseAndNameContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndFirstNameContainingIgnoreCase(
            String name, String lastName, String firstName, Pageable pageable);
}