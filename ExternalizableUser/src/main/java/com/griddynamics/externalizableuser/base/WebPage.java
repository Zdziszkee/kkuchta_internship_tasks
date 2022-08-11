package com.griddynamics.externalizableuser.base;

public class WebPage {

    private final User viewer;

    public WebPage(User viewer) {

        this.viewer = viewer;
    }

    public User getViewer() {

        return viewer;
    }

}
