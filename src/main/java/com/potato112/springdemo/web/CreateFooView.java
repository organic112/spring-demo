package com.potato112.springdemo.web;

import com.potato112.springdemo.crud.jdbc.services.JdbcDocLockDao;
import com.potato112.springdemo.crud.jdbc.services.RentalCarDAO;
import com.potato112.springdemo.security.userauthsecurity.authentication.SysView;
import com.potato112.springdemo.security.userauthsecurity.service.WebSecurityService;
import com.potato112.springdemo.web.form.listeners.BinderWithValueChangeListener;
import com.potato112.springdemo.web.form.listeners.DefaultLeaveFormAction;
import com.potato112.springdemo.web.form.vo.FooFormDetailsVO;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeLeaveEvent;
import com.vaadin.flow.router.BeforeLeaveObserver;
import com.vaadin.flow.router.Route;
import org.springframework.security.access.annotation.Secured;


@Route(value = CreateFooView.ROUTE,  layout = MainView.class)
@Secured({
        SysView.FooBusinessArea.FOO_OVERVIEW_VIEW,
        SysView.AdministrationArea.USER_VIEW
})
public class CreateFooView extends SysPage implements BeforeLeaveObserver {

    static final String ROUTE = "foo/create";
    public static final String VIEW_NAME = SysView.FooBusinessArea.FOO_OVERVIEW_VIEW;

    private static final Class<OverviewFooView> BACK_NAVIGATION_TARGET = OverviewFooView.class;
    private static final Class<EditFooView> EDIT_VIEW = EditFooView.class;

    private final BinderWithValueChangeListener<FooFormDetailsVO> binder;

    private WebSecurityService webSecurityService;
    private RentalCarDAO rentalCarDAO;
    private JdbcDocLockDao jdbcDocLockDao;
    private Button button;

    public CreateFooView(WebSecurityService webSecurityService, RentalCarDAO rentalCarDAO, JdbcDocLockDao jdbcDocLockDao) {

        this.webSecurityService = webSecurityService;
        this.rentalCarDAO = rentalCarDAO;
        this.jdbcDocLockDao = jdbcDocLockDao;

        binder = new BinderWithValueChangeListener<>(FooFormDetailsVO.class);

        this.button = new Button("test button");
        create();
    }

    private void create() {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(button);
    }

    @Override
    protected String getViewName() {
        return VIEW_NAME;
    }

    @Override
    public void beforeLeave(BeforeLeaveEvent event) {

        Class<?> navigationTarget = event.getNavigationTarget();

        if (isEditView(navigationTarget)) {
            return;
        }
        if (binder.wasModified()) {
            event.postpone();
            DefaultLeaveFormAction cancelAction = new DefaultLeaveFormAction(event,
                    () -> true,
                    () -> { });
            cancelAction.run();
        }
    }

    private boolean isEditView(Class<?> navigationTarget) {

        return navigationTarget.isAssignableFrom(EDIT_VIEW);
    }

    private void backToOverview() {

        UI.getCurrent().navigate(BACK_NAVIGATION_TARGET);
    }


}
