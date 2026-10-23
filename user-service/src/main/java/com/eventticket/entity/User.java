package com.eventticket.entity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @Column(name = "email" , nullable = false)
    private String username;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "surname" , nullable = false)
    private String surname;

    @Column(name = "phone_number" , nullable = false , unique = true)
    private String phoneNumber;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "created_time" , nullable = false)
    private LocalDateTime createdTime;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<RefreshToken> refreshTokens;

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
