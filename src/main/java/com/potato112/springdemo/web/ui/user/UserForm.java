package com.potato112.springdemo.web.ui.user;

import com.potato112.springdemo.web.service.security.model.UserDetailsAuthority;
import com.potato112.springdemo.web.service.user.model.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.binder.Binder;

public class UserForm extends Div {

    public UserForm(Binder<UserDto> binder, UserFormParametersDto parameters , UserDetailsAuthority userContext) {

        UserBaseSection userFormBaseSection = new UserBaseSection(binder);
        Div baseSectionContent = new Div(userFormBaseSection);
        this.add(baseSectionContent);
    }

    public void setSaveButton(Button button){
        // TODO set this button for user Group wrapper
    }

    public void resetGridIsChanged(){
        System.out.println("FIXME implement grid reset !");
    }

    public boolean gridWasModified(){
        //TODO add logic here
        return true;
    }
}
