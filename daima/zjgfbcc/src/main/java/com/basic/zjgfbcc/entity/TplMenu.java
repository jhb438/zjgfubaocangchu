package com.basic.zjgfbcc.entity;

import java.util.List;

public class TplMenu {
    public TplMenu()
    {
        spread = false;
    }
    public String name ;
    public String title ;
    public String icon ;
    public String url ;
    public boolean spread ;
    public List<TplMenu> list ;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }
    public List<TplMenu> getList() {
        return list;
    }

    public void setList(List<TplMenu> list) {
        this.list = list;
    }
}
