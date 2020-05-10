package com.example.tibetonlinestore.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tibetonlinestore.HomeActivity;
import com.example.tibetonlinestore.MainActivity;
import com.example.tibetonlinestore.R;

public class AdminCategoryActivity extends AppCompatActivity {
    private ImageView Tibetan_wear, Tibetan_ac, Tibetan_bags;
    private ImageView Tibetan_Kid, Tibetan_Home;

    private Button LogoutBtn, CheckOrdersBtn, maintainProductsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);


        LogoutBtn = (Button) findViewById(R.id.admin_logout_btn);
        CheckOrdersBtn = (Button) findViewById(R.id.check_orders_btn);
        maintainProductsBtn = (Button) findViewById(R.id.maintain_btn);

        maintainProductsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, HomeActivity.class);
                intent.putExtra("Admin", "Admin");
                startActivity(intent);

            }
        });

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewOrdersActivity.class);
                startActivity(intent);

            }
        });

        Tibetan_wear = (ImageView) findViewById(R.id.Tibetan_wear);
        Tibetan_ac = (ImageView) findViewById(R.id.Tibetan_ac);
        Tibetan_bags = (ImageView) findViewById(R.id.Tibetan_bags);
        Tibetan_Kid = (ImageView) findViewById(R.id.Tibetan_Kid);
        Tibetan_Home = (ImageView) findViewById(R.id.Tibetan_Home);

        Tibetan_wear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Tibetan_wear");
                startActivity(intent);
            }
        });
        Tibetan_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Tibetan_ac");
                startActivity(intent);
            }
        });
        Tibetan_bags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Tibetan_bags");
                startActivity(intent);
            }
        });
        Tibetan_Kid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Tibetan_Kid");
                startActivity(intent);
            }
        });
        Tibetan_Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "Tibetan_Home");
                startActivity(intent);
            }
        });
    }
}
