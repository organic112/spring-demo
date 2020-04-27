package com.potato112.springdemo.web.ui.foo;

import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.ui.common.SysPage;
import com.potato112.springdemo.web.ui.constants.SysView;
import com.potato112.springdemo.web.service.security.WebSecurityService;
import com.potato112.springdemo.web.form.filters.FilterKey;
import com.potato112.springdemo.web.form.filters.FilteringHelper;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import lombok.AllArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


/**
 * This is example landing page after successful login
 */
@Route(value = OverviewFooView.ROUTE, layout = MainView.class)

@AllArgsConstructor
public class OverviewFooView extends SysPage implements HasUrlParameter<String> {

    public static final String ROUTE = "foo";  // dont user "/" note not "/foo"  but "foo", and as default route  ""
    public static final String VIEW_NAME = SysView.FooBusinessArea.FOO_OVERVIEW_VIEW;

    static final FilterKey FILTER_KEY = FilterKey.FOO_OVERVIEW_FILTERS;
    private final transient WebSecurityService webSecurityService;
    private final FilteringHelper filteringHelper;
    //private final Grid<FooOverviewVO> fooGrid;
    private final Map<String, String> filters = new HashMap<>();

    @PostConstruct
    private void create() {

        // Dummy impl

        Div content = new Div();

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

        content.add(layout);

        this.setContent(content);
    }

    @Override
    protected String getViewName() {
        return VIEW_NAME;
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
