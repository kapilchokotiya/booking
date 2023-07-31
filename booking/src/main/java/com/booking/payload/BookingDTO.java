package com.booking.payload;
//Date-14/4/23 all module
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class BookingDTO {
    private Long id;

    @NotNull(message = "User id is required")
    private Long userId;

    @NotNull(message = "Schedule id is required")
    private Long scheduleId;

    @NotNull(message = "Total passengers is required")
    @Min(value = 1, message = "At least 1 passenger is required")
    private Integer totalPassengers;

    @NotNull(message = "Total amount is required")
    @Min(value = 0, message = "Total amount cannot be negative")
    private Double totalAmount;

    @NotNull(message = "Payment method id is required")
    private Long paymentMethodId;

    @NotNull(message = "Booking status is required")
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters and setters
}