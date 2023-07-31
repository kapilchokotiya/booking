package com.booking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
@Data
@NoArgsConstructor
public class UserPaymentMethodDTO {
    private Long id;
    @NotEmpty(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "Payment type is required")
    private String paymentType;

    @NotEmpty(message = "Card number is required")
    @Pattern(regexp = "^\\d{16}$", message = "Invalid card number")
    private String cardNumber;

    @NotEmpty(message = "Expiration date is required")
    @Pattern(regexp = "^\\d{2}/\\d{2}$", message = "Invalid expiration date")
    private String expirationDate;

    @NotEmpty(message = "Card holder name is required")
    private String cardHolderName;

    // Getters and setters
}

