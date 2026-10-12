# egov-mail-board

전자정부프레임워크 기반 백엔드프로그래밍 I 실습 프로젝트입니다. 현재 구현된 기능은 학생 목록, 학생 등록, Excel 저장, 학생 상세 조회와 학습용 로그인 화면입니다.

## 실행 환경

- eGovFramework 5.0.0 / Spring MVC
- JDK 17 이상 (실습 PC: JDK 21)
- Apache Tomcat 10.1
- Maven
- Apache POI 5.4.1
- JSP / Jakarta JSTL

## Excel 준비

`C:/egov/data/students.xlsx`를 만들고 시트 이름을 **학생정보**로 지정합니다. A~D열 첫 행에는 다음 제목을 입력합니다.

| 학번 | 이름 | 학과 | 상태 |
| --- | --- | --- | --- |
| 20261001 | 홍길동 | 멀티미디어학과 | 재학 |
| 20261002 | 김철수 | 인공지능학과 | 재학 |
| 20261003 | 이영희 | 소프트웨어학과 | 재학 |

학번 열은 **텍스트** 형식으로 설정하세요. 위 학생들은 실습용 예시입니다. 실제 Excel 데이터는 Git에서 제외됩니다. 파일을 Excel에서 열었다면 웹에서 등록하기 전에 닫아주세요.

다른 경로를 사용하려면 Tomcat의 JVM 옵션에 `-Dstudent.excel.path=파일의절대경로`를 지정합니다.

## Eclipse 실행

1. **File → Import → Maven → Existing Maven Projects**로 프로젝트를 가져옵니다.
2. **Maven → Update Project (Alt+F5)**를 실행합니다.
3. Tomcat 10.1에 프로젝트를 추가하고 시작합니다.
4. 기본 포트 8080, 컨텍스트 `/egov-project`인 경우 아래 주소로 접속합니다.

- 첫 화면: `http://localhost:8080/egov-project/`
- 학생 목록: `http://localhost:8080/egov-project/students.do`
- 학생 등록: `http://localhost:8080/egov-project/student-form.do`
- 상세 조회: 목록에서 학번을 클릭합니다.
- 학습용 로그인: `http://localhost:8080/egov-project/login.do`

로그인은 고정된 실습용 값을 비교하는 예제이며 접근 제어가 구현된 인증 시스템은 아닙니다.

## Maven 빌드

```powershell
mvn "-Dmaven.javadoc.skip=true" package
```

기존 실습 PC에서 Javadoc 출력 경로의 권한 오류가 있어 위 명령은 문서 생성을 제외합니다. WAR 파일은 `target/egov-project-1.0.0.war`에 생성됩니다.

## 6차시 코드 흐름

`StudentController → StudentService → StudentExcelRepository → students.xlsx`

- Excel에서 학생 목록을 읽으며 첫 행은 제목으로 건너뜁니다.
- 등록 시 중복 학번과 공백 입력을 검사하고 Excel 마지막 행에 추가합니다.
- 학번을 문자열로 저장해 앞자리 0을 유지합니다.
- 학번으로 상세 정보를 조회합니다.
- 임시 파일 저장 후 교체하여 저장 실패 시 기존 데이터를 보호합니다.
- 한 서버에서 중복 검사와 저장을 순차 처리합니다.

## 실습 확인

학생 등록 → 상세 조회 → 같은 학번 중복 등록 → Tomcat 재시작 → 데이터 유지 순서로 확인하세요.

별도 Tomcat에서 목록/등록/상세 JSP, 한글과 앞자리 0, HTML 이스케이프, 중복 및 동시 중복 등록, 파일 누락 오류, 프로세스 재시작 후 데이터 유지를 확인했습니다. Maven의 기존 설정에서는 JUnit 테스트가 생략되며 위 검증은 실제 HTTP 요청과 Excel 내용 확인으로 수행했습니다.
