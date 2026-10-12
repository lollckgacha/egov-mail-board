package egovframework.example.student;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentExcelRepository studentExcelRepository;

    public StudentService(StudentExcelRepository studentExcelRepository) {
        this.studentExcelRepository = studentExcelRepository;
    }

    public List<Student> getStudents() throws IOException {
        return studentExcelRepository.findAll();
    }

    // 한 서버에서 중복 검사와 저장을 순서대로 처리합니다.
    public synchronized boolean addStudent(String studentNo, String name,
            String department, String status) throws IOException {
        studentNo = studentNo.trim();
        name = name.trim();
        department = department.trim();
        status = status.trim();
        if (studentNo.isEmpty() || name.isEmpty() || department.isEmpty() || status.isEmpty()) {
            throw new IllegalArgumentException("학번, 이름, 학과, 상태를 모두 입력해주세요.");
        }
        if (studentExcelRepository.findByStudentNo(studentNo) != null) {
            return false;
        }
        studentExcelRepository.addStudent(new Student(0, studentNo, name, department, status));
        return true;
    }

    public Student getStudent(String studentNo) throws IOException {
        return studentExcelRepository.findByStudentNo(studentNo.trim());
    }
}
