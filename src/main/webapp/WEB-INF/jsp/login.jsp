<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>학생 관리 시스템 로그인</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
   
</head>

<body>

    <div class="login-container">

        <h1>학생 관리 시스템</h1>

        <p class="description">
            이메일과 비밀번호를 입력하세요.
        </p>

        <form action="login.do" method="post">

            <label>이메일</label>
            <input type="email"
                   name="email"
                   placeholder="admin@example.com"
                   required>

            <label>비밀번호</label>
            <input type="password"
                   name="password"
                   placeholder="비밀번호 입력"
                   required>

            <div class="error">
                ${error}
            </div>

            <button type="submit">
                로그인
            </button>

        </form>

    </div>

</body>
</html>