package com.questappx.anniversary.AdsWorking;


public class AdClass
{
    String link;
    int drawable;
    int feature;
    String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    AdClass(String link, int drawable, int feature, String appName)
    {
        this.link = link;
        this.drawable = drawable;
        this.feature = feature;
        this.appName = appName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public int getFeature() {
        return feature;
    }

    public void setFeature(int feature) {
        this.feature = feature;
    }


}


