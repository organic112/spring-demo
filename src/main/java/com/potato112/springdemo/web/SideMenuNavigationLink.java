package com.potato112.springdemo.web;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SideMenuNavigationLink {
    private final String label;
    private final VaadinIcon icon;
    private final Class<? extends Component> navigationTarget;
    private final Class<? extends Component> selectingNavigationChainElement;
    private final String uuid;
}
