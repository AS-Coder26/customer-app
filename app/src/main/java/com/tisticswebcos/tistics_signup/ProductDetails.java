package com.tisticswebcos.tistics_signup;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetails extends AppCompatActivity {

    Toolbar productdetToolbar;
    TextView productDetName, productDetType, prodDetPrice, custAdd, vendorName;
    Button addChange,addToCart,buyProduct;
    ImageButton shareButton, likeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        productdetToolbar = findViewById(R.id.productdettoolbar);
        productDetName = findViewById(R.id.productDetName);
        productDetType = findViewById(R.id.productDetType);
        prodDetPrice = findViewById(R.id.prodDetPrice);
        custAdd = findViewById(R.id.custAdd);
        vendorName = findViewById(R.id.vendorName);
        addChange = findViewById(R.id.addChange);
        addToCart = findViewById(R.id.addToCart);
        buyProduct = findViewById(R.id.buyProduct);
        shareButton = findViewById(R.id.shareButton);
        likeButton = findViewById(R.id.likeButton);
        setSupportActionBar(productdetToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        productdetToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        getIncomingIntent();
    }

    void getIncomingIntent(){

        if(getIntent().hasExtra("productname")){

            String productName = getIntent().getStringExtra("productname");
            Toast.makeText(getApplicationContext(),"name: "+productName,Toast.LENGTH_SHORT);
            Log.d("intent msg",productName);
        }
        else{
            Toast.makeText(getApplicationContext(),"no intentmsg",Toast.LENGTH_SHORT);
        }

    }
}
