package com.potato112.springdemo.web;

import com.potato112.springdemo.security.userauthsecurity.authentication.SysRole;
import com.potato112.springdemo.security.userauthsecurity.service.WebSecurityService;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;

import java.util.Objects;

@Slf4j
/*@Secured({
        SysRole.OwnerRole.ADMIN,
        SysRole.OwnerRole.MANAGER,
        SysRole.DistributorRole.USER,
        SysRole.DistributorRole.MANAGER,
        SysRole.SpecialistRole.USER,
})*/
public class MainView extends VerticalLayout implements RouterLayout, PageConfigurator {

    private Div contentContainer = new Div();

    public MainView(WebSecurityService webSecurityService){
        this.setSpacing(false);

        log.info("Echo01 Create MainView...");

        Div div = new Div();
        div.add(contentContainer);
        add(div);
        Button button = new Button("test Main View button");
        add(button);
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

        log.info("Echo03 configure page...");
    }
}
