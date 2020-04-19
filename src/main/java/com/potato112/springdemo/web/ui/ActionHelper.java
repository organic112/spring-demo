package com.potato112.springdemo.web.ui;

import com.potato112.springdemo.web.SysValidationException;
import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;
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
            //Component detailedMessage = fixme (notification)
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
            // show notification
        }
    }
}
