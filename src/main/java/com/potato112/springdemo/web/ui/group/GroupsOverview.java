package com.potato112.springdemo.web.ui.group;


import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.form.filters.FilterKey;
import com.potato112.springdemo.web.form.filters.FilteringHelper;
import com.potato112.springdemo.web.service.group.GroupOverviewResponseDto;

import com.potato112.springdemo.web.service.group.GroupService;
import com.potato112.springdemo.web.service.group.RestGroupServiceImpl;
import com.potato112.springdemo.web.service.security.UserAuthService;
import com.potato112.springdemo.web.service.security.model.UserAuthorityVo;
import com.potato112.springdemo.web.ui.common.SysPage;
import com.potato112.springdemo.web.ui.common.SysUtilActionBar;
import com.potato112.springdemo.web.ui.constants.SysView;
import com.potato112.springdemo.web.ui.factories.GroupGridFactory;
import com.potato112.springdemo.web.ui.factories.SysButtonFactory;
import com.potato112.springdemo.web.ui.user.CreateUserView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.router.Route;
import org.picketlink.idm.impl.cache.GroupSearchImpl;
import org.springframework.security.access.annotation.Secured;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Route(value = GroupsOverview.ROUTE, layout = MainView.class)
@Secured(value = GroupsOverview.VIEW_NAME)
public class GroupsOverview extends SysPage {


    public static final String ROUTE = "group";
    public static final String VIEW_NAME = SysView.AdministrationArea.GROUP_VIEW; // change to GROUP_VIEW

    private final transient GroupService groupService;

    private final Grid<GroupOverviewResponseDto> groupsGrid;
    private Set<String> selectedGridRowIds;

    private Map<String, String> filters;

    @Override
    protected String getViewName() {
        return GroupsOverview.VIEW_NAME;
    }


    public GroupsOverview(GroupService groupService, UserAuthService userAuthService, FilteringHelper filteringHelper) {

        this.userAuthService = userAuthService;
        UserAuthorityVo authorityVo = getUserCUDAuthorization();

        this.groupService = groupService;
        this.filters = filteringHelper.loadFilters(FilterKey.GROUP_FILTERS);
        this.groupsGrid = new GroupGridFactory(this.groupService, authorityVo, filters)
                .create();

        this.groupsGrid.addSelectionListener(this::getSelectedRowIds);
        this.selectedGridRowIds = new HashSet<>();

        SysButtonFactory buttonFactory = new SysButtonFactory();
        Button deleteButton = buttonFactory.createDeleteButton(this::deleteSelectedGroups);

        Button addButton = buttonFactory.createNavigationToCreateButton(CreateUserView.class); //fixme create group view
        SysUtilActionBar actionBar = new SysUtilActionBar();
        actionBar.addItem(addButton);
        actionBar.addItem(deleteButton);
        Div content = new Div();
        content.add(actionBar, groupsGrid);
        this.setContent(content);
    }

    private void getSelectedRowIds(SelectionEvent<Grid<GroupOverviewResponseDto>, GroupOverviewResponseDto> event) {
        this.selectedGridRowIds = event.getAllSelectedItems().stream()
                .map(GroupOverviewResponseDto::getId)
                .collect(Collectors.toSet());
    }

    private void deleteSelectedGroups() {
        // TODO
    }
}
