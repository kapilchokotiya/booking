package com.booking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
public class UserOfferDTO {
    private Long id;
    @NotNull
    private Long userId;
    @NotNull
    private Long offerId;

    // getters and setters
}

