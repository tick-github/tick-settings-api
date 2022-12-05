package com.tick.settingsapi.controllers.ping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PingController {

    @GetMapping
    public String Ping() {
        log.info("Insecure ping request");
        return "Pong!";
    }

}
