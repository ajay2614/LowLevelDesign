package Patterns.Builder;

import java.util.List;

/**
 * Used in cases when there are optional parameters and to avoid creating many constructors
 * which can further duplicate constructor signatures(same type passing in constructor, how does
 * it know which is which)
 */
public class StudentBuilder {
    int rollNumber;
    String name;
    int age;
    String address;
    int phoneNumber;
    int parentsNumber;
    List<String> subjects;

    public StudentBuilder rollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
        return this;
    }
    public StudentBuilder name(String name) {
        this.name = name;
        return this;
    }
    public StudentBuilder age(int age) {
        this.age = age;
        return this;
    }
    public StudentBuilder address(String address) {
        this.address = address;
        return this;
    }
    public StudentBuilder phoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public StudentBuilder parentsNumber(int parentsNumber) {
        this.parentsNumber = parentsNumber;
        return this;
    }
    public StudentBuilder subjects(List<String> subjects) {
        this.subjects = subjects;
        return this;
    }
    public Student build() {
        return new Student(this);
    }

}
