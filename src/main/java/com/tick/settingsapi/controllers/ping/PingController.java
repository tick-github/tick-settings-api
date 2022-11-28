package com.tick.settingsapi.controllers.ping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
public class PingController {

    @GetMapping
    public String Ping() {
        log.info(LocalDateTime.now() + "\tInsecure ping request");
        return "Pong!";
    }

}
