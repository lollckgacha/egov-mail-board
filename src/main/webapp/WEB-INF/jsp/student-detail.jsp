<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>학생 상세정보</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/student.css">
</head>
<body>
    <header><h1>Student Management System</h1></header>
    <main class="container">
        <div class="title-area"><h2>학생 상세정보</h2></div>
        <c:choose>
            <c:when test="${not empty student}">
                <div class="table-box">
                    <table aria-label="학생 상세정보">
                        <tr><th scope="row">학번</th><td><c:out value="${student.studentNo}" /></td></tr>
                        <tr><th scope="row">이름</th><td><c:out value="${student.name}" /></td></tr>
                        <tr><th scope="row">학과</th><td><c:out value="${student.department}" /></td></tr>
                        <tr><th scope="row">상태</th><td><c:out value="${student.status}" /></td></tr>
                    </table>
                </div>
            </c:when>
            <c:otherwise><p role="alert"><c:out value="${error}" /></p></c:otherwise>
        </c:choose>
        <div class="toolbar"><a class="btn" href="${pageContext.request.contextPath}/students.do">목록으로</a></div>
    </main>
</body>
</html>
