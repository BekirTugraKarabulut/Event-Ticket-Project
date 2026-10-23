package com.eventticket.controller;

import com.eventticket.dto.DtoUser;
import com.eventticket.dto.DtoUserRegister;
import com.eventticket.security.AuthRequest;
import com.eventticket.security.AuthResponse;

public interface UserController {

    public DtoUser register(DtoUserRegister dtoUserRegister);

    public AuthResponse authenticate(AuthRequest authRequest);

}
