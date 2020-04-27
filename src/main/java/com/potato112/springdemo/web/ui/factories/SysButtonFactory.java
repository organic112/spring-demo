package com.potato112.springdemo.web.ui.factories;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class SysButtonFactory {

    public Button createPrimaryButton(String text) {
        Button button = prepareButton(text);
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        return button;
    }

    public Button createSecondaryButton(String text) {
        return prepareButton(text);
    }

    public Button createPlainButton(String text) {
        Button button = prepareButton(text);
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        return button;
    }

    public Button createPlainButton(String text, Icon icon) {
        Button button = prepareButton(text);
        button.setIcon(icon);
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        return button;
    }

    public Button createEditButtonWithIconAfterText(String text, Component icon) {
        Button button = createPlainButton(text);
        button.setIcon(icon);
        button.setIconAfterText(true);
        return button;
    }

    public Button createEditButton(Component icon, Runnable navigationAction) {
        Button button = prepareButton();
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        button.setIcon(icon);
        button.addClickListener(click -> navigationAction.run());
        return button;
    }

    public Button createNavigationToCreateButton(Class<? extends Component> createViewClass) {

        Icon icon = new Icon(VaadinIcon.PLUS_CIRCLE_O);
        Button addButton = createPlainButton("Create", icon);
        addButton.addClickListener(event -> UI.getCurrent().navigate(createViewClass));
        return addButton;
    }


    private Button prepareButton(String text) {
        Button button = new Button(text);
        // set class name
        return button;
    }

    private Button prepareButton() {
        Button button = new Button();
        // set class name
        return button;
    }

    public Button createDeleteButton(Runnable action) {

        Icon icon = new Icon(VaadinIcon.PLUS_CIRCLE_O);
        Button deleteButton = createPlainButton("Delete", icon);
        deleteButton.addClickListener(event -> action.run());
        return deleteButton;
    }
}