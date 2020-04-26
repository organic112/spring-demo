package com.potato112.springdemo.web;

import com.potato112.springdemo.security.userauthsecurity.UserAuthService;
import com.potato112.springdemo.security.userauthsecurity.authentication.SysView;
import com.potato112.springdemo.security.userauthsecurity.model.UserAuthority;
import com.potato112.springdemo.security.userauthsecurity.service.WebSecurityService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Div;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Slf4j
@Tag("sys-page")
//@AllArgsConstructor
public abstract class SysPage extends Div {


    protected UserAuthService userAuthService;
    protected UserAuthority userAuthority;

    private Div blankPage;
    private Div headerContainer;
    private Div contentContainer;
    private Div footerContainer;

    @Getter
    private Component header;
    @Getter
    private Component content;
    @Getter
    private Component footer;

    /**
     * Checks user CRUD (CUD) permissions for current ViewName
     * IF no CUD permissions fetched sets all CUD permissions to false (read only)
     */
    protected void initUserCUDAuthorization() {
        Optional<UserAuthority> userAuthorityOp = userAuthService.getGrantedAuthorityByViewName(getViewName());
        System.out.println("INIT01 USER AUTHORITY INITIALIZATION EXISTS:" + userAuthorityOp.isPresent());
        if (!userAuthorityOp.isPresent()) {
            log.info("User authority not found for view name:" + getViewName());
            userAuthService.invalidateUserSession();
        } else {
            this.userAuthority = userAuthorityOp.get();
        }
    }

    // provides view name for user access authorization
    protected abstract String getViewName();

    // default constructor without injections should be provided
    protected SysPage() {

        this.blankPage = new Div();

        Div topBar = new Div();
        this.add(topBar);

        this.headerContainer = new Div();
        topBar.add(headerContainer);

        // TODO add some action bar / customized menu etc.

        this.contentContainer = new Div();
        blankPage.add(contentContainer);

        this.footerContainer = new Div();
        blankPage.add(footerContainer);

        this.add(blankPage);
    }

    protected void setHeader(Component newHeader) {
        this.headerContainer.removeAll();
        if (null == newHeader) {
            return;
        }
        this.header = newHeader;
        this.headerContainer.add(newHeader);
    }

    protected void setContent(Component content) {
        contentContainer.removeAll();

        if (null == content) {
            return;
        }
        this.content = content;
        contentContainer.add(content);
    }

    protected void setFooter(Component newFooter) {
        this.footerContainer.removeAll();
        if (null == newFooter) {
            return;
        }
        this.footer = newFooter;
        this.footerContainer.add(newFooter);
    }

}
