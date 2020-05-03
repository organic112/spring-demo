package com.potato112.springdemo.web.ui;

import com.potato112.springdemo.web.service.group.GroupPermissionDto;
import com.potato112.springdemo.web.ui.common.DefaultConfirmAction;
import com.potato112.springdemo.web.ui.constants.ViewName;
import com.potato112.springdemo.web.ui.factories.SysButtonFactory;
import com.potato112.springdemo.web.ui.group.GroupDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import javafx.collections.ObservableList;


import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;


/**
 * Has purpose to Edit Grid row details
 */
public class LookupWindow extends Dialog {


    public LookupWindow( Binder<GroupPermissionDto> binder, Button saveButton) {


        GroupPermissionDto dto = binder.getBean();

        VerticalLayout verticalLayout = new VerticalLayout();

        VerticalLayout formVertical = new VerticalLayout();

        HorizontalLayout horizontalLayoutLine2 = new HorizontalLayout();
        horizontalLayoutLine2.setAlignItems(FlexComponent.Alignment.CENTER);

        final ComboBox<ViewName> viewNameCombo = new ComboBox("View name");
        viewNameCombo.setItems(EnumSet.allOf(ViewName.class));
        viewNameCombo.setWidth("300px");

        Checkbox canCreate = new Checkbox("Create");
        Checkbox canUpdate = new Checkbox("Update");
        Checkbox canDelete = new Checkbox("Delete");

        binder.bind(viewNameCombo, "viewName");
        binder.bind(canCreate, "canCreate");
        binder.bind(canUpdate, "canUpdate");
        binder.bind(canDelete, "canDelete");

        formVertical.add(viewNameCombo, canCreate, canUpdate, canDelete);

        Button confirmButton = saveButton;
        Button cancelBtn = new Button("CANCEL");

        horizontalLayoutLine2.add(confirmButton, cancelBtn);
        verticalLayout.add(formVertical, horizontalLayoutLine2);
        this.add(verticalLayout);
    }

}
