package com.potato112.springdemo.web;

import com.potato112.springdemo.security.userauthsecurity.authentication.SysRole;
import com.potato112.springdemo.security.userauthsecurity.service.WebSecurityService;
import com.potato112.springdemo.web.form.FilterKey;
import com.potato112.springdemo.web.form.FilteringHelper;
import com.potato112.springdemo.web.form.FooOverviewVO;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.*;
import org.springframework.security.access.annotation.Secured;

import java.util.HashMap;
import java.util.Map;


@Route(value = OverviewFooView.ROUTE)
@Secured({
        SysRole.OwnerRole.ADMIN,
        SysRole.OwnerRole.MANAGER
})
public class OverviewFooView extends SysPage implements HasUrlParameter<String> {

    static final FilterKey FILTER_KEY = FilterKey.FOO_OVERVIEW_FILTERS;
    public static final String ROUTE = "foo";

    private final transient WebSecurityService webSecurityService;
    private final FilteringHelper filteringHelper;
    private final Grid<FooOverviewVO> fooGrid;
    private final Map<String, String> filters = new HashMap<>();

    public OverviewFooView(WebSecurityService webSecurityService, FilteringHelper filteringHelper, Grid<FooOverviewVO> fooGrid) {
        this.webSecurityService = webSecurityService;
        this.filteringHelper = filteringHelper;
        this.fooGrid = fooGrid;

        create();
    }

    private void create() {

        // TODO initialize and create overview
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
