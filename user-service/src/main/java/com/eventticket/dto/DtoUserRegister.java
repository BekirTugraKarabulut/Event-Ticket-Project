package com.eventticket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUserRegister {

    @Email(message = "Invalid email format")
    private String username;

    @Size(min = 3,max = 20)
    private String name;

    @Size(min = 3,max = 20)
    private String surname;

    @Size(min = 11 , max = 11)
    private String phoneNumber;

    private String password;

}
