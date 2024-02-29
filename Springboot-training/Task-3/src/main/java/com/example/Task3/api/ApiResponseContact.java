package com.example.Task3.api;

import com.example.Task3.Model.PhoneNum;
import com.example.Task3.Model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiResponseContact {
    private Long id;
    private String firstName;
    private String lastName;
    private List<PhoneNum> phonenums;
    private UserInfo userInfo = null;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
