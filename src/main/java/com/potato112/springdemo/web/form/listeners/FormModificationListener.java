package com.potato112.springdemo.web.form.listeners;

import com.vaadin.flow.component.HasValue.ValueChangeEvent;
import com.vaadin.flow.component.HasValue.ValueChangeListener;
import lombok.Getter;

public class FormModificationListener implements ValueChangeListener {

    @Getter
    private boolean changed = false;

    public FormModificationListener() {
    }

    @Override
    public void valueChanged(ValueChangeEvent event) {

        if (event.isFromClient()) {
            changed = true;
        }
    }
}
