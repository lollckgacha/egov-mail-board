<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>학생 관리 시스템</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/student.css">

</head>

<body>

    <header>

        <h1>
            Student Management System
        </h1>

    </header>


    <div class="container">

        <div class="title-area">

            <h2>
                학생 관리
            </h2>

            <p>
                등록된 학생 정보를 조회할 수 있습니다.
            </p>

        </div>


        <div class="toolbar">

            <strong>
                전체 학생 ${studentCount}명
            </strong>

            <a
                href="${pageContext.request.contextPath}/student-form.do"
                class="btn">

                + 학생 등록

            </a>

        </div>


        <div class="table-box">

            <table>

                <thead>

                    <tr>

                        <th>번호</th>
                        <th>학번</th>
                        <th>이름</th>
                        <th>학과</th>
                        <th>상태</th>

                    </tr>

                </thead>


                <tbody>

                    <c:forEach
                        var="student"
                        items="${students}">

                        <tr>

                            <td>
                                ${student.id}
                            </td>

                            <td>
                                <c:url var="detailUrl" value="/student-detail.do">
                                    <c:param name="studentNo" value="${student.studentNo}" />
                                </c:url>
                                <a href="<c:out value='${detailUrl}' />"><c:out value="${student.studentNo}" /></a>
                            </td>

                            <td>
                                <c:out value="${student.name}" />
                            </td>

                            <td>
                                <c:out value="${student.department}" />
                            </td>

                            <td>

                                <span class="status">
                                    <c:out value="${student.status}" />
                                </span>

                            </td>

                        </tr>

                    </c:forEach>

                    <c:if test="${empty students}">
                        <tr><td colspan="5">등록된 학생이 없습니다.</td></tr>
                    </c:if>
                </tbody>

            </table>

        </div>

    </div>

</body>

</html>