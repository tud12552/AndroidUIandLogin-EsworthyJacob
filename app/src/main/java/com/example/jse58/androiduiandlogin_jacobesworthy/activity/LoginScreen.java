package com.example.jse58.androiduiandlogin_jacobesworthy.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jse58.androiduiandlogin_jacobesworthy.R;
import com.example.jse58.androiduiandlogin_jacobesworthy.model.entity.dao.UserProfilePersistence;
import com.example.jse58.androiduiandlogin_jacobesworthy.model.entity.entity.UserProfile;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {

    private ArrayList<UserProfile> mUsers;
    private UserProfilePersistence mPresistenceProfile;

    private ImageView mLoginImgView = null;
    private EditText mEditTxtEmail = null;
    private EditText mEditTxtPswd = null;
    private Button mBtnLogin = null;
    private TextView mTxtViewOr = null;
    private Button mBtnSignUp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);



        mTxtViewOr = (TextView)findViewById(R.id.txtViewLoginSuccess);
        mLoginImgView = (ImageView)findViewById(R.id.imageViewLogin);
        mEditTxtEmail = (EditText)findViewById(R.id.editTxtEmail);
        mEditTxtPswd = (EditText) findViewById(R.id.editTxtPassowrd);
        mBtnLogin = (Button)findViewById(R.id.BtnLogin);
        mBtnSignUp = (Button) findViewById(R.id.BtnSignUp);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(),"Login Button Pressed",Toast.LENGTH_SHORT).show();

                if( !mEditTxtEmail.getText().toString().isEmpty() && !mEditTxtPswd.getText().toString().isEmpty())
                {
                    validateInfo(mEditTxtEmail.getText().toString(), mEditTxtPswd.getText().toString());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid credentials.",Toast.LENGTH_SHORT).show();
                }

                Intent intentLoginSuccess = new Intent(LoginScreen.this, LoginSuccessActivity.class);
                intentLoginSuccess.putExtra("NAME", "Jacob");
                intentLoginSuccess.putExtra("SURNAME", "Esworthy");
                intentLoginSuccess.putExtra("BIRTHDAY", "April 20, 1993");

                startActivity(intentLoginSuccess);
            }
        });

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"SignUp Button Pressed",Toast.LENGTH_SHORT).show();

                Intent intentSignUp = new Intent(LoginScreen.this,SignUpActivity.class);
                startActivity(intentSignUp);
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        mPresistenceProfile = new UserProfilePersistence(this);
        mUsers = mPresistenceProfile.getDataFromDB();

    }

    public boolean validateInfo(String user, String pswd)
    {
        boolean b = false;
        for(UserProfile up:mUsers)
        {
            if(up.getEmail().equals(user) || up.getUserName().equals(user)) {
                if (up.getPswd().equals(pswd)) {
                  b = true;
                  break;
                }
            }
            b = false;
        }
        return b;
    }
}
