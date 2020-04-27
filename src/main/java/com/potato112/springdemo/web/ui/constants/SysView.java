package com.potato112.springdemo.web.ui.constants;

/**
 * Stores Constants related to System web page views.
 * Intended to use in security context (user access)
 */
public class SysView {

    private SysView() {
    }

    /**
     * represents views related to System Administration (Admin access)
     */
    public static final class AdministrationArea {
        public static final String USER_VIEW = "USER_VIEW";
        public static final String GROUP_VIEW = "GROUP_VIEW";

        private AdministrationArea() {
        }
    }

    /**
     * represents application business views
     */
    public static final class FooBusinessArea {
        public static final String FOO_OVERVIEW_VIEW = "FOO_OVERVIEW_VIEW";

        private FooBusinessArea() {
        }
    }

    public static String[] getAllViews() {

        String[] ALL_VIEWS = {

                // FIXME: refactor: get all views with recursion - not explicit
                AdministrationArea.USER_VIEW,
                AdministrationArea.GROUP_VIEW,
                FooBusinessArea.FOO_OVERVIEW_VIEW
        };
        return ALL_VIEWS;
    }
}
