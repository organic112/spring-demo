package com.potato112.springdemo.web.ui.factories;

import com.potato112.springdemo.web.service.search.QueryUtils;
import com.potato112.springdemo.web.service.security.model.UserAuthority;
import com.potato112.springdemo.web.service.user.UserService;
import com.potato112.springdemo.web.service.user.UserVo;
import com.potato112.springdemo.web.ui.common.SortingHelper;
import com.potato112.springdemo.web.ui.common.SortingKey;
import com.potato112.springdemo.web.ui.common.SysGridHelper;
import com.potato112.springdemo.web.ui.user.EditUserView;
import com.potato112.springdemo.web.ui.user.UserOverviewResponseVo;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;

import java.util.List;
import java.util.Map;

public class UserGridFactory {


    private static final SortingKey SORTING_KEY = SortingKey.USER_SORTING;
    private UserAuthority userAuthority;

    public Grid<UserOverviewResponseVo> create(UserService userService, UserAuthority userAuthority, Map<String, String> filters) {

        this.userAuthority = userAuthority;
        SortingHelper<UserOverviewResponseVo> sortingHelper = new SortingHelper<>();
        Grid<UserOverviewResponseVo> userGrid = new Grid<>(UserOverviewResponseVo.class);
        SysGridHelper.initializeGridStyle(userGrid);

        userGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        userGrid.setWidth("900px");
        userGrid.addSelectionListener(selectionEvent -> {
        });  // FIXME empty?

        buildColumns(userGrid);

        //UserDetailsAuthority userDetailsAuthority = securityService.getUser();


        //UserGroupVO userGroup = userDetailsAuthority.getAuthorities().g
        //filters.put("userGroupId", userGroup.getId());

        sortingHelper.loadSorting(userGrid, SortingKey.USER_SORTING);

        CallbackDataProvider<UserOverviewResponseVo, Map<String, String>> provider = DataProvider.fromFilteringCallbacks(
                query -> userService.getUsers(QueryUtils.buildSearchVo(query, filters)).stream(),
                query -> userService.count(QueryUtils.buildSearchVoForCountQuery(query, filters))
        );
        userGrid.setPageSize(10);
        userGrid.setDataProvider(provider);
        userGrid.addSortListener(event -> sortingHelper.setSortingInSession(event, SortingKey.USER_SORTING));
        return userGrid;
    }

    private void buildColumns(Grid<UserOverviewResponseVo> userGrid) {

        SysGridHeaderFactory headerFactory = new SysGridHeaderFactory();
        userGrid.setColumns();

        Grid.Column<UserOverviewResponseVo> emailColumn = userGrid.addColumn(UserVo.AttributeName.EMAIL);
        emailColumn.setHeader(headerFactory.createActionsHeader("Email"));

        Grid.Column<UserOverviewResponseVo> firstNameColumn = userGrid.addColumn(UserVo.AttributeName.FIRST_NAME);
        firstNameColumn.setHeader(headerFactory.createActionsHeader("First Name"));

        Grid.Column<UserOverviewResponseVo> lastNameColumn = userGrid.addColumn(UserVo.AttributeName.LAST_NAME);
        lastNameColumn.setHeader(headerFactory.createActionsHeader("Last Name"));

        Grid.Column<UserOverviewResponseVo> groupsColumn = userGrid.addComponentColumn(
                userOverviewVo -> {
                    List<String> orgNames = userOverviewVo.getGroups();
                    return SysGridListItemFactory.create(orgNames);
                });
        groupsColumn.setKey(UserVo.AttributeName.USER_GROUPS);
        groupsColumn.setSortable(false);
        groupsColumn.setHeader(headerFactory.createHeader("Groups"));

        Grid.Column<UserOverviewResponseVo> phoneColumn = userGrid.addColumn(UserVo.AttributeName.PHONE);
        phoneColumn.setHeader(headerFactory.createActionsHeader("Phone"));

        Grid.Column<UserOverviewResponseVo> lockedColumn = userGrid.addColumn(user -> user.getLocked().getStatus());
        lockedColumn.setKey(UserVo.AttributeName.LOCKED);
        lockedColumn.setHeader(headerFactory.createActionsHeader("Locked"));

        if (getUserEditPermission()) {
            new CommonGridColumnFactory(userAuthority).addActionColumn(userGrid, this::navigateToEditView);
        }

        userGrid.setSortableColumns(UserVo.AttributeName.EMAIL, UserVo.AttributeName.FIRST_NAME, UserVo.AttributeName.LAST_NAME,
                UserVo.AttributeName.PHONE, UserVo.AttributeName.LOCKED);
    }

    private void navigateToEditView(UserOverviewResponseVo user) {
        UI.getCurrent().navigate(EditUserView.class, user.getId());
    }

    boolean getUserEditPermission() {
        return null != userAuthority && userAuthority.canUpdate();
    }

}
