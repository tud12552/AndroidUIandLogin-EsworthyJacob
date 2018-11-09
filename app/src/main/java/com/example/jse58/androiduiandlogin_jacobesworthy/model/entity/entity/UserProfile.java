package com.example.jse58.androiduiandlogin_jacobesworthy.model.entity.entity;

import android.support.annotation.Nullable;
import java.io.Serializable;

public class UserProfile implements Serializable {

    private String mFirstName;
    private String mLastName;
    private String mUserName;
    private String mBday;
    private String mPhoneNum;
    private String mEmail;
    private String mPswd;


    public UserProfile(String firstname, String lastname, String username, String phonenumber, String email, String pswd, String bday ) {
        this.mFirstName = firstname;
        this.mLastName = lastname;
        this.mUserName = username;
        this.mPhoneNum = phonenumber;
        this.mEmail = email;
        this.mPswd = pswd;
        this.mBday = bday;
    }


    public String getName() {
        return mFirstName;
    }

    public void setName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName()
    {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUsername(String username) {
        this.mUserName = username;
    }

    public String getBday() {
        return mBday;
    }

    public void setBday(String bday) {
        this.mBday = bday;
    }

    public String getPhoneNumber() {
        return mPhoneNum;
    }

    public void setPhone(String phonenumber) {
        this.mPhoneNum = phonenumber;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getPswd() {
        return mPswd;
    }

    public void setPassword(String pswd) {
        this.mPswd = pswd;
    }

}