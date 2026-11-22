package com.eventticket.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sepet_id")
    private Long sepetId;

    @Column(name = "username")
    private String username;

    @Column(name = "ticket_id")
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

}
