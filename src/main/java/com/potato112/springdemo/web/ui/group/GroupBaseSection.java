package com.potato112.springdemo.web.ui.group;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class GroupBaseSection extends FormLayout {


    private TextField groupNameField = new TextField("Group name");

    public GroupBaseSection(Binder<GroupDto> binder) {

        binder.bind(groupNameField, GroupDto.AttributeName.GROUP_NAME);

        add(groupNameField);
    }
}
