package com.eventticket.service.impl;

import com.eventticket.dto.DtoPhoneNumber;
import com.eventticket.dto.DtoUserInfo;
import com.eventticket.entity.User;
import com.eventticket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdatePhoneNoService {

    private final UserRepository userRepository;

    public DtoUserInfo updatePhoneNumber(DtoPhoneNumber dtoPhoneNumber){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            user.get().setPhoneNumber(dtoPhoneNumber.getPhoneNumber());
            User dbUser = userRepository.save(user.get());
            return DtoUserInfo.builder()
                    .username(dbUser.getUsername())
                    .name(dbUser.getName())
                    .surname(dbUser.getSurname())
                    .phoneNumber(dbUser.getPhoneNumber())
                    .build();
        }else{
            throw new RuntimeException("User not found");
        }

    }

}
