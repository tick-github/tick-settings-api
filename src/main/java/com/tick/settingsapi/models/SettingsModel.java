package com.tick.settingsapi.models;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@RequiredArgsConstructor
@Builder
@Getter
public class SettingsModel {

    @Id
    private String userId;
    private String primaryColor;
    private String secondaryColor;
    private String tertiaryColor;
    private String locale;
    private String weatherCity;

}
