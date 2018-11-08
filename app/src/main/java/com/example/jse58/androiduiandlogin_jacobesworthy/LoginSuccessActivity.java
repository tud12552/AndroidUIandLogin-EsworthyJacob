package com.example.jse58.androiduiandlogin_jacobesworthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity {

    private TextView mTxtViewWelcomeMessage = null;

    private String mName = null;
    private String mSurName = null;
    private String mBday = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        Intent data = getIntent();

        mTxtViewWelcomeMessage = (TextView)findViewById(R.id.txtViewLoginSuccess);

        mName = data.getStringExtra("NAME");
        mSurName = data.getStringExtra("SURNAME");
        mBday = data.getStringExtra("BIRTHDAY");

        StringBuilder strBuild = new StringBuilder(200);
        strBuild.append("Welcome " + mName + " " + mSurName + "\n" + "Your Birthday is:\n" + mBday);

        mTxtViewWelcomeMessage.setText(strBuild);
    }
}
