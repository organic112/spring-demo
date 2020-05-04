package com.potato112.springdemo.web.ui.user;

import com.potato112.springdemo.SysUINotificationFactory;
import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;
import com.potato112.springdemo.web.form.listeners.DefaultLeaveFormAction;
import com.potato112.springdemo.web.service.security.WebSecurityService;
import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.service.user.model.UserDto;
import com.potato112.springdemo.web.service.user.model.UsersService;
import com.potato112.springdemo.web.ui.common.action.DefaultConfirmAction;
import com.potato112.springdemo.web.ui.common.action.SysUtilActionBar;
import com.potato112.springdemo.web.ui.constants.SysView;
import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.ui.common.SysPage;
import com.potato112.springdemo.web.ui.factories.SysButtonFactory;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.*;

import java.util.NoSuchElementException;

@Route(value = EditUserView.ROUTE, layout = MainView.class)
public class EditUserView extends SysPage implements HasUrlParameter<String>, BeforeLeaveObserver {

    public static final String ROUTE = "user/edit";
    public static final String VIEW_NAME = SysView.AdministrationArea.USER_VIEW;
    public static final Class<UserOverview> BACK_NAVIGATION_TARGET = UserOverview.class;

    private final BinderWithValueChangeListener<UserDto> binder;

    private UsersService usersService;
    private WebSecurityService securityService;

    private Button saveButton;
    private UserForm userForm;

    public EditUserView(UsersService usersService, WebSecurityService securitySettings) {
        this.securityService = securitySettings;
        this.usersService = usersService;
        this.binder = new BinderWithValueChangeListener<>(UserDto.class);
    }

    @Override
    protected String getViewName() {
        return VIEW_NAME;
    }

    /**
     * When redirected from Overview sets edit form with current user data by user id.
     */
    @Override
    public void setParameter(BeforeEvent beforeEvent, String param) {

        UserDto userDto = usersService.getUser(param).orElseThrow(NoSuchElementException::new);
        this.binder.setBean(userDto);
        configureUserForm();
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
                    () -> {
                    });
            cancelAction.run();
        }
    }

    private SysUtilActionBar createActionBar() {
        SysUtilActionBar sysUtilActionBar = new SysUtilActionBar();
        this.saveButton = createSaveButton();
        sysUtilActionBar.addItem(this.saveButton);
        return sysUtilActionBar;
    }

    private Button createSaveButton() {

        SysButtonFactory sysButtonFactory = new SysButtonFactory();
        DefaultConfirmAction<UserDto, UserDto> saveAction = new DefaultConfirmAction<>(binder, usersService::update, this::confirmAction);

        return sysButtonFactory.createSaveButton(saveAction);
    }

    private void confirmAction(UserDto updatedUser) {
        this.binder.setBean(updatedUser);
        //this.userForm.setUpdatedUserGroups(updatedUser);  FIXME
        this.userForm.resetGridIsChanged();
        SysUINotificationFactory.showSuccess("User successfully updated.");
    }

    private void configureUserForm() {

        this.configureBackButton(BACK_NAVIGATION_TARGET);

        SysUtilActionBar mainActionBar = createActionBar();
        this.add(mainActionBar);

        UserFormParametersDto parametersDto = this.usersService.getUserFromParameters();

        UserDetailsAuthority userContext = securityService.getUser();
        userForm = new UserForm(binder, parametersDto, userContext);
        userForm.setSaveButton(saveButton);
        this.setContent(userForm);
    }

    private boolean isEditView(Class<?> navigationTarget) {

        return navigationTarget.isAssignableFrom(EditUserView.class);
    }

    private boolean wasModified() {

        // TODO add logic with group modification
        return binder.wasModified();
    }
}
