package com.booking.util;

import com.booking.payload.UserDTO;
import com.opencsv.CSVWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class CsvExporter {

    public static ByteArrayInputStream exportUsersToCsv(List<UserDTO> users) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
             CSVWriter csvWriter = new CSVWriter(outputStreamWriter)) {

            String[] header = {"ID", "First Name", "Last Name", "Email", "Phone Number", "Profile Picture"};
            csvWriter.writeNext(header);

            for (UserDTO user : users) {
                String[] row = {
                        String.valueOf(user.getId()),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getPhoneNumber(),
                        user.getProfilePicture()
                };
                csvWriter.writeNext(row);
            }

            csvWriter.flush();
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Failed to generate CSV", e);
        }
    }
}
