package com.eventticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DtoCredit {

    private Long id;

    private String username;

    private String creditCartNumber;

    private String mmdd;

    private Date createDate;

}
