package com.potato112.springdemo.web;


import com.potato112.springdemo.SysUINotificationFactory;
import com.potato112.springdemo.common.ErrorResult;
import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;
import com.potato112.springdemo.web.service.user.model.UserDto;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.BindingValidationStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class ValidationMessageUtil {

    private final BinderWithValueChangeListener binder;

    public ValidationMessageUtil(BinderWithValueChangeListener binder) {
        this.binder = binder;
    }

    public static Component crateNotificationContent(List<BindingValidationStatus<?>> fieldValidationErrors) {

        if (null == fieldValidationErrors || fieldValidationErrors.isEmpty()) {
            return new Div();
        }
        Div notificationContent = new Div();
        fieldValidationErrors.stream().filter(ValidationMessageUtil::isVisible)
                .filter(field -> field.getMessage().isPresent())
                .map(field -> field.getMessage().get())
                .forEach(message -> {
                    HtmlComponent br = new HtmlComponent("br");
                    notificationContent.add(new Text(message), br);
                });
        return notificationContent;
    }

    private static boolean isVisible(BindingValidationStatus<?> bindingValidationStatus) {
        HasValue<?, ?> field = bindingValidationStatus.getField();
        return field instanceof Component && ((Component) field).isVisible();
    }

    public void showMessage(ErrorResult actionResult) {

        String mainMessage = actionResult.getMainMessage();
        Map<String, String> validationErrors = actionResult.getValidationError();

        Component notificationContent = crateNotificationContent(validationErrors);
        SysUINotificationFactory.showWarn(mainMessage, notificationContent);
        if (null != validationErrors && !validationErrors.isEmpty()) {
            showNotValidFields(validationErrors);
        }
    }

    private Component crateNotificationContent(Map<String, String> validationErrors) {
        if (null == validationErrors || validationErrors.isEmpty()) {
            return new Div();
        }
        Div notificationContent = new Div();
        validationErrors.forEach((key, value) -> {
            HtmlComponent br = new HtmlComponent("br");
            notificationContent.add(new Text(value), br);
        });
        return notificationContent;
    }

    // FIXME check usability for other sys DTO's (
    private void showNotValidFields(Map<String, String> validationErrors) {

        System.out.println("ECHO_Z01 used Validation message Util");

        validationErrors.entrySet().stream().forEach(errorEntry -> {

            String propertyId = errorEntry.getKey();
            Optional<Binder.Binding<UserDto, ?>> bindingOptional = binder.getBinding(propertyId);

            log.info("ErrorKey: " + errorEntry.getKey() + " propertyId: " + propertyId);

            if (bindingOptional.isPresent()) {
                Binder.Binding<UserDto, ?> binding = bindingOptional.get();
                HasValue<?, ?> field = binding.getField();

                if (field instanceof HasValidation) {
                    ((HasValidation) field).setErrorMessage(errorEntry.getValue());
                    ((HasValidation) field).setInvalid(true);

                    log.info("Has validation logic, message: " + errorEntry.getValue());

                } else {
                    log.info("Failed to show validation message! " + propertyId + " " + field.getClass().getSimpleName());
                }
            } else {
                log.info("No binding property! " + propertyId);
            }
        });
    }
}
