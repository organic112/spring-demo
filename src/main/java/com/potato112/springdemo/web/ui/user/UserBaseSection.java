package com.potato112.springdemo.web.ui.user;

import com.potato112.springdemo.web.service.user.UserStatus;
import com.potato112.springdemo.web.service.user.UserVo;
import com.potato112.springdemo.web.ui.factories.SysCheckBoxFactory;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class UserBaseSection extends FormLayout {

    private EmailField emailField = new EmailField("Email");
    private TextField firstNameField = new TextField("First name");
    private TextField lastNameField = new TextField("Last name");
    private TextField phoneField = new TextField("Phone");
    private Checkbox statusField = SysCheckBoxFactory.create("Locked");

    public UserBaseSection(Binder<UserVo> binder) {

        // TODO set user Ids
        binder.bind(emailField, UserVo.AttributeName.EMAIL);
        binder.bind(firstNameField, UserVo.AttributeName.FIRST_NAME);
        binder.bind(lastNameField, UserVo.AttributeName.LAST_NAME);
        binder.bind(phoneField, UserVo.AttributeName.PHONE);
        binder.forField(statusField).bind(this::getStatus, this::setStatus);

        emailField.setVisible(true);
        emailField.setValue("test@test.com");

        add(emailField);
        add(firstNameField);
        add(lastNameField);
        add(phoneField);
        add(statusField);
    }

    private boolean getStatus(UserVo userVo) {
        return UserStatus.DISABLED.equals(userVo.getLocked());
    }

    private void setStatus(UserVo userVo, Boolean value) {
        if (Boolean.TRUE.equals(value)) {
            userVo.setLocked(UserStatus.DISABLED);
        } else {
            userVo.setLocked(UserStatus.ENABLED);
        }
    }
}
