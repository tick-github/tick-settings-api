package com.tick.settingsapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Response {

    private String message;
    private Object data;

}
