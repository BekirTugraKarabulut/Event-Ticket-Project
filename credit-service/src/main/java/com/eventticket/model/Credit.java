package com.eventticket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "credits")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "credit_card_number")
    private String creditCartNumber;

    @Column(name = "mm/dd")
    private String mmdd;

    @Column(name = "create_date")
    private Date createDate;

}
