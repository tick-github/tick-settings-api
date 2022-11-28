package com.tick.settingsapi.controllers.settings;

import com.tick.settingsapi.dtos.SettingsDTO;
import com.tick.settingsapi.models.Response;
import com.tick.settingsapi.models.SettingsModel;
import com.tick.settingsapi.repositories.SettingsRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/settings")
@Slf4j
public class SettingsController {

    private final SettingsRepository _repository;

    public SettingsController(SettingsRepository repository) {
        this._repository = repository;
    }

    @PostMapping public ResponseEntity<Response> create(
            @RequestHeader("id") String userId, @RequestBody SettingsDTO request) {

        var alreadyPresent =
                _repository.findById(userId).isPresent();

        if (alreadyPresent) {
            log.warn(
                    String.format(
                            "%s\tTried to create Settings object for user %s while user already existed. No changes.",
                            LocalDateTime.now(), userId)
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
        log.info(String.format("%s\tCreated new Settings object for user %s.", LocalDateTime.now(), userId));

        return ResponseEntity.ok().body(
                Response.builder()
                        .data(newSettings)
                        .build()
        );
    }

}
