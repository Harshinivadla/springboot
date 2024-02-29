package com.example.Task3.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Please enter userName")
    private String userName;

    @Column(unique = true)
    @Email(message = "Enter valid Email")
    private String email;

    @Column(unique = true)
    @Pattern(regexp = "[789]\\d{9}", message = "Please enter valid mobileNumber")
    private String phNo;

    private String password;
    private String role;

}