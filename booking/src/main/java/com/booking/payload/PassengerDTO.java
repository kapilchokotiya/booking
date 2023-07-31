package com.booking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Instant;
@Data
@NoArgsConstructor
public class PassengerDTO {
    private Long id;
    @NotNull
    private Long bookingId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Positive
    private Integer age;
    @NotBlank
    private String gender;
    @NotBlank
    private String seatNumber;
    private Instant createdAt;
    private Instant updatedAt;

    // getters and setters
}

