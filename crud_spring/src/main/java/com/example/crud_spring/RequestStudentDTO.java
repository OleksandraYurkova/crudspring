package com.example.crud_spring;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestStudentDTO {
    private String name;
    private int age;
}
