package com.potato112.springdemo.web.ui.user;

import com.potato112.springdemo.web.ui.common.DefaultConfirmAction;
import com.potato112.springdemo.web.ui.constants.SysView;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.service.user.UserService;
import com.potato112.springdemo.web.service.user.UserVo;
import com.potato112.springdemo.web.service.security.WebSecurityService;
import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.ui.common.SysMainActionBar;
import com.potato112.springdemo.web.ui.common.SysPage;
import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;
import com.potato112.springdemo.web.form.listeners.DefaultLeaveFormAction;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.Route;

@Route(value = CreateUserView.ROUTE, layout = MainView.class)
public class CreateUserView extends SysPage implements BeforeLeaveObserver {

    static final String ROUTE = "user/create";
    public static final String VIEW_NAME = SysView.FooBusinessArea.FOO_OVERVIEW_VIEW;


    private static final Class<EditUserView> EDIT_VIEW = EditUserView.class;

    private final BinderWithValueChangeListener<UserVo> binder;
    private final transient UserService userService;
    private Button saveButton;
    private UserForm userForm;

    @Override
    protected String getViewName() {
        return VIEW_NAME;
    }

    public CreateUserView(UserService userService, WebSecurityService webSecurityService) {

        configureCreateUserView();
        this.userService = userService;
        this.binder = new BinderWithValueChangeListener<>(UserVo.class);
        configureUserForm(webSecurityService);
    }

    private void configureCreateUserView() {

        SysMainActionBar mainActionBar = createActionBar();
        this.add(mainActionBar);



        // back button
        // header
        // footer
        // etc.
    }

    private void save() {

        DefaultConfirmAction<UserVo, String> saveAction = new DefaultConfirmAction<>(binder, userService::create, id -> {
            userForm.resetGridIsChanged();
            UI.getCurrent().navigate(EditUserView.class, id);
            // TODO show notification
        });
        saveAction.run();
    }

    private SysMainActionBar createActionBar(){
        SysMainActionBar mainActionBar = new SysMainActionBar();
        saveButton = mainActionBar.configurePrimaryActionButton("SAVE", this::save);
        mainActionBar.configureSecondaryActionBar("CANCEL", this::backToOverview);
        return mainActionBar;
    }

    private void backToOverview() {

        UI.getCurrent().navigate(UserListView.class);
    }

    private void configureUserForm(WebSecurityService webSecurityService) {
        binder.setBean(new UserVo());
        UserFormParametersVo parameters = this.userService.getUserFromParameters();
        UserDetailsAuthority userContext = webSecurityService.getUser();


        userForm = new UserForm(binder, parameters, userContext);
        userForm.setSaveButton(saveButton);
        userForm.add(saveButton);
        this.setContent(userForm);
    }

    @Override
    public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {


        Class<?> navigationTarget = beforeLeaveEvent.getNavigationTarget();
        if (isEditView(navigationTarget)) {
            return;
        }
        if (binder.wasModified() || userForm.gridWasModified()) {

            beforeLeaveEvent.postpone();
            DefaultLeaveFormAction cancelAction = new DefaultLeaveFormAction(beforeLeaveEvent, this::wasModified,
                    () -> { });
            cancelAction.run();
        }
    }

    private boolean wasModified() {

        // TODO add logic with group modification
        return binder.wasModified();
    }



    private boolean isEditView(Class<?> navigationTarget) {

        return navigationTarget.isAssignableFrom(EditUserView.class);
    }
}
