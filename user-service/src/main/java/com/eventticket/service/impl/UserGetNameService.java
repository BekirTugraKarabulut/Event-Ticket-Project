package com.eventticket.service.impl;

import com.eventticket.entity.User;
import com.eventticket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGetNameService {

    private final UserRepository userRepository;

    public String getNamebyUsername(String username){

        Optional<User> user = userRepository.findByUsername(username);

        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }else{
            return user.get().getName();
        }

    }

}
