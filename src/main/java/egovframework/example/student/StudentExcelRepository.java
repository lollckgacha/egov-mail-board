package egovframework.example.student;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

@Repository
public class StudentExcelRepository {
    private static final String FILE_PATH = "C:/egov/data/students.xlsx";
    private static final String SHEET_NAME = "학생정보";
    // 테스트 또는 다른 PC에서는 -Dstudent.excel.path=... 로 경로를 변경할 수 있습니다.
    private final Path filePath = Paths.get(System.getProperty("student.excel.path", FILE_PATH));

    // 첫 행은 제목이며, A~D열은 학번 / 이름 / 학과 / 상태입니다.
    public synchronized List<Student> findAll() throws IOException {
        List<Student> students = new ArrayList<>();
        try (XSSFWorkbook workbook = openWorkbook()) {
            Sheet sheet = getStudentSheet(workbook);
            DataFormatter formatter = new DataFormatter();
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                String studentNo = formatter.formatCellValue(row.getCell(0)).trim();
                if (studentNo.isEmpty()) {
                    continue;
                }
                students.add(new Student(rowIndex, studentNo,
                        formatter.formatCellValue(row.getCell(1)),
                        formatter.formatCellValue(row.getCell(2)),
                        formatter.formatCellValue(row.getCell(3))));
            }
        }
        return students;
    }

    public synchronized void addStudent(Student student) throws IOException {
        try (XSSFWorkbook workbook = openWorkbook()) {
            Sheet sheet = getStudentSheet(workbook);
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);
            row.createCell(0).setCellValue(student.getStudentNo());
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getDepartment());
            row.createCell(3).setCellValue(student.getStatus());

            // 임시 파일 쓰기가 성공한 뒤 교체하여 저장 실패 시 원본을 보호합니다.
            Path temp = Files.createTempFile(filePath.toAbsolutePath().getParent(), "students-", ".xlsx");
            try {
                try (OutputStream output = Files.newOutputStream(temp)) {
                    workbook.write(output);
                }
                try {
                    Files.move(temp, filePath, StandardCopyOption.REPLACE_EXISTING,
                            StandardCopyOption.ATOMIC_MOVE);
                } catch (AtomicMoveNotSupportedException e) {
                    Files.move(temp, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
            } finally {
                Files.deleteIfExists(temp);
            }
        }
    }

    public synchronized Student findByStudentNo(String studentNo) throws IOException {
        for (Student student : findAll()) {
            if (student.getStudentNo().equals(studentNo)) {
                return student;
            }
        }
        return null;
    }

    private XSSFWorkbook openWorkbook() throws IOException {
        // 입력 스트림은 저장하기 전에 닫습니다 (Windows 파일 잠금 방지).
        try (InputStream input = Files.newInputStream(filePath)) {
            return new XSSFWorkbook(input);
        }
    }

    private Sheet getStudentSheet(XSSFWorkbook workbook) throws IOException {
        Sheet sheet = workbook.getSheet(SHEET_NAME);
        if (sheet == null) {
            throw new IOException("Excel에 '" + SHEET_NAME + "' 시트가 없습니다: " + filePath);
        }
        return sheet;
    }
}
