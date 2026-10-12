<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"><title>학생 정보 저장 오류</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/student.css">
</head>
<body>
    <header><h1>Student Management System</h1></header>
    <main class="container">
        <h2>학생 정보를 처리하지 못했습니다</h2>
        <p role="alert"><c:out value="${error}" /></p>
        <a class="btn" href="${pageContext.request.contextPath}/students.do">목록 다시 열기</a>
    </main>
</body>
</html>
