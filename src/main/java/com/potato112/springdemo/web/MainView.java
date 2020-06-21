package com.potato112.springdemo.web;

import com.potato112.springdemo.web.service.security.UserAuthService;
import com.potato112.springdemo.web.service.security.WebSecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;

import java.util.Objects;

@Secured({})
@Slf4j
@CssImport("./styles/shared-styles.css")
//@CssImport(value = "./styles/vaadin-grid-sys.css", themeFor = "vaadin-grid")
//@CssImport("./frontend/styles/shared-styles.css")
public class MainView extends VerticalLayout implements RouterLayout, PageConfigurator {

    private Div contentContainer = new Div();

    public MainView(UserAuthService userAuthService, WebSecurityService webSecurityService) {

        this.addClassName("main-layout");
        this.setSpacing(false);
        this.setSizeFull();

        Component header = new SysHeader(userAuthService, webSecurityService);
        add(header);

        Label topLabel = new Label("MAIN VIEW LAYOUT, below routed content:");
        add(topLabel);

        // move as content to MainBar
        VerticalLayout tabsMenu = new tabsLayout(webSecurityService);
        add(tabsMenu);


        this.expand(tabsMenu);

        Div mainContentWindow = new Div();
        mainContentWindow.setClassName("main-content-window");

        Div mainContentWrapper = new Div();
        mainContentWrapper.setClassName("main-content-container-wrapper");
        mainContentWrapper.add(contentContainer);

        mainContentWindow.add(mainContentWrapper);
        tabsMenu.add(mainContentWindow);
        tabsMenu.expand(mainContentWindow);


        Div footer = new Div();
        footer.setClassName("sys-main-footer");
        Label mainViewLayoutLabel = new Label("Copyright: open source");
        footer.add(mainViewLayoutLabel);
        //add(footer);
    }

    @Override
    public void showRouterLayoutContent(HasElement hasElement) {

        log.info("Echo02 show router content...");

        Objects.requireNonNull(hasElement);
        Objects.requireNonNull(hasElement.getElement());
        contentContainer.removeAll();
        contentContainer.getElement().appendChild(hasElement.getElement());
    }

    @Override
    public void configurePage(InitialPageSettings initialPageSettings) {

        //new CommonPageConfiguratio().
        log.info("Echo05 configure page...");
    }
}
