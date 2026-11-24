package com.eventticket.controller.impl;

import com.eventticket.controller.UserController;
import com.eventticket.dto.DtoPhoneNumber;
import com.eventticket.dto.DtoUser;
import com.eventticket.dto.DtoUserInfo;
import com.eventticket.dto.DtoUserRegister;
import com.eventticket.security.AuthRequest;
import com.eventticket.security.AuthResponse;
import com.eventticket.service.UserService;
import com.eventticket.service.impl.GetInfoByUserService;
import com.eventticket.service.impl.UpdatePhoneNoService;
import com.eventticket.service.impl.UserGetNameService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserGetNameService userGetNameService;
    private final GetInfoByUserService getInfoByUserService;
    private final UpdatePhoneNoService updatePhoneNoService;

    @PostMapping(path = "/register")
    @Override
    public DtoUser register(@Valid @RequestBody DtoUserRegister dtoUserRegister) {
        return userService.register(dtoUserRegister);
    }

    @PostMapping(path = "/auth")
    @Override
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return userService.authenticate(authRequest);
    }

    @GetMapping(path = "/name/{username}")
    public String getNamebyUsername(@PathVariable(name = "username" , required = true) String username){
        return userGetNameService.getNamebyUsername(username);
    }

    @GetMapping(path = "/info")
    public DtoUserInfo getUserInfoByUsername(){
        return getInfoByUserService.getUserInfoByUsername();
    }

    @PutMapping(path = "/update")
    public DtoUserInfo updatePhoneNumber(@RequestBody DtoPhoneNumber dtoPhoneNumber){
        return updatePhoneNoService.updatePhoneNumber(dtoPhoneNumber);
    }

}
