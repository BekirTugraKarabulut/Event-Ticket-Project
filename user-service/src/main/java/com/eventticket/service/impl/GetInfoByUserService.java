package com.eventticket.service.impl;

import com.eventticket.dto.DtoUserInfo;
import com.eventticket.entity.User;
import com.eventticket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetInfoByUserService {

    private final UserRepository userRepository;

    public DtoUserInfo getUserInfoByUsername(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);

        return DtoUserInfo.builder()
                .username(user.get().getUsername())
                .name(user.get().getName())
                .surname(user.get().getSurname())
                .phoneNumber(user.get().getPhoneNumber())
                .build();

    }
}

