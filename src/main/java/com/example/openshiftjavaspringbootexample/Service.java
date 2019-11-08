package com.example.openshiftjavaspringbootexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service {

    @GetMapping(value="/helloWorld")
	private String helloWorld() {
        return "Hello World!";
    }

}
