package com.example.jse58.androiduiandlogin_jacobesworthy;
import java.util.ArrayList;

public interface IPersistence<T> {

    public void insert(T t);

    public ArrayList<T> getDataFromDB();

}
