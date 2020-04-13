package com.potato112.springdemo.web.form.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Represents web form data
 */
@Data
public class AddressVO implements Serializable {

    public static class AttributeName {
        private AttributeName() {
        }

        public static final String STREET = "street";
        public static final String CITY = "city";
        public static final String ZIP = "zip";
        public static final String COUNTRY = "country";
    }

    private String id;
    @NotEmpty(message = "Street cannot be empty")
    @Length(max = 200, message = "max number of characters should be 200 chars")
    private String street;
    private String city;
    private String zip;
    @NotEmpty(message = "country should not be empty")
    private CountryVO country;

}
