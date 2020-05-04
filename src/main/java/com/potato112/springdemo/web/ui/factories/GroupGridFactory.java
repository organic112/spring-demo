package com.potato112.springdemo.web.ui.factories;

import com.potato112.springdemo.web.service.group.model.GroupOverviewResponseDto;
import com.potato112.springdemo.web.service.group.model.GroupPermissionDto;
import com.potato112.springdemo.web.service.group.model.GroupService;
import com.potato112.springdemo.web.service.search.QueryUtils;
import com.potato112.springdemo.web.service.security.model.UserAuthorityDto;
import com.potato112.springdemo.web.service.user.model.UserDto;
import com.potato112.springdemo.web.ui.common.grid.SysGridHelper;
import com.potato112.springdemo.web.ui.constants.ViewName;
import com.potato112.springdemo.web.ui.group.EditGroupView;
import com.potato112.springdemo.web.service.group.model.GroupDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupGridFactory implements GridFactory<GroupOverviewResponseDto> {

    private GroupService groupService;
    private UserAuthorityDto userAuthorityDto;
    private Map<String, String> filters;


    public GroupGridFactory(GroupService groupService, UserAuthorityDto userAuthorityDto, Map<String, String> filters) {
        this.groupService = groupService;
        this.userAuthorityDto = userAuthorityDto;
        this.filters = filters;
    }

    @Override
    public Grid<GroupOverviewResponseDto> create() {

        Grid<GroupOverviewResponseDto> groupGrid = new Grid<>(GroupOverviewResponseDto.class);
        SysGridHelper.initializeGridStyle(groupGrid);

        CallbackDataProvider<GroupOverviewResponseDto, Map<String, String>> dataProvider = DataProvider.fromFilteringCallbacks(
                query -> groupService.getGroups(QueryUtils.buildSearchDto(query, filters)).stream(),
                query -> groupService.count(QueryUtils.buildSearchDtoForCountQuery(query, filters))
        );
        groupGrid.setDataProvider(dataProvider);
        buildColumns(groupGrid);
        return groupGrid;
    }

    void buildColumns(Grid<GroupOverviewResponseDto> groupsGrid) {

        SysGridHeaderFactory headerFactory = new SysGridHeaderFactory();
        groupsGrid.setColumns();

        Grid.Column<GroupOverviewResponseDto> groupNameColumn = groupsGrid.addColumn(GroupDto.AttributeName.GROUP_NAME);
        groupNameColumn.setHeader(headerFactory.createHeader("Group name"));

        Grid.Column<GroupOverviewResponseDto> groupPermissionsColumn = groupsGrid.addComponentColumn(gropuDto -> {

            List<String> permissionNames = gropuDto.getGroupPermissions().stream()
                    .map(GroupPermissionDto::getViewName)
                    .map(ViewName::getName)
                    .collect(Collectors.toList());
            return SysGridCellListItemFactory.create(permissionNames);
        });

        groupPermissionsColumn.setKey(UserDto.AttributeName.USER_GROUPS);
        groupPermissionsColumn.setSortable(false);
        groupPermissionsColumn.setHeader(headerFactory.createHeader("Group permissions"));


        if (getUserEditPermission()) {
            new CommonGridColumnFactory(userAuthorityDto).addEditRowActionColumn(groupsGrid, this::navigateToEditView);
        }
    }

    private void navigateToEditView(GroupOverviewResponseDto group) {

        UI.getCurrent().navigate(EditGroupView.class, group.getId());
    }

    boolean getUserEditPermission() {
        return null != userAuthorityDto && userAuthorityDto.canUpdate();
    }
}
