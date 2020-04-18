package com.potato112.springdemo.web.ui;

import com.potato112.springdemo.security.userauthsecurity.model.UserDetailsAuthority;
import com.potato112.springdemo.security.userauthsecurity.service.UserVo;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends Div {

    public UserForm(Binder<UserVo> binder, UserDetailsAuthority userContext) {

        UserBaseSection userBaseSection = new UserBaseSection(binder);

        Div baseSectionContent = new Div(userBaseSection);

        // TODO add groups grid

        this.add(baseSectionContent);

    }


}
