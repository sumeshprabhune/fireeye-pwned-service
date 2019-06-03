package com.pwned.fireeye.demo.rest;

import com.pwned.fireeye.demo.beans.PasteEntityResponse;
import com.pwned.fireeye.demo.service.IPasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pwned/pastes")
public class PasteController {

    @Autowired
    private IPasteService pasteService;

    @GetMapping("{account}")
    public ResponseEntity<PasteEntityResponse> getBreach(@PathVariable(value = "account") String accountId) {
        PasteEntityResponse pasteEntityResponse = pasteService.getPasteAccounts(accountId);
        return new ResponseEntity<>(pasteEntityResponse, HttpStatus.OK);
    }
}
