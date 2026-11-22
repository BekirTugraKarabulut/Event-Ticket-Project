package com.eventticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoCartDetails {

    private Long sepetId;

    private Long id;

    private String name;

    private Integer quantity;

    private String date;

    private String day;

    private String place;

    private String time;

    private String imageUrl;

    private BigDecimal price;

    private String accessCode;

}
