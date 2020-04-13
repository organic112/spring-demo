package com.potato112.springdemo.web.form;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.ExtendedClientDetails;
import com.vaadin.flow.server.VaadinSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExtendedClientDetailsManager {

    public void saveExtendedClientDetails(ExtendedClientDetails extendedClientDetails) {

        log.warn("Echo YY01 ExtendedClientDetailsManager");

        if(null == extendedClientDetails){
            log.warn("extendedClientDetails are null !");
        }
        getCurrentSession().setAttribute("extendedClientDetails", extendedClientDetails);
    }

    private static VaadinSession getCurrentSession() {
        return UI.getCurrent().getSession();
    }
}
