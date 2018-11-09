package com.example.jse58.androiduiandlogin_jacobesworthy.model.entity.dao;
import java.util.ArrayList;

public interface IPersistence<T> {

    public void insert(T t);

    public void delete(T t);

    public void edit (T t);

    public ArrayList<T> getDataFromDB();

}
