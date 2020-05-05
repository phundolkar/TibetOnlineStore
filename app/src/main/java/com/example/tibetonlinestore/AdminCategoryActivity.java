package com.example.tibetonlinestore;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminCategoryActivity extends AppCompatActivity {
    private ImageView Tibetan_wear,Tibetan_ac,Tibetan_bags;
    private ImageView Tibetan_Kid,Tibetan_Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);
        Tibetan_wear= (ImageView)findViewById(R.id.Tibetan_wear);
        Tibetan_ac=(ImageView)findViewById(R.id.Tibetan_ac);
        Tibetan_bags=(ImageView)findViewById(R.id.Tibetan_bags);
        Tibetan_Kid=(ImageView)findViewById(R.id.Tibetan_Kid);
        Tibetan_Home=(ImageView)findViewById(R.id.Tibetan_Home);

        Tibetan_wear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Tibetan_wear");
                startActivity(intent);
            }
        });
        Tibetan_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Tibetan_ac");
                startActivity(intent);
            }
        });
        Tibetan_bags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Tibetan_bags");
                startActivity(intent);
            }
        });
        Tibetan_Kid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Tibetan_Kid");
                startActivity(intent);
            }
        });
        Tibetan_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent= new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category","Tibetan_Home");
                startActivity(intent);
            }
        });
    }
}
