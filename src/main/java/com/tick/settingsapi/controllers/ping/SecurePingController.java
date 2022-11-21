package com.tick.settingsapi.controllers.ping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/settings/secure/ping")
public class SecurePingController {

    @GetMapping
    public String Ping(@RequestHeader("name") String name) {
        return String.format("Pong! Thank you for verifying your identity, %s!", name);
    }

}
