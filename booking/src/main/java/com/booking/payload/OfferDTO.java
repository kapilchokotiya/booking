package com.booking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Instant;
import java.time.LocalDate;
@Data
@NoArgsConstructor
public class OfferDTO {
    private Long id;
    @NotBlank
    private String offerName;
    @NotBlank
    private String promoCode;
    @NotNull
    private String discountType;
    @NotNull
    @Positive
    private Double discountValue;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    private Instant createdAt;
    private Instant updatedAt;

    // getters and setters
}

