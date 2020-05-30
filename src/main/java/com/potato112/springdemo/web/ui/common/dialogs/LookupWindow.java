package com.potato112.springdemo.web.ui.common.dialogs;

import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;
import com.potato112.springdemo.web.service.group.model.GroupPermissionDto;
import com.potato112.springdemo.web.ui.common.action.DefaultConfirmAction;
import com.potato112.springdemo.web.ui.constants.ViewName;
import com.potato112.springdemo.web.ui.factories.SysButtonFactory;
import com.potato112.springdemo.web.service.group.model.GroupDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.EnumSet;


/**
 * Has purpose to Edit Grid row details
 */
public class LookupWindow extends Dialog {

    private BinderWithValueChangeListener<GroupPermissionDto> binder = new BinderWithValueChangeListener<>(GroupPermissionDto.class);

    public LookupWindow(GroupPermissionDto groupPermissionDto, Grid<GroupPermissionDto> grid, DefaultConfirmAction<GroupDto, GroupDto> saveAction) {

        this.binder.setBean(groupPermissionDto);

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


        SysButtonFactory sysButtonFactory = new SysButtonFactory();
        Button confirmButton = sysButtonFactory.createSaveButton(saveAction);

        confirmButton.addClickListener(buttonClickEvent -> {

                // FIXME workaround to avoid refresh breaking keys when using grid.getDataProvider().refreshAll();
                grid.setDataProvider(grid.getDataProvider());
                this.close();
        });

        Button cancelBtn = new Button("CANCEL");
        cancelBtn.addClickListener(buttonClickEvent -> {

            this.close();
        });

        horizontalLayoutLine2.add(confirmButton, cancelBtn);
        verticalLayout.add(formVertical, horizontalLayoutLine2);
        this.add(verticalLayout);




    }

}
