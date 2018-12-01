package com.example.jse58.androiduiandlogin_jacobesworthy;

public class RecyclerViewItem {

    private String fullName, birthday;

    public RecyclerViewItem(String fullName, String birthday) {
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
