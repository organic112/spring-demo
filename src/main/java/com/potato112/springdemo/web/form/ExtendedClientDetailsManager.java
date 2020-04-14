package com.potato112.springdemo.web.form;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.ExtendedClientDetails;
import com.vaadin.flow.server.VaadinSession;
import lombok.extern.slf4j.Slf4j;

/**
 * Sets REST client extended details
 */
@Slf4j
public class ExtendedClientDetailsManager {

    public void saveExtendedClientDetails(ExtendedClientDetails extendedClientDetails) {

        log.info("Echo YY01 ExtendedClientDetailsManager");

        if (null == extendedClientDetails) {
            log.info("Echo YY02 extendedClientDetails are null !");
        }
        getCurrentSession().setAttribute("extendedClientDetails", extendedClientDetails);
    }

    private static VaadinSession getCurrentSession() {

        return UI.getCurrent().getSession();
    }
}
