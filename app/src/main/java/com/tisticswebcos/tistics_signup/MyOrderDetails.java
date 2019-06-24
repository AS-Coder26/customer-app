package com.tisticswebcos.tistics_signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyOrderDetails extends AppCompatActivity {

    Toolbar toolbar;
    TextView prodName, prodType, price, minorder, orderid, transactionid,ddate,permeter, nmeter, nmetercharge, delivcharge, gst, totalprice;
    Button track;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_details);
        toolbar = findViewById(R.id.orderdettoolbar);
        prodName = findViewById(R.id.pName);
        prodType = findViewById(R.id.pType);
        price = findViewById(R.id.price);
        minorder = findViewById(R.id.minorderdet);
        orderid = findViewById(R.id.orderid);
        transactionid = findViewById(R.id.transactionid);
        ddate = findViewById(R.id.ddate);
        permeter = findViewById(R.id.permeter);
        nmeter = findViewById(R.id.nmeter);
        nmetercharge = findViewById(R.id.nmetercharge);
        delivcharge = findViewById(R.id.delcharge);
        gst = findViewById(R.id.gst);
        totalprice = findViewById(R.id.totalprice);
        track = findViewById(R.id.trackbutton);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });

        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trackintent = new Intent(getApplicationContext(),OrderLocationMap.class);
                startActivity(trackintent);

            }
        });
        getIncomingIntent();

    }

    void getIncomingIntent(){

        if(getIntent().hasExtra("prodname")){

            String productName = getIntent().getStringExtra("prodname");
            String status =  getIntent().getStringExtra("ostatus");
            Toast.makeText(getApplicationContext(),"name: "+productName,Toast.LENGTH_SHORT);
            Log.d("intent msg",status);
        }
        else{
            Toast.makeText(getApplicationContext(),"no intentmsg",Toast.LENGTH_SHORT);
        }

    }
}
