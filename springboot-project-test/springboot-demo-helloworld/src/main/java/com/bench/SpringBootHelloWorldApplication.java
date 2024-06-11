package com.bench;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
public class SpringBootHelloWorldApplication {
    public static void main(String[] args){
        SpringApplication.run(SpringBootHelloWorldApplication.class, args);
    }
//
//    @GetMapping("/hello")
//    public ResponseEntity<String> hello(){
//        return new ResponseEntity<>("hello world", HttpStatus.OK);
//    }
}
