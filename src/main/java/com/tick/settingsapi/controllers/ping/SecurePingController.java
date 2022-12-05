package com.tick.settingsapi.controllers.ping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/settings/secure/ping")
@Slf4j
public class SecurePingController {

    @GetMapping
    public String Ping(@RequestHeader("name") String name, @RequestHeader("id") String id) {
        log.info("Secure ping request from user " + id);
        return String.format("Pong! Thank you for verifying your identity, %s!", name);
    }

}
