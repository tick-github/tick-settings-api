package com.tick.settingsapi.controllers.ping;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/settings/secure/ping")
@Slf4j
public class SecurePingController {

    @ApiOperation(value = "Checks the Settings API heartbeat in secure mode.", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pong! Thank you for verifying your identity, %s!"),
            @ApiResponse(code = 401, message = "Authorization header is invalid"),
            @ApiResponse(code = 403, message = "Authorization header is missing in request")
    })
    @GetMapping
    public String Ping(
            @ApiParam(value = "The user's name grabbed from a valid Google ID Token.", required = true)
            @RequestHeader("name") String name,
            @ApiParam(value = "The user's sub identifier grabbed from a valid Google ID Token.", required = true)
            @RequestHeader("id") String id) {
        log.info("Secure ping request from user " + id);
        return String.format("Pong! Thank you for verifying your identity, %s!", name);
    }

}
