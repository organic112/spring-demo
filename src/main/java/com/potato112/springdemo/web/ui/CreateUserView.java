package com.potato112.springdemo.web.ui;

import com.potato112.springdemo.security.userauthsecurity.model.UserDetailsAuthority;
import com.potato112.springdemo.security.userauthsecurity.service.UserService;
import com.potato112.springdemo.security.userauthsecurity.service.UserVo;
import com.potato112.springdemo.security.userauthsecurity.service.WebSecurityService;
import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.SysPage;
import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.Route;

@Route(value = CreateUserView.ROUTE, layout = MainView.class)
public class CreateUserView extends SysPage implements BeforeLeaveObserver {

    static final String ROUTE = "user/create";

    private static final Class<EditUserView> EDIT_VIEW = EditUserView.class;

    private final BinderWithValueChangeListener<UserVo> binder;
    private final transient UserService userService;
    private Button saveButton;
    private UserForm userForm;

    public CreateUserView(UserService userService, WebSecurityService webSecurityService) {
        configureCreateUserView();
        this.userService = userService;
        this.binder = new BinderWithValueChangeListener<>(UserVo.class);
        configureUserForm(webSecurityService);
    }

    private void configureCreateUserView() {
        // back button
        // header
        // footer
        // etc.
    }

    private void configureUserForm(WebSecurityService webSecurityService) {
        binder.setBean(new UserVo());
        UserDetailsAuthority userContext = webSecurityService.getUser();
        userForm = new UserForm(binder, userContext);
        userForm.add(saveButton);
        this.setContent(userForm);
    }

    @Override
    public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {

    }
}
