package com.mpt.journal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Имя не может быть пустым")
    @Size(max = 50, message = "Имя не должно превышать 50 символов")
    private String name;

    @NotBlank(message = "Фамилия не может быть пустой")
    @Size(max = 50, message = "Фамилия не должна превышать 50 символов")
    private String lastName;

    @NotBlank(message = "Отчество не может быть пустым")
    @Size(max = 50, message = "Отчество не должно превышать 50 символов")
    private String firstName;

    @Size(max = 50, message = "Дополнительное имя не должно превышать 50 символов")
    private String middleName;

    private boolean isDeleted = false;

    public StudentEntity() {
    }

    public StudentEntity(int id, String name, String lastName, String firstName, String middleName, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.isDeleted = isDeleted;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
