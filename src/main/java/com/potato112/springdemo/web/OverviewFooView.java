package com.potato112.springdemo.web;

import com.potato112.springdemo.security.userauthsecurity.authentication.SysRole;
import com.potato112.springdemo.security.userauthsecurity.service.WebSecurityService;
import com.potato112.springdemo.web.form.filters.FilterKey;
import com.potato112.springdemo.web.form.filters.FilteringHelper;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.PWA;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Route(value = OverviewFooView.ROUTE, layout = MainView.class)
/*@Secured({
        SysRole.OwnerRole.ADMIN,
        SysRole.OwnerRole.MANAGER
})*/

@AllArgsConstructor
public class OverviewFooView extends SysPage implements HasUrlParameter<String> {

    static final FilterKey FILTER_KEY = FilterKey.FOO_OVERVIEW_FILTERS;
    public static final String ROUTE = "/foo"; // note not "/foo" but default route "/"

    private final transient WebSecurityService webSecurityService;
    private final FilteringHelper filteringHelper;
    //private final Grid<FooOverviewVO> fooGrid;
    private final Map<String, String> filters = new HashMap<>();

/*    public OverviewFooView(WebSecurityService webSecurityService, FilteringHelper filteringHelper) {
        this.webSecurityService = webSecurityService;
        this.filteringHelper = filteringHelper;
        // this.fooGrid = fooGrid;

        create();
    }*/

    @PostConstruct
    private void create() {

        // Dummy impl
        Button button = new Button("Test Foo Overview button");
        button.setIcon(VaadinIcon.USER.create());
        Label label = new Label("Test label");

        button.addClickListener(new ComponentEventListener<ClickEvent<Button>>() {
            @Override
            public void onComponentEvent(ClickEvent<Button> buttonClickEvent) {

                System.out.println("button clicked");
            }
        });

        VerticalLayout layout = new VerticalLayout();
        layout.add(label);
        layout.add(button);

        this.setContent(layout);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String voidParam) {

        Location location = beforeEvent.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        this.filteringHelper.applyFiltersFromUrlParameters(FILTER_KEY, queryParameters);
        this.filters.putAll(this.filteringHelper.loadFilters(FILTER_KEY));
        // TODO
    }
}
