package com.tick.settingsapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class Response {

    private String message;
    private Object data;

}
