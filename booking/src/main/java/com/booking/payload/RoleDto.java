package com.booking.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
public class RoleDto {

    private Long id;
    @NotBlank(message = "Role name is required")
    @Size(max = 50, message = "Role name must be a maximum of 50 characters")
    private String roleName;

    // getters and setters
}

