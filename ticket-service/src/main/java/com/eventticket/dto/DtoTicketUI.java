package com.eventticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoTicketUI {

    private Long id;

    private String name;

    private String date;

    private String day;

    private String time;

    private String place;

    private String imageUrl;

    private boolean soldOut = false;

}
