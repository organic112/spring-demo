package com.potato112.springdemo.web.ui.common;

import com.potato112.springdemo.common.ErrorResult;
import lombok.Data;


@Data
public class SysValidationException extends RuntimeException {

    private ErrorResult result;

    public SysValidationException() {
        super();
    }

    public SysValidationException(ErrorResult result) {

        super(result.getMainMessage());
        this.result = result;
    }
}
