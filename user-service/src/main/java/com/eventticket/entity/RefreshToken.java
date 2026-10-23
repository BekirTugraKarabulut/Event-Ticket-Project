package com.eventticket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "refresh_token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "expire_date")
    private Date expiredDate;

    @ManyToOne
    @JoinColumn(name = "email" , referencedColumnName = "email")
    private User user;

}
