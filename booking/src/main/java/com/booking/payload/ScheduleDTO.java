package com.booking.payload;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class ScheduleDTO {
    private Long id;

    @NotNull(message = "Bus ID cannot be null")
    private Long busId;

    @NotNull(message = "Route ID cannot be null")
    private Long routeId;

    @NotNull(message = "Departure time cannot be null")
    @FutureOrPresent(message = "Departure time cannot be in the past")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time cannot be null")
    @FutureOrPresent(message = "Arrival time cannot be in the past")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price must be greater than 0")
    private Double price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // getters and setters
}

// BookingDTO.java




