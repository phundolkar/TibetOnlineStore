package com.example.tibetonlinestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import Prevalent.Prevalent;
import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity {
private CircleImageView profileImageView;
private EditText fullNameEditText, userPhoneEditText, addressEditText;
private TextView profileChangeTextBtn, closeTextBtn, saveTextBtn;
private Uri imageUri;
private String myUrl="";
private StorageReference storageProfilePictureRef;
private String checker= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        profileImageView=(CircleImageView)findViewById(R.id.settings_profile_image);
        userPhoneEditText=(EditText)findViewById(R.id.settings_phone_number);
        fullNameEditText=(EditText)findViewById(R.id.settings_full_name);
        addressEditText=(EditText)findViewById(R.id.settings_address);
        profileChangeTextBtn=(TextView)findViewById(R.id.profile_image_change_btn);
        closeTextBtn=(TextView)findViewById(R.id.close_settings);
        saveTextBtn=(TextView)findViewById(R.id.update_account_settings_btn);

        userInfoDisplay(profileImageView,fullNameEditText,userPhoneEditText,addressEditText);

        closeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
             finish();
            }
        });
        saveTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(checker.equals("clicked"))
                {
                    userInfoSaved();
                }
                else
                {
                    updateOnlyUserInfo();
                }

            }
        });

        profileChangeTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
             checker="clicked";
            }
        });
    }

    private void userInfoSaved()
    {

    }

    private void userInfoDisplay(final CircleImageView profileImageView, final EditText fullNameEditText, final EditText userPhoneEditText, final EditText addressEditText)
    {
        DatabaseReference UsersRef= FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentOnlineUser.getPhone());
        UsersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    if(dataSnapshot.child("image").exists())
                    {
                        String image= dataSnapshot.child("image").getValue().toString();
                        String name= dataSnapshot.child("name").getValue().toString();
                        String phone= dataSnapshot.child("phone").getValue().toString();
                        String address= dataSnapshot.child("address").getValue().toString();

                        Picasso.get().load(image).into(profileImageView);
                        fullNameEditText.setText(name);
                        userPhoneEditText.setText(phone);
                        addressEditText.setText(address);

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
