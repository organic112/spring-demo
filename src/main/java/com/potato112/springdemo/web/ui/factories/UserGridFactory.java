package com.potato112.springdemo.web.ui.factories;

import com.potato112.springdemo.web.service.search.QueryUtils;
import com.potato112.springdemo.web.service.security.model.UserAuthorityVo;

import com.potato112.springdemo.web.service.user.UsersService;
import com.potato112.springdemo.web.service.user.UserDto;
import com.potato112.springdemo.web.ui.common.SortingHelper;
import com.potato112.springdemo.web.ui.common.SortingKey;
import com.potato112.springdemo.web.ui.common.SysGridHelper;
import com.potato112.springdemo.web.ui.user.EditUserView;
import com.potato112.springdemo.web.ui.user.UserOverviewResponseDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;

import java.util.List;
import java.util.Map;

public class UserGridFactory implements GridFactory<UserOverviewResponseDto> {

    private static final SortingKey SORTING_KEY = SortingKey.USER_SORTING;

    private UsersService usersService;
    private UserAuthorityVo userAuthorityVo;
    private Map<String, String> filters;

    public UserGridFactory(UsersService usersService, UserAuthorityVo userAuthorityVo, Map<String, String> filters) {
        this.usersService = usersService;
        this.userAuthorityVo = userAuthorityVo;
        this.filters = filters;
    }

    @Override
    public Grid<UserOverviewResponseDto> create() {

        SortingHelper<UserOverviewResponseDto> sortingHelper = new SortingHelper<>();
        Grid<UserOverviewResponseDto> userGrid = new Grid<>(UserOverviewResponseDto.class);
        SysGridHelper.initializeGridStyle(userGrid);

        userGrid.addSelectionListener(selectionEvent -> {
        });  // FIXME empty?

        buildColumns(userGrid);
        //UserDetailsAuthority userDetailsAuthority = securityService.getUser();
        //UserGroupVO userGroup = userDetailsAuthority.getAuthorities().g
        //filters.put("userGroupId", userGroup.getId());

        sortingHelper.loadSorting(userGrid, SortingKey.USER_SORTING);

        CallbackDataProvider<UserOverviewResponseDto, Map<String, String>> provider = DataProvider.fromFilteringCallbacks(
                query -> usersService.getUser(QueryUtils.buildSearchVo(query, filters)).stream(),
                query -> usersService.count(QueryUtils.buildSearchVoForCountQuery(query, filters))
        );

/*        Grid.Column<UserOverviewResponseDto> userGroupsColumn = userGrid.addComponentColumn(gropuDto -> {

            List<String> groupNames = gropuDto.getUserGroups();
            return SysGridCellListItemFactory.create(groupNames);
        });

        userGroupsColumn.setKey(UserVo.AttributeName.USER_GROUPS);
        userGroupsColumn.setSortable(false);*/
        //userGroupsColumn.setHeader(headerFactory.createHeader("Group permissions"));



        userGrid.setPageSize(10);
        userGrid.setDataProvider(provider);
        userGrid.addSortListener(event -> sortingHelper.setSortingInSession(event, SortingKey.USER_SORTING));
        return userGrid;
    }

    private void buildColumns(Grid<UserOverviewResponseDto> userGrid) {

        SysGridHeaderFactory headerFactory = new SysGridHeaderFactory();
        userGrid.setColumns();

        Grid.Column<UserOverviewResponseDto> emailColumn = userGrid.addColumn(UserDto.AttributeName.EMAIL);
        emailColumn.setHeader(headerFactory.createHeader("Email"));

        Grid.Column<UserOverviewResponseDto> firstNameColumn = userGrid.addColumn(UserDto.AttributeName.FIRST_NAME);
        firstNameColumn.setHeader(headerFactory.createHeader("First Name"));

        Grid.Column<UserOverviewResponseDto> lastNameColumn = userGrid.addColumn(UserDto.AttributeName.LAST_NAME);
        lastNameColumn.setHeader(headerFactory.createHeader("Last Name"));

        Grid.Column<UserOverviewResponseDto> groupsColumn = userGrid.addComponentColumn(
                userOverviewVo -> {
                    List<String> orgNames = userOverviewVo.getUserGroups();
                    return SysGridListItemFactory.create(orgNames);
                });
        groupsColumn.setKey(UserDto.AttributeName.USER_GROUPS);
        groupsColumn.setSortable(false);
        groupsColumn.setHeader(headerFactory.createHeader("Groups"));

        Grid.Column<UserOverviewResponseDto> phoneColumn = userGrid.addColumn(UserDto.AttributeName.PHONE);
        phoneColumn.setHeader(headerFactory.createHeader("Phone"));

        Grid.Column<UserOverviewResponseDto> lockedColumn = userGrid.addColumn(user -> user.getLocked().getStatus());
        lockedColumn.setKey(UserDto.AttributeName.LOCKED);
        lockedColumn.setHeader(headerFactory.createHeader("Locked"));

        if (getUserEditPermission()) {
            new CommonGridColumnFactory(userAuthorityVo).addActionColumn(userGrid, this::navigateToEditView);
        }

        userGrid.setSortableColumns(UserDto.AttributeName.EMAIL, UserDto.AttributeName.FIRST_NAME, UserDto.AttributeName.LAST_NAME,
                UserDto.AttributeName.PHONE, UserDto.AttributeName.LOCKED);
    }

    private void navigateToEditView(UserOverviewResponseDto user) {
        UI.getCurrent().navigate(EditUserView.class, user.getId());
    }

    boolean getUserEditPermission() {
        return null != userAuthorityVo && userAuthorityVo.canUpdate();
    }

}
