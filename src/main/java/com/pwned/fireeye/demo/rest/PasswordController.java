package com.pwned.fireeye.demo.rest;

import com.pwned.fireeye.demo.service.IPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("password")
public class PasswordController {

    @Autowired
    private IPasswordService passwordService;

    @GetMapping(value = "{characters}", produces = "text/plain")
    public String getPwnedPasswords(@PathVariable(value = "characters") String characters) {
        return passwordService.getPwnedPasswords(characters);
    }
}
