package com.potato112.springdemo.web;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;

public class SysMainActionBar extends Div {

    private Div primaryButtonContainer;
    private Button primaryButton;
    private Div secondaryButtonContainer;
    private Button secondaryButton;


    public SysMainActionBar() {
        this.primaryButtonContainer = new Div();
        this.add(primaryButtonContainer);
        this.secondaryButtonContainer = new Div();
        this.add(secondaryButtonContainer);
    }

    public Button configurePrimaryActionButton(String text, Runnable action) {

        this.primaryButton = new SysButtonFactory().createPrimaryButton(text);
        setButtonInSlot(primaryButtonContainer, primaryButton, action);
        return this.primaryButton;
    }

    private void setButtonInSlot(Div slot, Button button, Runnable action) {
        slot.removeAll();
        button.addClickListener(event -> action.run());
        slot.add(button);
    }
}
