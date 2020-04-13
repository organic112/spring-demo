package com.potato112.springdemo.web.form;

import com.potato112.springdemo.security.userauthsecurity.service.WebSecurityService;
import com.potato112.springdemo.web.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * Before routing gets to view this listener check if user is logged in.
 */
@Slf4j
@Component
@AllArgsConstructor
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

    private final transient WebSecurityService webSecurityService;

    @Override
    public void serviceInit(ServiceInitEvent event) {

        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.getPage().retrieveExtendedClientDetails(details
                    -> new ExtendedClientDetailsManager()
                    .saveExtendedClientDetails(details));
            ui.addBeforeEnterListener(this::beforeEnter);
        });
    }

    private void beforeEnter(BeforeEnterEvent event) {

        log.info("XX01 before enter listener!");

        UI ui = event.getUI();
        VaadinSession session = ui.getSession();
        WebBrowser browser = session.getBrowser();
        Class<?> navigationTarget = event.getNavigationTarget();

        if (browser.isEdge() && !navigationTarget.isAssignableFrom(UnsupportedBrowserView.class)) {

            log.info("XX02 before enter listener!");
            event.rerouteTo(UnsupportedBrowserView.class);
        }

        if (!webSecurityService.isAccessGranted(event.getNavigationTarget())) {
            log.info("XX03 before enter listener!");

            if (webSecurityService.isUserLoggedIn()) {

                log.info("XX04 before enter listener!");
                event.rerouteToError(NotFoundException.class);

            } else {

                log.info("XX05 before enter listener!");
                log.info("reroute to login view page...");
                event.rerouteTo(LoginView.class); // FIXME LoginView.class
            }
        }
        log.info("XX06 Before enter listener: Access is granted");
    }
}