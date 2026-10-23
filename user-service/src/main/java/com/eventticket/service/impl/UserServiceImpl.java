package com.eventticket.service.impl;

import com.eventticket.dto.DtoUser;
import com.eventticket.dto.DtoUserRegister;
import com.eventticket.entity.RefreshToken;
import com.eventticket.entity.User;
import com.eventticket.repository.RefreshTokenRepository;
import com.eventticket.repository.UserRepository;
import com.eventticket.security.AuthRequest;
import com.eventticket.security.AuthResponse;
import com.eventticket.security.JwtService;
import com.eventticket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationProvider authenticationProvider;
    private final JwtService jwtService;

    @Override
    public DtoUser register(DtoUserRegister dtoUserRegister) {

        User user = User.builder()
                .username(dtoUserRegister.getUsername())
                .name(dtoUserRegister.getName())
                .surname(dtoUserRegister.getSurname())
                .phoneNumber(dtoUserRegister.getPhoneNumber())
                .password(bCryptPasswordEncoder.encode(dtoUserRegister.getPassword()))
                .createdTime(LocalDateTime.now())
                .build();

        if(userRepository.findByUsername(user.getUsername()).isPresent() ||
                userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()){
            throw new RuntimeException("User already exists");
        }

        User dbUser = userRepository.save(user);

        DtoUser dtoUser = DtoUser.builder()
                .username(dbUser.getUsername())
                .name(dbUser.getName())
                .surname(dbUser.getSurname())
                .phoneNumber(dbUser.getPhoneNumber())
                .password(dbUser.getPassword())
                .createdTime(dbUser.getCreatedTime())
                .build();

        return dtoUser;
    }

    private RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setIssueDate(new Date());
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
        return refreshToken;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword());
        authenticationProvider.authenticate(authenticationToken);

        Optional<User> user = Optional.of(userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found")));

        String accessToken = jwtService.generateToken(user.get());
        RefreshToken refreshToken = createRefreshToken(user.get());
        refreshTokenRepository.save(refreshToken);

        return new AuthResponse(accessToken, refreshToken.getRefreshToken());
    }


}
