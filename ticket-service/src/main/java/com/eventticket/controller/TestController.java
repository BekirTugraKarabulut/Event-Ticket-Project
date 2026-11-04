package com.eventticket.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@SecurityRequirement(name = "bearerAuth")
public class TestController {

        @GetMapping("/test")
        public String test(Authentication authentication) {

            return "Ticket Service çalışıyor! Giriş yapan kullanıcı: "
                    + authentication.getName();
        }

}
