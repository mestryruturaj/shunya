package io.two.bit.saint.shunya.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping(value = "/")
    public String ping() {
        return "Pong!!!";
    }
}
