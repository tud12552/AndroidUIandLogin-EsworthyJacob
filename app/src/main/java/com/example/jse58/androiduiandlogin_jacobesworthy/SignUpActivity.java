package com.example.jse58.androiduiandlogin_jacobesworthy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    private UserProfilePersistence mProfilePersistence = new UserProfilePersistence(this);

    private Button mBtnConfirm = null;

    private EditText mEditTxtFirstName = null;
    private EditText mEditTxtLastName = null;
    private EditText mEditTxtUserName = null;
    private EditText mEditTxtBday = null;
    private EditText mEditTxtPhone = null;
    private EditText mEditTxtEmail = null;
    private EditText mEditTxtPswd = null;

    //Firebase info
    FirebaseAuth mSignUpAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mSignUpAuth = FirebaseAuth.getInstance();

        mBtnConfirm = (Button)findViewById(R.id.btnConfirmSignup);
        mEditTxtFirstName = (EditText) findViewById(R.id.editTxtFirstName);
        mEditTxtLastName = (EditText) findViewById(R.id.editTxtLastName);
        mEditTxtUserName = (EditText) findViewById(R.id.editTxtUserName);
        mEditTxtBday = (EditText) findViewById(R.id.editTxtBday);
        mEditTxtPhone = (EditText) findViewById(R.id.editTxtPhoneNum);
        mEditTxtEmail = (EditText)findViewById(R.id.editTxtEmail);
        mEditTxtPswd = (EditText) findViewById(R.id.editTxtNewPassword);

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createNewUser(v);

            }
        });
    }

    public void createNewUser(View v)
    {
        String first = mEditTxtFirstName.getText().toString();
        String last = mEditTxtLastName.getText().toString();
        String usern = mEditTxtUserName.getText().toString();
        String bday = mEditTxtBday.getText().toString();
        String phone = mEditTxtPhone.getText().toString();
        String email = mEditTxtEmail.getText().toString();
        String password = mEditTxtPswd.getText().toString();

        UserProfile newUser = new UserProfile(first,last,usern,phone,email,bday,password);

        mProfilePersistence.insert(newUser);

        Toast.makeText(getApplicationContext(),"You Have Been Added.",Toast.LENGTH_SHORT).show();


        mSignUpAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Log.d(TAG,"SignUpActivity : Success");
                            toastMessage("Authentication SUCCESSFUL.");
                            FirebaseUser newUser = mSignUpAuth.getCurrentUser();
                            Intent goToHomeScreen = new Intent(getApplicationContext(),LoginScreen.class);
                            goToHomeScreen.putExtra("NEW_EMAIL",newUser.getEmail());
                            startActivity(goToHomeScreen);
                        }
                        else
                        {
                            Log.d(TAG,"SignUpActivity : Failed");
                            toastMessage("Authentication FAILURE.");
                        }
                    }
                });
    }

    public void toastMessage(String msg)
    {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
