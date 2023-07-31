package com.booking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RouteDTO {
    private Long id;

    @NotBlank(message = "Origin is required")
    private String origin;

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotEmpty(message = "Distance is required")
    @Min(value = 1, message = "Distance must be at least 1")
    private Integer distance;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // Getters and setters
}

