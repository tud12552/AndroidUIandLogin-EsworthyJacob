package com.example.jse58.androiduiandlogin_jacobesworthy;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jse58.androiduiandlogin_jacobesworthy.R;

// I added this comment to test the new GitHub integration at school using PSU PC's.

public class CustomListAdapter extends ArrayAdapter {

    private final Activity mContext;
    private final String[] mFirstNameArray;
    private final String[] mLastNameArray;
    private final String[] mBdayArray;

    public CustomListAdapter(Activity context, String[] first, String[] last, String[] bday)
    {
        super(context,R.layout.listview_row, first);

        this.mContext = context;
        this.mFirstNameArray = first;
        this.mLastNameArray = last;
        this.mBdayArray = bday;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.listview_row, null,true);

        TextView txtViewFirstName = (TextView) rowView.findViewById(R.id.txtViewFirstName);
        TextView txtViewLastName = (TextView) rowView.findViewById(R.id.txtViewLastName);
        TextView txtViewBday = (TextView) rowView.findViewById(R.id.txtViewBday);

        txtViewFirstName.setText(mFirstNameArray[position]);
        txtViewLastName.setText(mLastNameArray[position]);
        txtViewBday.setText(mBdayArray[position]);

        return rowView;
    }

}
