package com.potato112.springdemo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;

public class SysUINotificationFactory {

    public static void showSuccess(String message) {
        SysNotification notification = new SysNotification(message, SysNotification.Variant.SUCCESS);
        notification.open();
    }

    public static void showWarn(String message) {
        SysNotification notification = new SysNotification(message, SysNotification.Variant.WARN);
        notification.open();
    }

    public static void showError(String message) {
        SysNotification notification = new SysNotification(message, SysNotification.Variant.ERROR);
        notification.open();
    }

    public static void showWarn(String message, String details) {
        showWarn(message, new Text(details));
    }

    public static void showWarn(String message, Component details) {

        SysNotification notification = new SysNotification(message, details, SysNotification.Variant.WARN);
        notification.open();
    }
}
