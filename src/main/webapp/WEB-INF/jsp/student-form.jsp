<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="jakarta.tags.core" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>
        학생 등록
    </title>

    <!-- 학생 등록 화면 CSS -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/student-form.css">

</head>


<body>

    <!-- ==============================
         상단 영역
    =============================== -->

    <header>

        <h1>
            Student Management System
        </h1>

    </header>


    <!-- ==============================
         전체 내용
    =============================== -->

    <div class="container">


        <!-- 화면 제목 -->

        <div class="title-area">

            <h2>
                학생 등록
            </h2>

            <p>
                새로운 학생 정보를 입력하세요.
            </p>

        </div>


        <!-- ==============================
             학생 등록 폼
        =============================== -->

        <div class="form-box">


            <!-- ==============================
                 학번 중복 오류 메시지
            =============================== -->

            <c:if test="${not empty error}">

                <div class="error-message">

                    <c:out value="${error}" />

                </div>

            </c:if>


            <form
                action="${pageContext.request.contextPath}/student-add.do"
                method="post">


                <!-- ==============================
                     학번
                =============================== -->

                <div class="form-group">

                    <label for="studentNo">
                        학번
                    </label>

                    <input
                        type="text"
                        id="studentNo"
                        name="studentNo"
                        value="<c:out value='${studentNo}' />"
                        placeholder="학번을 입력하세요."
                        required>

                </div>


                <!-- ==============================
                     이름
                =============================== -->

                <div class="form-group">

                    <label for="name">
                        이름
                    </label>

                    <input
                        type="text"
                        id="name"
                        name="name"
                        value="<c:out value='${name}' />"
                        placeholder="학생 이름을 입력하세요."
                        required>

                </div>


                <!-- ==============================
                     학과
                     직접 입력이 아닌 목록 선택
                =============================== -->

                <div class="form-group">

                    <label for="department">
                        학과
                    </label>

                    <select
                        id="department"
                        name="department"
                        required>


                        <!-- 안내 문구 -->

                        <option
                            value=""
                            disabled
                            ${empty department ? 'selected' : ''}>

                            학과를 선택하세요.

                        </option>


                        <!-- 건축공학과 -->

                        <option
                            value="건축공학과"
                            ${department == '건축공학과' ? 'selected' : ''}>

                            건축공학과

                        </option>


                        <!-- 경영학과 -->

                        <option
                            value="경영학과"
                            ${department == '경영학과' ? 'selected' : ''}>

                            경영학과

                        </option>


                        <!-- 광고홍보학과 -->

                        <option
                            value="광고홍보학과"
                            ${department == '광고홍보학과' ? 'selected' : ''}>

                            광고홍보학과

                        </option>


                        <!-- 멀티미디어학과 -->

                        <option
                            value="멀티미디어학과"
                            ${department == '멀티미디어학과' ? 'selected' : ''}>

                            멀티미디어학과

                        </option>


                        <!-- 무역물류학과 -->

                        <option
                            value="무역물류학과"
                            ${department == '무역물류학과' ? 'selected' : ''}>

                            무역물류학과

                        </option>


                        <!-- 설비소방학과 -->

                        <option
                            value="설비소방학과"
                            ${department == '설비소방학과' ? 'selected' : ''}>

                            설비소방학과

                        </option>


                        <!-- 전자공학과 -->

                        <option
                            value="전자공학과"
                            ${department == '전자공학과' ? 'selected' : ''}>

                            전자공학과

                        </option>


                        <!-- 컴퓨터공학과 -->

                        <option
                            value="컴퓨터공학과"
                            ${department == '컴퓨터공학과' ? 'selected' : ''}>

                            컴퓨터공학과

                        </option>


                        <!-- 토목환경공학과 -->

                        <option
                            value="토목환경공학과"
                            ${department == '토목환경공학과' ? 'selected' : ''}>

                            토목환경공학과

                        </option>


                        <!-- 화학생명공학과 -->

                        <option
                            value="화학생명공학과"
                            ${department == '화학생명공학과' ? 'selected' : ''}>

                            화학생명공학과

                        </option>


                    </select>

                </div>


                <!-- ==============================
                     학생 상태
                =============================== -->

                <div class="form-group">

                    <label for="status">
                        상태
                    </label>

                    <select
                        id="status"
                        name="status">


                        <option
                            value="재학"
                            ${empty status || status == '재학' ? 'selected' : ''}>

                            재학

                        </option>


                        <option
                            value="휴학"
                            ${status == '휴학' ? 'selected' : ''}>

                            휴학

                        </option>


                    </select>

                </div>


                <!-- ==============================
                     버튼
                =============================== -->

                <div class="form-buttons">


                    <!-- 등록 -->

                    <button
                        type="submit"
                        class="submit-btn">

                        등록

                    </button>


                    <!-- 취소 -->

                    <a
                        href="${pageContext.request.contextPath}/students.do"
                        class="cancel-btn">

                        취소

                    </a>


                </div>


            </form>

        </div>

    </div>

</body>

</html>