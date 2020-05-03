package com.potato112.springdemo.web.ui.group;

import com.potato112.springdemo.SysUINotificationFactory;
import com.potato112.springdemo.web.MainView;
import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;
import com.potato112.springdemo.web.form.listeners.DefaultLeaveFormAction;
import com.potato112.springdemo.web.service.group.GroupService;
import com.potato112.springdemo.web.service.security.UserAuthService;
import com.potato112.springdemo.web.service.security.model.UserAuthorityVo;
import com.potato112.springdemo.web.ui.common.DefaultConfirmAction;
import com.potato112.springdemo.web.ui.common.SysPage;
import com.potato112.springdemo.web.ui.common.SysUtilActionBar;
import com.potato112.springdemo.web.ui.constants.SysView;
import com.potato112.springdemo.web.ui.factories.SysButtonFactory;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.*;

import java.util.NoSuchElementException;


@Route(value = EditGroupView.ROUTE, layout = MainView.class)
public class EditGroupView extends SysPage implements HasUrlParameter<String>, BeforeLeaveObserver {

    public static final String ROUTE = "group/edit";
    public static final String VIEW_NAME = SysView.AdministrationArea.GROUP_VIEW;

    public static final Class<GroupsOverview> BACK_NAVIGATION_TARGET = GroupsOverview.class;

    private final BinderWithValueChangeListener<GroupDto> binder;

    private GroupService groupService;


    private Button saveButton;
    private GroupForm groupForm;

    public EditGroupView(GroupService groupService, UserAuthService userAuthService) {
        this.userAuthService = userAuthService;

        this.groupService = groupService;
        this.binder = new BinderWithValueChangeListener<>(GroupDto.class);

    }

    @Override
    protected String getViewName() {
        return VIEW_NAME;
    }

    /**
     * When redirected from Overview sets edit form with current item  data by item id.
     */
    @Override
    public void setParameter(BeforeEvent beforeEvent, String id) {

        GroupDto groupDto = groupService.getGroup(id).orElseThrow(NoSuchElementException::new);
        this.binder.setBean(groupDto);
        configureGroupForm();
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
                    () -> {
                    });
            cancelAction.run();
        }
    }

    private void configureGroupForm() {

        this.configureBackButton(BACK_NAVIGATION_TARGET);

        SysUtilActionBar mainActionBar = createActionBar();
        this.add(mainActionBar);

        UserAuthorityVo authorityVo = getUserCUDAuthorization();
        groupForm = new GroupForm(binder, authorityVo, saveButton);
        //groupForm.setSaveButton(saveButton);
        this.setContent(groupForm);
    }

    private SysUtilActionBar createActionBar() {
        SysUtilActionBar sysUtilActionBar = new SysUtilActionBar();
        this.saveButton = createSaveButton();
        sysUtilActionBar.addItem(this.saveButton);
        return sysUtilActionBar;
    }

    private Button createSaveButton() {

        SysButtonFactory sysButtonFactory = new SysButtonFactory();
        DefaultConfirmAction<GroupDto, GroupDto> saveAction = new DefaultConfirmAction<>(binder, groupService::update, this::confirmAction);

        return sysButtonFactory.createSaveButton(saveAction);
    }

    private void confirmAction(GroupDto updatedUser) {
        this.binder.setBean(updatedUser);
        //this.userForm.setUpdatedUserGroups(updatedUser);  FIXME
        this.groupForm.resetGridIsChanged();
        SysUINotificationFactory.showSuccess("Group successfully updated.");
    }

    private boolean isEditView(Class<?> navigationTarget) {

        return navigationTarget.isAssignableFrom(EditGroupView.class);
    }

    private boolean wasModified() {
        // TODO add logic with group modification
        return binder.wasModified();
    }
}
