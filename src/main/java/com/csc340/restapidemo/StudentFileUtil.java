package com.csc340.restapidemo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentFileUtil {

    private static final String FILE_PATH = "students.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Student> readAllStudents() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists() && file.length() > 0) {
            return objectMapper.readValue(file, new TypeReference<List<Student>>() {});
        }
        return new ArrayList<>();
    }

    public static Student readStudentById(int id) throws IOException {
        List<Student> students = readAllStudents();
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public static void writeStudent(Student student) throws IOException {
        List<Student> students = readAllStudents();
        students.add(student);
        objectMapper.writeValue(new File(FILE_PATH), students);
    }

    public static void updateStudent(int id, Student updatedStudent) throws IOException {
        List<Student> students = readAllStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.set(i, updatedStudent);
                objectMapper.writeValue(new File(FILE_PATH), students);
                return;
            }
        }
    }

    public static void deleteStudent(int id) throws IOException {
        List<Student> students = readAllStudents();
        students.removeIf(student -> student.getId() == id);
        objectMapper.writeValue(new File(FILE_PATH), students);
    }
}
