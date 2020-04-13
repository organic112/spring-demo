package com.potato112.springdemo.web.form.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Represents web form data
 */
@Data
public class CountryVO implements Serializable {

    private String id;
    @NotEmpty(message = "Country name cannot be empty")
    private String name;

}
