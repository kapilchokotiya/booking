package com.booking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.Instant;
@Data
@NoArgsConstructor
public class FeedbackDTO {
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    @PositiveOrZero
    @Max(5)
    private Integer rating;
    @NotBlank
    private String comments;
    private Instant createdAt;
    private Instant updatedAt;

    // getters and setters
}

