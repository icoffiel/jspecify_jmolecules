package com.icoffiel.jspecify.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class HelloController {
    @GetMapping
    public String hello() {

        var message = getMessage("World");

        return message.toUpperCase();
    }

    private String getMessage(String input) {
        return "Hello " + input;
    }
}
