package com.eventticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoTicket {

    private Long id;

    private String name;

    private String date;

    private String day;

    private String time;

    private String place;

    private String imageUrl;

    private boolean soldOut = false;

}
