package com.vishnu.ExpenseTracker.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {
    @NotBlank(message = "Please enter name")
    private String name;

    @NotNull(message = "Please enter email")
    @Email(message = "Enter valid email")
    private String email;

    @NotNull(message = "Please enter password")
    @Size(min = 5 , message = "Password at least 5 characters")
    private String password;

    private Long age=0L;
}
