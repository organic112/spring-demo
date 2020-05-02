package com.potato112.springdemo;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class SysNotification extends Notification {

    private static final int NOTIFICATION_TIME_MILLIS = 5000;

    @Getter
    @AllArgsConstructor
    public enum Variant {
        SUCCESS(NotificationVariant.LUMO_SUCCESS),
        WARN(NotificationVariant.LUMO_PRIMARY),
        ERROR(NotificationVariant.LUMO_ERROR),
        ;
        private final NotificationVariant lumoVariant;
    }

    private Div notificationContainer = new Div();


    public SysNotification(String header, Component text, Variant variant) {

        this(variant);
        notificationContainer.add(new H3(header));
        notificationContainer.add(text);
    }

    public SysNotification(String header, String text, Variant variant) {
        this(header, new Text(text), variant);
    }

    public SysNotification(String text, Variant variant) {

        this(variant);
        System.out.println("Notification display: " + text);
        notificationContainer.add(text);
    }

    private SysNotification(Variant variant) {

        notificationContainer.setClassName("sys-notification-content");

        this.add(notificationContainer);
        this.setDuration(NOTIFICATION_TIME_MILLIS);
        this.setPosition(Notification.Position.TOP_CENTER);
        this.addThemeVariants(variant.getLumoVariant());
    }

}
