package com.potato112.springdemo.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
public class ErrorResult implements Serializable {

    private String mainMessage;
    private Map<String, String> validationError;

}
