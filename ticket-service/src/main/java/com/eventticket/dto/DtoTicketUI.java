package com.eventticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoTicketUI {

    private Long id;

    private int categoryId;

    private String name;

    private String date;

    private String day;

    private String time;

    private String place;

    private String imageUrl;

    private Long capacity;

    private String accessCode;

    private BigDecimal price;

    private boolean soldOut = false;

}
