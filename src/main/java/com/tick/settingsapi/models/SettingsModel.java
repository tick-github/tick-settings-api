package com.tick.settingsapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
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
