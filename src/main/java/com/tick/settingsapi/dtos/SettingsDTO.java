package com.tick.settingsapi.dtos;

public record SettingsDTO(String primaryColor, String secondaryColor, String tertiaryColor, String locale,
                          String weatherCity) {
}
