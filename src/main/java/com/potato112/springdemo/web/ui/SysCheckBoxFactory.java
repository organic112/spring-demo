package com.potato112.springdemo.web.ui;

import com.vaadin.flow.component.checkbox.Checkbox;

public final class SysCheckBoxFactory {

    private SysCheckBoxFactory() {
    }

    public static Checkbox create(String label) {

        Checkbox checkbox = new Checkbox(label);
        checkbox.setClassName("sys-checkbox");
        return checkbox;
    }
}
