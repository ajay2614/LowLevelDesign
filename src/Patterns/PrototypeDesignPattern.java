package Patterns;

class StudentObj implements Cloneable {
    private String name;
    private int age;

    //not required, just for demonstration
    public StudentObj(){}

    public StudentObj(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }}

public class PrototypeDesignPattern {

    public static void main(String[] args) {
        try {
            StudentObj student1 = new StudentObj("John", 20);

            //Cloning incorrect way
            StudentObj student2 = new StudentObj();
            // unable to set student2.name = student1.getName() as fields are private;

            //Another incorrect way, this is not cloning, this will just point to student1 address
            //if done this, any changes in student2 will also reflect in student1
            student2 = student1;

            //Correct way to clone
            student2 = (StudentObj) student1.clone();

            System.out.println("Original Student: " + student1.getName() + ", Age: " + student1.getAge());
            System.out.println("Cloned Student: " + student2.getName() + ", Age: " + student2.getAge());

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
