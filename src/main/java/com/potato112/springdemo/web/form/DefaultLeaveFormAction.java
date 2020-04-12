package com.potato112.springdemo.web.form;

import com.vaadin.flow.router.BeforeLeaveEvent;

import java.util.function.Supplier;

public class DefaultLeaveFormAction implements Runnable {

    private final BeforeLeaveEvent event;
    private final Supplier<Boolean> showConfirmation;
    private final Runnable confirmAction;

    public DefaultLeaveFormAction(BeforeLeaveEvent event, Supplier<Boolean> showConfirmation, Runnable confirmAction) {
        this.event = event;
        this.showConfirmation = showConfirmation;
        this.confirmAction = confirmAction;
    }

    @Override
    public void run() {

        event.postpone();
        if (showConfirmation.get()) {

            // TODO implement confirmation
            System.out.println("Confirmation Dialog");

        } else {
            onConfirmLeave();
        }
    }

    private void onConfirmLeave() {
        confirmAction.run();
        event.getContinueNavigationAction().proceed();
    }

}
