package com.potato112.springdemo.web;

import com.potato112.springdemo.web.service.security.WebSecurityService;
import com.potato112.springdemo.web.ui.group.GroupsOverview;
import com.potato112.springdemo.web.ui.landing.LandingPageExampleView;
import com.potato112.springdemo.web.ui.user.UserOverview;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;

import java.util.*;

public class SysSideMenuLayout extends HorizontalLayout implements AfterNavigationObserver {

    private static final List<SideMenuNavigationLink> SIDE_MENU_LINKS = Arrays.asList(
            new SideMenuNavigationLink("Landing page", VaadinIcon.HAND, LandingPageExampleView.class, MainView.class, "landingPageMenuLink"),
            new SideMenuNavigationLink("Users", VaadinIcon.HAND, UserOverview.class, MainView.class, "usersMenuLink"),
            new SideMenuNavigationLink("Groups", VaadinIcon.HAND, GroupsOverview.class, MainView.class, "groupsMenuLink")
    );

    private final Tabs navigationTabs;
    private Map<Class<? extends Component>, Tab> navigationElementWithCorrespondingTabs = new HashMap<>();

    public SysSideMenuLayout(WebSecurityService webSecurityService) {

        this.navigationTabs = new Tabs();
        navigationTabs.setClassName("sidebar-menu-items-container");
        navigationTabs.setOrientation(Tabs.Orientation.VERTICAL);
        navigationTabs.setAutoselect(false);

        this.setWidthFull();
        this.setMargin(false);
        this.setPadding(false);
        this.setSpacing(false);

        Component sideMenuBar = buildSideMenu(webSecurityService);
        add(sideMenuBar);
    }

    private Div buildSideMenu(WebSecurityService webSecurityService) {

        Div sideMenuWindow = new Div();
        sideMenuWindow.setClassName("sidebar-menu-window");

        VerticalLayout sideMenuBar = new VerticalLayout();
        sideMenuBar.setClassName("sidebar-menu");
        sideMenuBar.add(this.navigationTabs);

        SideMenuItemFactory itemFactory = new SideMenuItemFactory();
        SideMenuNavigationTabFactory navigationTabFactory = new SideMenuNavigationTabFactory(itemFactory);

        for (SideMenuNavigationLink linkEntry : SIDE_MENU_LINKS) {
            if (shouldAddLink(webSecurityService, linkEntry)) {
                Tab tab = navigationTabFactory.buildItemNavigationLink(linkEntry);
                this.navigationElementWithCorrespondingTabs.put(linkEntry.getSelectingNavigationChainElement(), tab);
                this.navigationTabs.add(tab);
            }
        }
        sideMenuBar.add(new SysDividerFactory().createHorizontalLineDivider());

        // TODO more elements

        sideMenuWindow.add(sideMenuBar);
        return sideMenuWindow;
    }

    private boolean shouldAddLink(WebSecurityService webSecurityService, SideMenuNavigationLink linkEntry) {
        return webSecurityService.isAccessGranted(linkEntry.getNavigationTarget());
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        setSelectedTabAccordingToCurrentView(afterNavigationEvent);
    }

    private void setSelectedTabAccordingToCurrentView(AfterNavigationEvent afterNavigationEvent) {

        for (HasElement activeChainEntry : afterNavigationEvent.getActiveChain()) {

            Element element = activeChainEntry.getElement();
            if (null != element) {

                Optional<Component> navigationChainComponentOp = element.getComponent();
                if (!navigationChainComponentOp.isPresent()) {
                    continue;
                }
                Component navigationChainComponent = navigationChainComponentOp.get();
                Tab temp = navigationElementWithCorrespondingTabs.get(navigationChainComponent.getClass());
                if (null != temp) {
                    this.navigationTabs.setSelectedTab(temp);
                    return;
                }
            }
        }
    }
}
