package com.potato112.springdemo.web.ui.user;

import com.potato112.springdemo.web.service.security.UserAuthService;
import com.potato112.springdemo.web.ui.common.SysUtilActionBar;
import com.potato112.springdemo.web.ui.constants.SysView;
import com.potato112.springdemo.web.service.user.UserService;
import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.ui.factories.SysButtonFactory;
import com.potato112.springdemo.web.ui.common.SysPage;
import com.potato112.springdemo.web.form.filters.FilterKey;
import com.potato112.springdemo.web.form.filters.FilteringHelper;
import com.potato112.springdemo.web.ui.factories.UserGridFactory;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.selection.SelectionEvent;
import com.vaadin.flow.router.Route;
import org.springframework.security.access.annotation.Secured;

import java.util.HashSet;
import java.util.Map;

import java.util.Set;
import java.util.stream.Collectors;

@Route(value = UserListView.ROUTE, layout = MainView.class)
@Secured(value = UserListView.VIEW_NAME)
public class UserListView extends SysPage {

    public static final String ROUTE = "user";
    public static final String VIEW_NAME = SysView.AdministrationArea.USER_VIEW;

    private final transient UserService userService;
    private final Grid<UserOverviewResponseVo> usersGrid;
    private Set<String> selectedUsersIdRows;

    @Override
    protected String getViewName(){
        return UserListView.VIEW_NAME;
    }

    // search
    // map filters
    private final Map<String, String> filters;

    public UserListView(UserService userService, UserAuthService userAuthService, FilteringHelper filteringHelper) {

        this.userAuthService = userAuthService;
        initUserCUDAuthorization();

        this.userService = userService;
        this.filters = filteringHelper.loadFilters(FilterKey.USER_FILTERS);
        this.usersGrid = new UserGridFactory().create(this.userService, userAuthority, filters);
        this.usersGrid.addSelectionListener(this::getSelectedUserIds);
        this.selectedUsersIdRows = new HashSet<>();

        //this.setHeader();

        SysButtonFactory buttonFactory = new SysButtonFactory();
        Button deleteButton = buttonFactory.createDeleteButton(this::deleteSelectedUsers);

        Button addButton = buttonFactory.createNavigationToCreateButton(CreateUserView.class);
        SysUtilActionBar actionBar = new SysUtilActionBar();
        actionBar.addItem(addButton);
        actionBar.addItem(deleteButton);

        Div content = new Div();
        content.add(actionBar, usersGrid);
        this.setContent(content);
    }

    private void getSelectedUserIds(SelectionEvent<Grid<UserOverviewResponseVo>, UserOverviewResponseVo> gridUserVoSelectionEvent) {

        this.selectedUsersIdRows = gridUserVoSelectionEvent.getAllSelectedItems().stream()
                .map(UserOverviewResponseVo::getId)
                .collect(Collectors.toSet());
    }

    private void deleteSelectedUsers() {

        if (this.selectedUsersIdRows.isEmpty()) {
            // TODO notification
        } else {
            confirmDeletion();
        }
    }

    private void confirmDeletion() {
        // TODO confirmation dialog
    }
}
