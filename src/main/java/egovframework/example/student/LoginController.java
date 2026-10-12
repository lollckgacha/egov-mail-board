package egovframework.example.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // DB에 저장된 값이라고 가정하는 임시 로그인 정보
    private static final String SAVED_EMAIL = "test@naver.com";
    private static final String SAVED_PASSWORD = "test";

    // 로그인 화면을 보여주는 기능
    @GetMapping("/login.do")
    public String loginPage() {

        return "login";
    }

    // 로그인 버튼을 눌렀을 때 실행
    @PostMapping("/login.do")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model) {

        // 입력값과 서버에 저장된 값을 비교
        if (SAVED_EMAIL.equals(email) && SAVED_PASSWORD.equals(password)) {

            // 로그인 성공
            return "redirect:/students.do";
        }

        // 로그인 실패
        model.addAttribute(
                "error",
                "이메일 또는 비밀번호가 올바르지 않습니다."
        );

        return "login";
    }
}