package com.potato112.springdemo.web.ui.group;


import com.potato112.springdemo.web.ui.constants.SysView;
import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.ui.common.SysPage;
import com.vaadin.flow.router.Route;
import org.springframework.security.access.annotation.Secured;


@Route(value = GroupsOverview.ROUTE, layout = MainView.class)
@Secured(value = GroupsOverview.VIEW_NAME)
public class GroupsOverview extends SysPage {


    public static final String ROUTE = "group";
    public static final String VIEW_NAME = SysView.AdministrationArea.USER_VIEW; // change to GROUP_VIEW

    @Override
    protected String getViewName() {
        return VIEW_NAME;
    }





}
