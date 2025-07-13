package Patterns.Builder;

import java.util.List;

public class Student {

    int rollNumber;
    String name;
    int age;
    String address;
    int phoneNumber;
    int parentsNumber;
    List<String> subjects;

    public Student(StudentBuilder studentBuilder) {
        this.rollNumber = studentBuilder.rollNumber;
        this.name = studentBuilder.name;
        this.age = studentBuilder.age;
        this.address = studentBuilder.address;
        this.phoneNumber = studentBuilder.phoneNumber;
        this.parentsNumber = studentBuilder.parentsNumber;
        this.subjects = studentBuilder.subjects;
    }
}
