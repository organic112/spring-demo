package com.potato112.springdemo.web.ui.group;


import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;
import com.potato112.springdemo.web.form.listeners.DefaultLeaveFormAction;
import com.potato112.springdemo.web.service.group.GroupService;
import com.potato112.springdemo.web.ui.common.DefaultConfirmAction;
import com.potato112.springdemo.web.ui.common.SysMainActionBar;
import com.potato112.springdemo.web.ui.common.SysPage;
import com.potato112.springdemo.web.ui.constants.SysView;
import com.potato112.springdemo.web.ui.user.EditUserView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.Route;

@Route(value = CreateGroupView.ROUTE, layout = MainView.class)
public class CreateGroupView extends SysPage implements BeforeLeaveObserver {

    static final String ROUTE = "group/create";
    public static final String VIEW_NAME = SysView.AdministrationArea.GROUP_VIEW;

    private final BinderWithValueChangeListener<GroupDto> binder;
    private final transient GroupService groupService;

    private GroupForm groupForm;
    private Button saveButton;

    public CreateGroupView(GroupService groupService) {

        configureCreateUserView();

        this.groupService = groupService;
        this.binder = new BinderWithValueChangeListener<>(GroupDto.class);

        configureGroupForm();
    }

    @Override
    protected String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public void beforeLeave(BeforeLeaveEvent beforeLeaveEvent) {
        Class<?> navigationTarget = beforeLeaveEvent.getNavigationTarget();
        if (isEditView(navigationTarget)) {
            return;
        }
        if (binder.wasModified() || groupForm.gridWasModified()) {
            beforeLeaveEvent.postpone();
            DefaultLeaveFormAction cancelAction = new DefaultLeaveFormAction(beforeLeaveEvent, this::wasModified,
                    () -> { });
            cancelAction.run();
        }
    }

    private void configureGroupForm() {
        binder.setBean(new GroupDto());
        groupForm = new GroupForm(binder);
        groupForm.add(saveButton);
        this.setContent(groupForm);
    }

    private void configureCreateUserView() {

        SysMainActionBar mainActionBar = createActionBar();
        this.add(mainActionBar);
    }

    private SysMainActionBar createActionBar() {
        SysMainActionBar mainActionBar = new SysMainActionBar();
        saveButton = mainActionBar.configurePrimaryActionButton("SAVE", this::save);
        mainActionBar.configureSecondaryActionBar("CANCEL", this::backToOverview);
        return mainActionBar;
    }

    private void save() {

        DefaultConfirmAction<GroupDto, String> saveAction = new DefaultConfirmAction<>(binder, groupService::create, id -> {
            groupForm.resetGridIsChanged();
            UI.getCurrent().navigate(EditUserView.class, id);
        });
        saveAction.run();
    }

    private void backToOverview() {
        UI.getCurrent().navigate(GroupsOverview.class);
    }

    private boolean wasModified() {
        // TODO add logic with group modification
        return binder.wasModified();
    }

    private boolean isEditView(Class<?> navigationTarget) {
        return navigationTarget.isAssignableFrom(EditGroupView.class);
    }
}
