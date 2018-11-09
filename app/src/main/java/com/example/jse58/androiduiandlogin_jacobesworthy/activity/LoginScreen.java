package com.example.jse58.androiduiandlogin_jacobesworthy.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jse58.androiduiandlogin_jacobesworthy.R;
import com.example.jse58.androiduiandlogin_jacobesworthy.model.entity.dao.UserProfilePersistence;
import com.example.jse58.androiduiandlogin_jacobesworthy.model.entity.entity.UserProfile;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {

    private ArrayList<UserProfile> mUsers;
    private UserProfilePersistence mPresistenceProfile;

    private ListView listView;

    private String[] firstNames;
    private String[] lastNames;
    private String[] bdays;

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

        CustomListAdapter customList = new CustomListAdapter(this,firstNames,lastNames,bdays);

        listView = (ListView)findViewById(R.id.listViewID);
        listView.setAdapter(customList);



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
                String[] b = new String[4];
                Toast.makeText(getApplicationContext(),"Login Button Pressed",Toast.LENGTH_SHORT).show();

                if( !mEditTxtEmail.getText().toString().isEmpty() && !mEditTxtPswd.getText().toString().isEmpty())
                {
                    b = validateInfo(mEditTxtEmail.getText().toString(), mEditTxtPswd.getText().toString());

                    if(b[3].matches("true")) {
                        Toast.makeText(getApplicationContext(), "Logging In.", Toast.LENGTH_SHORT).show();

                        Intent intentLoginSuccess = new Intent(LoginScreen.this, LoginSuccessActivity.class);
                        intentLoginSuccess.putExtra("CURRENT_FIRST_NAME", b[0]);
                        intentLoginSuccess.putExtra("CURRENT_LAST_NAME", b[1]);
                        intentLoginSuccess.putExtra("CURRENT_BDAY", b[2]);
                        startActivity(intentLoginSuccess);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Type your credentails first.",Toast.LENGTH_SHORT).show();
                }
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

        int i = 0;
        for(UserProfile up:mUsers)
        {
            firstNames[i] = up.getName();
            lastNames[i] = up.getLastName();
            bdays[i] = up.getBday();
            i++;
        }
    }

    public String[] validateInfo(String user, String pswd)
    {
        String[] returnStrArr = new String[4];
        for(UserProfile up:mUsers)
        {
            if(up.getEmail().equals(user) || up.getUserName().equals(user)) {
                if (up.getPswd().equals(pswd)) {
                  returnStrArr[0] = up.getName();
                  returnStrArr[1] = up.getLastName();
                  returnStrArr[2] = up.getBday();
                  returnStrArr[3] = "true";
                  break;
                }
            }
            returnStrArr[3] = "false";
        }
        return returnStrArr;
    }
}
