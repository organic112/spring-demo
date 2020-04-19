package com.potato112.springdemo.web.ui;

import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;

import java.util.function.Consumer;
import java.util.function.Function;

public class DefaultConfirmAction<BEAN_TYPE, CONFIRM_RESULT_TYPE> implements Runnable {

    private final ActionHelper actionHelper;

    public DefaultConfirmAction(BinderWithValueChangeListener<BEAN_TYPE> binder,
                                Function<BEAN_TYPE, CONFIRM_RESULT_TYPE> confirmAction,
                                Consumer<CONFIRM_RESULT_TYPE> postConfirmAction) {

        this.actionHelper = new ActionHelper(confirmAction, binder, postConfirmAction);
    }

    @Override
    public void run() {
        if (!actionHelper.isValid()) {
            return;
        }
        actionHelper.confirm();
    }
}
