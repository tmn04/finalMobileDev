package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.util.Calendar.getInstance;

public class SignUp extends AppCompatActivity {

    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);

        regBtn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                String name = regName.getEditText().getText().toString();
                String username = regUsername.getEditText().getText().toString();
                String email = regEmail.getEditText().getText().toString();
                String phoneNo = regPhoneNo.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
                reference.child(username).setValue(helperClass);
            }
        });
    }

    private Boolean validateName()
    {
        String val = regName.getEditText().getText().toString();

        if(val.isEmpty())
        {
            regName.setError("Field cannot be empty");
            return false;
        }
        else {
            regName.setError(null);
            return true;
        }
    }

    private Boolean validateUsername()
    {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace ="(?=\\S+$)";
        if(val.isEmpty())
        {
            regName.setError("Field cannot be empty");
            return false;
        }
        else if (val.length()>=15){
            regName.setError("Username too long");
            return false;
        }
        else if (!val.matches(noWhiteSpace))
        {
            regName.setError("White Spaces are not allowed");
            return false;
        }
        else
        {
            regName.setError(null);
            return true;
        }
    }

    private Boolean validateEmail()
    {
        String val = regName.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        }
        else if (!val.matches(emailPattern))
        {
            regName.setError("Invalid email");
            return false;
        }
        else
        {
            regName.setError(null);
            return true;
        }

    }
    public void registerUser(View view)
    {
        if (!validateEmail() | !validateName() | !validateUsername()) {
            return;
        }
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
        reference.child(username).setValue(helperClass);

    }
}