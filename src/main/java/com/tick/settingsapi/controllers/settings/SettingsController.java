package com.tick.settingsapi.controllers.settings;

import com.tick.settingsapi.dtos.SettingsDTO;
import com.tick.settingsapi.models.Response;
import com.tick.settingsapi.models.SettingsModel;
import com.tick.settingsapi.repositories.SettingsRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/settings")
@Slf4j
public class SettingsController {

    private final SettingsRepository _repository;

    public SettingsController(SettingsRepository repository) {
        this._repository = repository;
    }

    @ApiOperation(value = "Creates a Settings Object for a specific user.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 400, message = "Settings object of user %s already exists."),
            @ApiResponse(code = 401, message = "Authorization header is invalid"),
            @ApiResponse(code = 403, message = "Authorization header is missing in request")
    })
    @PostMapping public ResponseEntity<Response> create(
            @ApiParam(value = "The user's sub identifier grabbed from a valid Google ID Token.", required = true)
            @RequestHeader("id") String userId,
            @ApiParam(value = "The details of the Settings Object to add.", required = true)
            @RequestBody SettingsDTO request) {

        log.info(String.format("Received request to create settings for user %s.", userId));
        var alreadyPresent =
                _repository.findById(userId).isPresent();

        if (alreadyPresent) {
            log.warn(
                    String.format(
                            "Tried to create Settings object for user %s while user already existed. No changes.",
                            userId)
            );
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .message(String.format("Settings object of user %s already exists.", userId))
                            .build()
            );
        }

        var newSettings = SettingsModel.builder()
                .userId(userId)
                .primaryColor(request.primaryColor())
                .secondaryColor(request.secondaryColor())
                .tertiaryColor(request.tertiaryColor())
                .locale(request.locale())
                .weatherCity(request.weatherCity())
                .build();
        _repository.save(newSettings);
        log.info(String.format("Created new Settings object for user %s.", userId));

        return ResponseEntity.ok().body(
                Response.builder()
                        .data(newSettings)
                        .build()
        );
    }

    @ApiOperation(value = "Gets a Settings Object for a specific user.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "Authorization header is invalid"),
            @ApiResponse(code = 403, message = "Authorization header is missing in request"),
            @ApiResponse(code = 404, message = "Could not find settings for user %s.")
    })
    @GetMapping public ResponseEntity<Response> get(
            @ApiParam(value = "The user's sub identifier grabbed from a valid Google ID Token.", required = true)
            @RequestHeader("id") String userId) {

        log.info(String.format("Received request to get settings for user %s.", userId));
        var settings = _repository.findById(userId);

        if (settings.isEmpty()) {
            log.warn(String.format(
                    "Tried to get Settings object for user %s but found no match.", userId)
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Response.builder()
                        .message(String.format("Could not find settings for user %s.", userId))
                        .build()
            );
        }

        log.info(String.format("Responded to request for settings for user %s.", userId));

        return ResponseEntity.ok().body(
                Response.builder()
                        .data(settings)
                        .build()
        );
    }

}
