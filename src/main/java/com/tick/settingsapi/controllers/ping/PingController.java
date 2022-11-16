package com.tick.settingsapi.controllers.ping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/settings/ping")
public class PingController {

    @GetMapping
    public String Ping() {
        return "Pong!";
    }

}
