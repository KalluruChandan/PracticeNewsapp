package com.newsapp.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "user_table"
)
public class User{

    @Id
    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be empty")
    @Size(
            min = 6,
            message = "User name sholud atleast 6 characters"
    )
    private String username;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be empty")
    @Email
    private String email;

    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be empty")
    @Size(
            min = 6,
            message = "password sholud atleast 6 characters"
    )
    private String password;

}