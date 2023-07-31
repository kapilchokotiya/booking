package com.booking.util;

import com.booking.payload.UserDTO;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfExporter {

    public static ByteArrayInputStream exportUsersToPdf(List<UserDTO> users) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(out);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument, PageSize.A4)) {

            document.add(new Paragraph("List of users"));

            Table table = new Table(UnitValue.createPercentArray(new float[]{1f, 2f, 2f, 3f, 3f, 3f}));
            table.addCell("ID");
            table.addCell("First Name");
            table.addCell("Last Name");
            table.addCell("Email");
            table.addCell("Phone Number");
            table.addCell("Profile Picture");

            for (UserDTO user : users) {
                table.addCell(String.valueOf(user.getId()));
                table.addCell(user.getFirstName());
                table.addCell(user.getLastName());
                table.addCell(user.getEmail());
                table.addCell(user.getPhoneNumber());
                table.addCell(user.getProfilePicture());
            }

            document.add(table);
        } catch (IOException e) {
            // Handle the exception here, if needed
            throw new RuntimeException("Failed to generate PDF", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}


























































//public class PdfExporter {
//
//    public static ByteArrayInputStream exportUsersToPdf(List<UserDTO> users) {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        try {
//            PdfWriter writer = new PdfWriter(out);
//            PdfDocument pdfDocument = new PdfDocument(writer);
//            Document document = new Document(pdfDocument, PageSize.A4);
//            document.add(new Paragraph("List of users"));
//
//           // float[] columnWidths = {1f, 2f, 2f, 3f, 3f, 3f};
//            Table table = new Table(UnitValue.createPercentArray(new float[]{
//                    table.addCell("ID");
//            table.addCell("First Name");
//            table.addCell("Last Name");
//            table.addCell("Email");
//            table.addCell("Phone Number");
//            table.addCell("Profile Picture");
//
//            for (UserDTO userDTO :userDTOS) {
//                table.addCell(String.valueOf(userDTOS.getId()));
//                table.addCell(userDTOS.getFirstName());
//                table.addCell(userDTOS.getLastName());
//                table.addCell(userDTOS.getEmail());
//                table.addCell(userDTOS.getPhoneNumber());
//                table.addCell(userDTOS.getProfilePicture());
//            }
//            document.add(table);
//            document.close();
//        } catch (IOException e) {
//            // Handle the exception here, if needed
//            throw new RuntimeException("Failed to generate PDF",e);
//
//        }
//        return new ByteArrayInputStream(out.toByteArray());
//        }
//    }
//
////    private static Cell createHeaderCell(String title) throws IOException {
////        Color grayColor = new DeviceRgb(240, 240, 240);
////        PdfFont font = PdfFontFactory.createFont(PdfFontFactory.);
////        Cell cell = new Cell();
////        cell.setBackgroundColor(grayColor);
////        cell.setPadding(5);
////        cell.add(new Paragraph(title).setFont(font));
////        return cell;
////    }
//
