package com.systems.ticketing.ticket.controller;

import com.systems.ticketing.appuser.AppUserDto;
import com.systems.ticketing.appuser.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    @Autowired
    private AppUserService appUserService;
//    @GetMapping("/login")
//    public String loginUser(String emailaddress,String password){
//        appUserService.loadUserByUsername(emailaddress);
//        return "login";
//    }
    @PostMapping("signin")
    public ResponseEntity<?> apploginUser(@RequestBody AppUserDto dto){
        if(dto==null){
            throw new IllegalArgumentException("dto cannot be empty!!!!!!!");
        }
        String email= dto.getEmail();
        String pass= dto.getPassword();
        log.info("....................{},{} ",email,pass);
                appUserService.authenticateUser(email,pass);
        return  ResponseEntity.ok("login sucessfull");

    }
}
