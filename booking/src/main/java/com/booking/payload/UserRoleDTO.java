package com.booking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
@Data
@NoArgsConstructor
public class UserRoleDTO {
    private Long id;
    @NotEmpty(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "Role ID is required")
    private Long roleId;

    // Getters and setters
}
