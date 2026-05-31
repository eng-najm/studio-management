package com.studio.features.dashboard.model;

public class DashItem {
    private String name;
    private String icon;
    private String route;

    public DashItem(String name, String icon, String route) {
        this.name = name;
        this.icon = icon;
        this.route = route;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

}
