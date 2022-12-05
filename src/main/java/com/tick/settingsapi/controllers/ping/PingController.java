package com.tick.settingsapi.controllers.ping;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1/settings/ping")
public class PingController {

    @ApiOperation(
            value = "Checks the Settings API heartbeat in unsecure mode.",
            response = String.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Pong!", response = String.class)
    })
    @GetMapping
    public String Ping() {
        log.info("Insecure ping request");
        return "Pong!";
    }

}
