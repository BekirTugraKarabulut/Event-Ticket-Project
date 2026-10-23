package com.eventticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoUser {

    private String username;

    private String name;

    private String surname;

    private String phoneNumber;

    private String password;

    private LocalDateTime createdTime;

}
