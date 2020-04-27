package com.potato112.springdemo.web.ui.factories;

import com.potato112.springdemo.web.service.group.GroupOverviewResponseDto;
import com.potato112.springdemo.web.service.group.GroupPermissionDto;
import com.potato112.springdemo.web.service.group.GroupService;
import com.potato112.springdemo.web.service.search.QueryUtils;
import com.potato112.springdemo.web.service.security.model.UserAuthorityVo;
import com.potato112.springdemo.web.ui.constants.ViewName;
import com.potato112.springdemo.web.ui.group.GroupDto;
import com.potato112.springdemo.web.ui.user.EditUserView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.CallbackDataProvider;
import com.vaadin.flow.data.provider.DataProvider;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupGridFactory implements GridFactory<GroupOverviewResponseDto> {

    private GroupService groupService;
    private UserAuthorityVo userAuthorityVo;
    private Map<String, String> filters;

    public GroupGridFactory(GroupService groupService, UserAuthorityVo userAuthorityVo, Map<String, String> filters) {
        this.groupService = groupService;
        this.userAuthorityVo = userAuthorityVo;
        this.filters = filters;
    }

    @Override
    public Grid<GroupOverviewResponseDto> create() {

        Grid<GroupOverviewResponseDto> groupGrid = new Grid<>(GroupOverviewResponseDto.class);

        CallbackDataProvider<GroupOverviewResponseDto, Map<String, String>> dataProvider = DataProvider.fromFilteringCallbacks(
                query -> groupService.getGroups(QueryUtils.buildSearchVo(query, filters)).stream(),
                query -> groupService.count(QueryUtils.buildSearchVoForCountQuery(query, filters))
        );
        groupGrid.setPageSize(10);
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

            List<String> permissionNames = gropuDto.getPermissionDtoList().stream()
                    .map(GroupPermissionDto::getViewName)
                    .map(ViewName::getName)
                    .collect(Collectors.toList());
            return SysGridCellListItemFactory.create(permissionNames);
        });
        groupPermissionsColumn.setHeader(headerFactory.createHeader("Group permissions"));

        if (getUserEditPermission()) {
            new CommonGridColumnFactory(userAuthorityVo).addActionColumn(groupsGrid, this::navigateToEditView);
        }
    }

    private void navigateToEditView(GroupOverviewResponseDto group) {
        UI.getCurrent().navigate(EditUserView.class, group.getId()); //fixme Edit group view
    }

    boolean getUserEditPermission() {
        return null != userAuthorityVo && userAuthorityVo.canUpdate();
    }
}
