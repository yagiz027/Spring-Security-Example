package com.yagiz.SpringSecurityExample.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<String> getMessage(){
        return ResponseEntity.ok("Merhaba");
    }
}
