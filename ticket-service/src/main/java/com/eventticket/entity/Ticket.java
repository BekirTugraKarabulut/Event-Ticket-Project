package com.eventticket.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private String date;

    @Column(name = "day")
    private String day;

    @Column(name = "time")
    private String time;

    @Column(name = "place")
    private String place;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "capacity")
    private Long capacity;

    @Column(name = "access_code")
    private String accessCode;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "sold_out")
    private boolean soldOut = false;

}
