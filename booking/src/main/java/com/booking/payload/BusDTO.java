package com.booking.payload;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class BusDTO {
    @NotNull(message = "Operator ID is required")
    private Long id;
    @NotNull(message = "Operator ID is required")
    private Long operatorId;
    @NotEmpty(message = "Bus type is required")
    private String busType;
    @NotNull(message = "Total seats is required")
    @Min(value = 1, message = "Total seats must be at least 1")
    private Integer totalSeats;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String amenities;

    // Getters and setters
}

