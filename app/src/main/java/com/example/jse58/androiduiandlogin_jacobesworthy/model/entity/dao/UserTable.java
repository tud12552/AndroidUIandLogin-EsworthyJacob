package com.example.jse58.androiduiandlogin_jacobesworthy.model.entity.dao;

import java.util.UUID;

public class UserTable {

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_NAME_FIRST_NAME = "firstName";
    public static final String COLUMN_NAME_LAST_NAME = "lastName";
    public static final String COLUMN_NAME_USER_NAME = "userName";
    public static final String COLUMN_NAME_BDAY = "Bday";
    public static final String COLUMN_NAME_PHONE_NUM = "phoneNum";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_PHONE_PSWD = "pswd";
    public static long COLUMN_NAME_ID;

    public static String create(){
        return new String ( "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME_ID + " PRIMARY KEY AUTO INCREMENT" +
                COLUMN_NAME_FIRST_NAME + " TEXT," +
                COLUMN_NAME_LAST_NAME + " TEXT," +
                COLUMN_NAME_USER_NAME  + " TEXT," +
                COLUMN_NAME_BDAY + " TEXT," +
                COLUMN_NAME_PHONE_NUM + " TEXT," +
                COLUMN_NAME_EMAIL + " TEXT," +
                COLUMN_NAME_PHONE_PSWD + " TEXT)");
    }

    public static String select(){
        return new String("SELECT * FROM "+TABLE_NAME);

    }

    public static final String delete(){
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}