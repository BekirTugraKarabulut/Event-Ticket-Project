package com.eventticket.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "sold_out")
    private boolean soldOut = false;

}
