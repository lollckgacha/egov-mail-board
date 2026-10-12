package egovframework.example.student;

public class Student {

    private int id;

    // 학번
    private String studentNo;

    private String name;
    private String department;
    private String status;

    public Student(
            int id,
            String studentNo,
            String name,
            String department,
            String status) {

        this.id = id;
        this.studentNo = studentNo;
        this.name = name;
        this.department = department;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getStatus() {
        return status;
    }
}