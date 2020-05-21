package com.potato112.springdemo.web.ui.common.action;

import com.potato112.springdemo.SysUINotificationFactory;
import com.potato112.springdemo.web.ValidationMessageUtil;
import com.potato112.springdemo.web.ui.common.exceptions.SysValidationException;
import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.data.binder.BinderValidationStatus;
import com.vaadin.flow.data.binder.BindingValidationStatus;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ActionHelper<BEAN_TYPE, CONFIRM_RESULT_TYPE> {


    private final Function<BEAN_TYPE, CONFIRM_RESULT_TYPE> confirmAction;
    private final BinderWithValueChangeListener<BEAN_TYPE> binder;
    private final Consumer<CONFIRM_RESULT_TYPE> postConfirmAction;

    public ActionHelper(Function<BEAN_TYPE, CONFIRM_RESULT_TYPE> confirmAction, BinderWithValueChangeListener<BEAN_TYPE> binder,
                        Consumer<CONFIRM_RESULT_TYPE> postConfirmAction) {
        this.confirmAction = confirmAction;
        this.binder = binder;
        this.postConfirmAction = postConfirmAction;
    }

    public boolean isValid() {

        BinderValidationStatus formValidation = binder.validate();
        if (!formValidation.isOk()) {

            List<BindingValidationStatus<?>> fieldValidationErrors = formValidation.getFieldValidationErrors();
            Component detailedMassage = ValidationMessageUtil.crateNotificationContent(fieldValidationErrors);
            SysUINotificationFactory.showWarn("Validation failed, provide correct data.", detailedMassage);
            return false;
        }
        return true;
    }

    public void confirm() {
        BEAN_TYPE bean = binder.getBean();
        try {
            CONFIRM_RESULT_TYPE actionResult = confirmAction.apply(bean);
            postConfirmAction.accept(actionResult);
        } catch (SysValidationException e) {

            System.out.println("NOTIFICATION" + e.getResult());
            new ValidationMessageUtil(binder).showMessage(e.getResult());
        }
    }
}
