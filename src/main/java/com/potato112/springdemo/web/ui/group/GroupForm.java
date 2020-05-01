package com.potato112.springdemo.web.ui.group;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.binder.Binder;

public class GroupForm extends Div {


    public GroupForm(Binder<GroupDto> binder) {

        GroupBaseSection groupBaseSection = new GroupBaseSection(binder);
        Div baseSectionContent = new Div(groupBaseSection);

        // TODO add permissions grid
        this.add(baseSectionContent);
    }

    public void resetGridIsChanged(){

        System.out.println("FIXME implement grid reset !");
    }

    public boolean gridWasModified(){

        //TODO add logic here
        return true;
    }
}
