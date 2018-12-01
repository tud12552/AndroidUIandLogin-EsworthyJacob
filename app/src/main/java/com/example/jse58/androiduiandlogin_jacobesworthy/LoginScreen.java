package com.example.jse58.androiduiandlogin_jacobesworthy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {

    private ArrayList<UserProfile> mUsers;
    private UserProfilePersistence mPresistenceProfile;

    private static final String TAG = "LoginActivity";

    private String newUserEmail;

    //Firebase info
    FirebaseAuth mSigninAuth;

    private ListView listView;

    private String[] firstNames;
    private String[] lastNames;
    private String[] bdays;

    private EditText mEditTxtEmail = null;
    private EditText mEditTxtPswd = null;
    private Button mBtnLogin = null;
    private Button mBtnSignUp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        mSigninAuth = FirebaseAuth.getInstance();
        mPresistenceProfile = new UserProfilePersistence(this);

        mEditTxtEmail = (EditText) findViewById(R.id.editTxtEmail);
        mEditTxtPswd = (EditText) findViewById(R.id.editTxtPassowrd);
        mBtnLogin = (Button) findViewById(R.id.BtnLogin);
        mBtnSignUp = (Button) findViewById(R.id.BtnSignUp);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn(mEditTxtEmail.getText().toString(), mEditTxtPswd.getText().toString());
            }
        });

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "SignUp Button Pressed", Toast.LENGTH_SHORT).show();

                Intent intentSignUp = new Intent(LoginScreen.this, SignUpActivity.class);
                startActivity(intentSignUp);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent prevIntent = getIntent();
        newUserEmail = prevIntent.getStringExtra("NEW_EMAIL");
        mEditTxtEmail.setText(newUserEmail);
        mEditTxtPswd.setText("");

    }

    public String[] validateInfo(String user, String pswd) {
        mUsers = mPresistenceProfile.getDataFromDB();

        String[] returnStrArr = new String[4];
        returnStrArr[0] = "0";
        returnStrArr[1] = "1";
        returnStrArr[2] = "2";
        returnStrArr[3] = "a";

        if (mUsers.size() == 0) {
            toastMessage("Please Signup.");
            return returnStrArr;
        }

        for (UserProfile up : mUsers) {
            if (up.getEmail().equals(user) || up.getUserName().equals(user)) {
                if (up.getPswd().equals(pswd)) {
                    returnStrArr[0] = up.getName();
                    returnStrArr[1] = up.getLastName();
                    returnStrArr[2] = up.getBday();
                    returnStrArr[3] = "true";
                    break;
                }
            }
            returnStrArr[3] = "a";
        }
        return returnStrArr;
    }

    public void SignIn(String email, String password) {
        try {
            mSigninAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
            {
                        String firstname, lastname, bday;
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                FirebaseUser user = mSigninAuth.getCurrentUser();

                                mUsers = mPresistenceProfile.getDataFromDB();
                                for (UserProfile up : mUsers) {
                                    if (up.getEmail().equals(mEditTxtEmail.getText().toString()) || up.getUserName().equals(mEditTxtEmail.getText().toString())) {
                                        if (up.getPswd().equals(mEditTxtPswd.getText().toString())) {
                                            firstname = up.getName();
                                            lastname = up.getLastName();
                                            bday = up.getBday();
                                            break;
                                        }
                                    }
                                }

                                Intent intentLoginSuccess = new Intent(LoginScreen.this, LoginSuccessActivity.class);
                                intentLoginSuccess.putExtra("CURRENT_FIRST_NAME", firstname);
                                intentLoginSuccess.putExtra("CURRENT_LAST_NAME", lastname);
                                intentLoginSuccess.putExtra("CURRENT_BDAY", bday);
                                startActivity(intentLoginSuccess);
                            }
                        }
                    });
        }
        catch (Exception ex)
        {
            try {
                mUsers = mPresistenceProfile.getDataFromDB();
                String[] b = new String[4];
                Toast.makeText(getApplicationContext(), "Login Button Pressed", Toast.LENGTH_SHORT).show();

                if (!mEditTxtEmail.getText().toString().isEmpty() && !mEditTxtPswd.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Verifying Info.", Toast.LENGTH_SHORT).show();
                    b = validateInfo(mEditTxtEmail.getText().toString(), mEditTxtPswd.getText().toString());

                    if (!b[3].isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Logging In.", Toast.LENGTH_SHORT).show();

                        Intent intentLoginSuccess = new Intent(LoginScreen.this, LoginSuccessActivity.class);
                        intentLoginSuccess.putExtra("CURRENT_FIRST_NAME", b[0]);
                        intentLoginSuccess.putExtra("CURRENT_LAST_NAME", b[1]);
                        intentLoginSuccess.putExtra("CURRENT_BDAY", b[2]);
                        startActivity(intentLoginSuccess);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Type your credentails first.", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e) {
                Log.d(TAG, e.getMessage());
                toastMessage("Sign in Error.");
                mEditTxtEmail.setText("");
                mEditTxtPswd.setText("");

            }
        }
    }

    public void toastMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
