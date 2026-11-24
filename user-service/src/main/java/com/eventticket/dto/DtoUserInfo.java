package com.eventticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoUserInfo {

    private String username;

    private String name;

    private String surname;

    private String phoneNumber;

}
