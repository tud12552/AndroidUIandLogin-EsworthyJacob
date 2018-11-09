package com.example.jse58.androiduiandlogin_jacobesworthy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private Button mBtnConfirm = null;

    private EditText mEditTxtFirstName = null;
    private EditText mEditTxtLastName = null;
    private EditText mEditTxtUserName = null;
    private EditText mEditTxtBday = null;
    private EditText mEditTxtPhone = null;
    private EditText mEditTxtEmail = null;
    private EditText mEditTxtPswd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mBtnConfirm = (Button)findViewById(R.id.btnConfirmSignup);

        mEditTxtFirstName = (EditText) findViewById(R.id.editTxtFirstName);
        mEditTxtLastName = (EditText) findViewById(R.id.editTxtLastName);
        mEditTxtUserName = (EditText) findViewById(R.id.editTxtUserName);
        mEditTxtBday = (EditText) findViewById(R.id.editTxtBday);
        mEditTxtPhone = (EditText) findViewById(R.id.editTxtPhoneNum);
        mEditTxtPswd = (EditText) findViewById(R.id.editTxtNewPassword);


        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You Have Been Added.",Toast.LENGTH_SHORT).show();
                Intent goToHomeScreen = new Intent(SignUpActivity.this,LoginScreen.class);
                startActivity(goToHomeScreen);
            }
        });
    }
}
