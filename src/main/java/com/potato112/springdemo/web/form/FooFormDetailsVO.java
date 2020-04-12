package com.potato112.springdemo.web.form;

import com.potato112.springdemo.security.userauthsecurity.model.GroupType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Represents web form data
 */
@Data
public class FooFormDetailsVO {

    public static final class AttributeName {

        private static final String ID = "id";
        private static final String NAME = "name";
        private static final String TYPE = "type";
        private static final String ADDRESS = "address";
        private static final String INFO = "info";
    }

    private String id;

    @Length(min = 1, max = 20, message = "name should be 1 to 20 characters")
    private String name;
    @NotNull(message = "groupType should not be null")
    private GroupType type;
    @NotNull(message = "Address cannot be empty")
    @Valid
    private AddressVO address;
    private String info;
}
