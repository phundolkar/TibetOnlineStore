package com.example.tibetonlinestore;

import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tibetonlinestore.Admin.AdminCategoryActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Users;
import Prevalent.Prevalent;
import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private Button LoginButton,Button_sign_up;
    private EditText InputNumber,InputPassword;
    private ProgressDialog loadingBar;
    private TextView AdminLink,NotAdminLink,ForgotPassword;

    private String parentDbName="Users";
    private CheckBox chkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InputNumber=(EditText)findViewById(R.id.InputNumber);
        InputPassword=(EditText)findViewById(R.id.InputPassword);
        chkBoxRememberMe=(CheckBox) findViewById(R.id.remember_me_check_box);
        AdminLink=(TextView)findViewById(R.id.admin_panel_link);
        NotAdminLink=(TextView)findViewById(R.id.not_admin_panel_link);
        loadingBar=new ProgressDialog(this);
        Paper.init(this);
        ForgotPassword=findViewById(R.id.forgot_password);


        LoginButton = (Button) findViewById(R.id.Button_login);

        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(LoginActivity.this,ResetPasswordActivity.class);
                intent.putExtra("check","login");
                startActivity(intent);

            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LoginUser();
            }
        });

        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName= "Admins";

            }
        });
        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                LoginButton.setText("Login");
                AdminLink.setVisibility(View.VISIBLE);
                NotAdminLink.setVisibility(View.INVISIBLE);
                parentDbName= "Users";
            }
        });
        Button_sign_up = (Button) findViewById(R.id.Button_sign_up);
        Button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void LoginUser()
    {
        String phone = InputNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone))
    {
        Toast.makeText(this,"Please write your phone number",Toast.LENGTH_SHORT).show();
    }
    else if (TextUtils.isEmpty(password))
    {
        Toast.makeText(this,"Please write your password",Toast.LENGTH_SHORT).show();
    }
    else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait ,while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(phone,password);

        }
    }

    private void AllowAccessToAccount(final String phone, final String password)
    {
        if (chkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserPasswordKey,password);
        }

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersData= dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (usersData.getPhone().equals(phone))
                    {
                        if (usersData.getPassword().equals(password))
                        {
                            if(parentDbName.equals("Admins"))
                            {
                                Toast.makeText(LoginActivity.this,"Welcome Admin, you are logged in successfully",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent= new Intent(LoginActivity.this, AdminCategoryActivity.class);
                                startActivity(intent);
                            }
                            else if(parentDbName.equals("Users"))
                            {
                                Toast.makeText(LoginActivity.this,"Logged in successfully",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent= new Intent(LoginActivity.this,HomeActivity.class);
                                Prevalent.currentOnlineUser=usersData;
                                startActivity(intent);
                            }

                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this,"Password is incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Account with this "+phone+"Already exist!",Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
                Toast.makeText(LoginActivity.this,"You need to create a new account",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }
}
