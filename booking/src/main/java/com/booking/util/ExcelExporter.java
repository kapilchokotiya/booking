package com.booking.util;

//How we can generate API to download report in Excel PDF & CSB.
import com.booking.payload.UserDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

//This annotation is a generic stereotype annotation used to indicate that a class is a Spring
// component. It is the base annotation for all other stereotype annotations.
@Component
public class ExcelExporter {

    public static ByteArrayInputStream exportUsersToExcel(List<UserDTO> users) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // Create a new sheet
            Sheet sheet = workbook.createSheet("Users");

            // Create a header row
            Row headerRow = sheet.createRow(0);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerCellStyle.setBorderBottom(BorderStyle.THIN);
            headerCellStyle.setBorderTop(BorderStyle.THIN);
            headerCellStyle.setBorderRight(BorderStyle.THIN);
            headerCellStyle.setBorderLeft(BorderStyle.THIN);

            String[] headers={"ID","First Name","Last Name","Email","Phone Number","Profile Picture"};
            for(int i=0;i<headers.length;i++){
                Cell cell=headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }
            // Add the users to the sheet
            int rowNum = 1;
            for (UserDTO user : users) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getFirstName());
                row.createCell(2).setCellValue(user.getLastName());
                row.createCell(3).setCellValue(user.getEmail());
                row.createCell(4).setCellValue(user.getPhoneNumber());
                row.createCell(5).setCellValue(user.getProfilePicture());
            }
            for (int i=0;i<headers.length;i++){
                sheet.autoSizeColumn(i);
            }

            // Write the workbook to a byte array
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to export users to Excel", e);
        }
    }
}
