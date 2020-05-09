package com.example.tibetonlinestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tibetonlinestore.Admin.AdminCategoryActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Admins;
import Model.Users;
import Prevalent.Prevalent;

public class AdminLoginActivity extends AppCompatActivity
{
    private Button adminLoginButton,notAdminButton;
    private EditText AdminNumber,AdminPassword;
    private ProgressDialog loadingBar;
    private String parentDbName="Admins";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminLoginButton=findViewById(R.id.Admin_login_btn);
        notAdminButton=findViewById(R.id.not_admin_btn);
        AdminNumber=findViewById(R.id.AdminNumber);
        AdminPassword=findViewById(R.id.AdminPassword);
        loadingBar=new ProgressDialog(this);

        notAdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(AdminLoginActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AdminLogin();
            }
        });
    }

    private void AdminLogin()
    {
        String phone = AdminNumber.getText().toString();
        String password = AdminPassword.getText().toString();

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
            loadingBar.setTitle("Admin Account");
            loadingBar.setMessage("Please wait admin,while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AdminAccess(phone,password);

        }


    }

    private void AdminAccess(final String phone, final String password)
    {
        final DatabaseReference AdminRef;
        AdminRef= FirebaseDatabase.getInstance().getReference();

        AdminRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(parentDbName).child(phone).exists())
                {
                   Admins adminsData= dataSnapshot.child(parentDbName).child(phone).getValue(Admins.class);
                    assert adminsData != null;
                    if (adminsData.getPhone().equals(phone))
                    {
                        if (adminsData.getPassword().equals(password))
                        {
                            if(parentDbName.equals("Admins"))
                            {
                                Toast.makeText(AdminLoginActivity.this,"Admin Logged in successfully",Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent= new Intent(AdminLoginActivity.this, AdminCategoryActivity.class);
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            loadingBar.dismiss();
                            Toast.makeText(AdminLoginActivity.this,"Password is incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(AdminLoginActivity.this,"Network error",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });


    }
}
