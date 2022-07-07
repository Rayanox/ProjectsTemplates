package com.karavel.batch.seo.linking.core.common;


import com.karavel.batch.seo.linking.common.beans.Theme;
import com.karavel.batch.seo.linking.common.beans.ZoneTouristiqueNode;

public class ZoneNode {

    private ZoneTouristiqueNode zone;
    private Theme theme;

    public static ZoneNode buildFrom(ZoneTouristiqueNode node,  Theme theme) {
        ZoneNode zoneNode = new ZoneNode();
        zoneNode.setZone(node);
        zoneNode.setTheme(theme);
        return zoneNode;
    }

    public ZoneTouristiqueNode getZone() {
        return zone;
    }

    public void setZone(ZoneTouristiqueNode zone) {
        this.zone = zone;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
