package egovframework.example.student;

import java.util.List;
import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(
            StudentService studentService) {

        this.studentService = studentService;
    }


    // =====================================
    // 학생 목록
    // =====================================

    @GetMapping("/students.do")
    public String students(Model model) throws IOException {

        List<Student> students =
                studentService.getStudents();

        model.addAttribute(
                "students",
                students
        );

        model.addAttribute(
                "studentCount",
                students.size()
        );

        return "students";
    }


    // =====================================
    // 학생 등록 화면
    // =====================================

    @GetMapping("/student-form.do")
    public String studentForm() {

        return "student-form";
    }


    // =====================================
    // 학생 등록 처리
    // =====================================

    @PostMapping("/student-add.do")
    public String addStudent(

            @RequestParam("studentNo")
            String studentNo,

            @RequestParam("name")
            String name,

            @RequestParam("department")
            String department,

            @RequestParam("status")
            String status,

            Model model) throws IOException {

        // 학생 등록 시도
        model.addAttribute("studentNo", studentNo);
        model.addAttribute("name", name);
        model.addAttribute("department", department);
        model.addAttribute("status", status);

        boolean result;
        try {
            result = studentService.addStudent(
                        studentNo,
                        name,
                        department,
                        status
                );
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "student-form";
        }


        // 학번이 중복된 경우
        if (!result) {

            model.addAttribute(
                    "error",
                    "이미 등록된 학번입니다."
            );

            // 사용자가 입력한 값 유지
            model.addAttribute(
                    "studentNo",
                    studentNo
            );

            model.addAttribute(
                    "name",
                    name
            );

            model.addAttribute(
                    "department",
                    department
            );

            model.addAttribute(
                    "status",
                    status
            );

            return "student-form";
        }


        // 등록 성공
        return "redirect:/students.do";
    }

    // 학번으로 상세 정보 조회
    @GetMapping("/student-detail.do")
    public String studentDetail(@RequestParam("studentNo") String studentNo,
            Model model, HttpServletResponse response) throws IOException {
        Student student = studentService.getStudent(studentNo);
        if (student == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("error", "해당 학번의 학생을 찾을 수 없습니다.");
        }
        model.addAttribute("student", student);
        return "student-detail";
    }

    @ExceptionHandler(IOException.class)
    public String excelError(IOException exception, Model model, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        model.addAttribute("error", "Excel 파일을 읽거나 저장하지 못했습니다. "
                + "C:/egov/data/students.xlsx 경로와 학생정보 시트를 확인하고, Excel에서 파일을 닫아주세요.");
        return "student-error";
    }
}
