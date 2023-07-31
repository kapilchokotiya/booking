package com.booking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BusOperatorDTO {
    @NotNull(message = "Operator ID is required")
    private Long id;

    @NotEmpty(message = "Operator name is required")
    private String operatorName;

    @NotEmpty(message = "Contact email is required")
    @Email(message = "Invalid email")
    private String contactEmail;

    @NotEmpty(message = "Contact phone is required")
    @Pattern(regexp = "^\\+?[0-9]{10,12}$", message = "Invalid phone number")
    private String contactPhone;

    private MultipartFile logo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and setters
}

