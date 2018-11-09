package com.example.jse58.androiduiandlogin_jacobesworthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity {

    private Button mBtnViewAllUsers = null;

    private TextView mTxtViewWelcomeMessage = null;

    private String mName = null;
    private String mSurName = null;
    private String mBday = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        Intent data = getIntent();

        mBtnViewAllUsers = (Button)findViewById(R.id.btnViewAllUsers);

        mTxtViewWelcomeMessage = (TextView)findViewById(R.id.txtViewLoginSuccess);

        mName = data.getStringExtra("NAME");
        mSurName = data.getStringExtra("SURNAME");
        mBday = data.getStringExtra("BIRTHDAY");

        StringBuilder strBuild = new StringBuilder(200);
        strBuild.append("Welcome " + mName + " " + mSurName + "\n" + "Your Birthday is:\n" + mBday);

        mTxtViewWelcomeMessage.setText(strBuild);

        mBtnViewAllUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showAllUsers = new Intent(LoginSuccessActivity.this,ViewAllUsers.class);

                startActivity(showAllUsers);
            }
        });
    }
}
