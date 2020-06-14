package com.potato112.springdemo.web.ui.login;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;

public abstract class AbstractLoginView extends AppLayout implements PageConfigurator {

    private HorizontalLayout centerLayout;
    private VerticalLayout mainLayout = new VerticalLayout();

    public AbstractLoginView() {
        buildCenterLayout();
    }

    abstract Component buildMainComponent();

    private void buildCenterLayout() {

        centerLayout = new HorizontalLayout();
        centerLayout.setId("loginLayout");

        Component mainComponent = buildMainComponent();
        centerLayout.add(mainComponent);
        centerLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        buildFinalView(centerLayout);
    }

    private void buildFinalView(HorizontalLayout centerLayout) {

        mainLayout.setSizeFull();
        mainLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        Div loginForWrapper = new Div(centerLayout);
        Div blur = new Div();
        blur.add(loginForWrapper);
        mainLayout.add(blur);
        getElement().appendChild(mainLayout.getElement());
    }

    @Override
    public void configurePage(InitialPageSettings initialPageSettings) {

        // FIXME configure
        initialPageSettings.addLink("shortcut icon", "icons/favicon.ico");
        initialPageSettings.addFavIcon("icon", "icons/favicon.ico", "192x192");
    }

    // TODO create header, footer etc.
}
