package com.potato112.springdemo.web;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import lombok.Getter;

@Tag("sys-page")
public abstract class SysPage extends Div {

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
