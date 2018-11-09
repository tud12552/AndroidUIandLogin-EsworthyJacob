package com.example.jse58.androiduiandlogin_jacobesworthy.model.entity.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.jse58.androiduiandlogin_jacobesworthy.model.entity.entity.UserProfile;

public class UserProfilePersistence implements IPersistence
{

    public AllowDatabase allowDatabase;

    public UserProfilePersistence (Context context){
        this.allowDatabase = new AllowDatabase(context);
    }

    @Override
    public void insert(Object o) {
        UserProfile userProfile = (UserProfile) o;

        SQLiteDatabase sqLiteDatabase = allowDatabase.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserTable.COLUMN_NAME_FIRST_NAME, userProfile.getName());
        contentValues.put(UserTable.COLUMN_NAME_LAST_NAME, userProfile.getLastName());
        contentValues.put(UserTable.COLUMN_NAME_USER_NAME, userProfile.getUserName());
        contentValues.put(UserTable.COLUMN_NAME_PHONE_NUM, userProfile.getPhoneNumber());
        contentValues.put(UserTable.COLUMN_NAME_EMAIL, userProfile.getEmail());
        contentValues.put(UserTable.COLUMN_NAME_PHONE_PSWD, userProfile.getPswd());
        contentValues.put(UserTable.COLUMN_NAME_BDAY, String.valueOf(userProfile.getBday()));

        // Insert the ContentValues into the Movie table.
        sqLiteDatabase.insert(UserTable.TABLE_NAME, null, contentValues);

        sqLiteDatabase.close();
    }

    @Override
    public ArrayList getDataFromDB()
    {
        ArrayList<UserProfile> users = null;

        SQLiteDatabase sqLiteDatabase = allowDatabase.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(UserTable.select(), null);

        cursor.moveToFirst();
        users = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){
            do {

                String firstName = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_NAME_FIRST_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_NAME_LAST_NAME));
                String userName = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_NAME_USER_NAME));
                String bday = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_NAME_BDAY));
                String phoneNum = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_NAME_PHONE_NUM));
                String email = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_NAME_EMAIL));
                String pswd = cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_NAME_PHONE_PSWD));

                UserProfile userProfile = new UserProfile(firstName, lastName, userName, phoneNum, email, bday, pswd);
                users.add(userProfile);

            } while (cursor.moveToNext()) ;
        }

        return users;
    }

}
