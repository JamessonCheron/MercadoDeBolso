package com.example.mercadodebolso.mercadodebolso.view.adapters;

/**
 * Created by jamesson on 5/22/2016.
 */
public class NavItem {
    private String mTitle;
    private String mSubtitle;
    int mIcon;

    public NavItem(String title, String subtitle) {
        mTitle = title;
        mSubtitle = subtitle;
        //mIcon = icon;
    }

    public String getmSubtitle() {
        return mSubtitle;
    }

    public void setmSubtitle(String mSubtitle) {
        this.mSubtitle = mSubtitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
