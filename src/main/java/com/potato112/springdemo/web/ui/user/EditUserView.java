package com.potato112.springdemo.web.ui.user;

import com.potato112.springdemo.web.ui.constants.SysView;
import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.ui.common.SysPage;
import com.vaadin.flow.router.*;

@Route(value = EditUserView.ROUTE, layout = MainView.class)
public class EditUserView extends SysPage implements HasUrlParameter<String>, BeforeLeaveObserver {

    public static final String ROUTE = "user/edit";
    public static final String VIEW_NAME = SysView.FooBusinessArea.FOO_OVERVIEW_VIEW;
    // private UserService userService;

    @Override
    protected String getViewName() {
        return VIEW_NAME;
    }


    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {

        // UserVo userVo = userService.getUser(s).o

    }

    @Override
    public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {

    }
}
