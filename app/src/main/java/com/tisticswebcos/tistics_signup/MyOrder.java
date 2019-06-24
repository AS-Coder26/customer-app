package com.tisticswebcos.tistics_signup;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MyOrder extends BaseActivity {

    private ArrayList<OrderListInitializor> oarrayList = new ArrayList<>();
    private RecyclerView orecyclerView;
    private OrderListAdapter oAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my_order);

        ConstraintLayout contentFrameLayout = (ConstraintLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_my_order, contentFrameLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(1).setChecked(true);

        orecyclerView = findViewById(R.id.orderrecyclerview);
        oAdapter = new OrderListAdapter(oarrayList,this);
        orecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        orecyclerView.setItemAnimator(new DefaultItemAnimator());
        //orecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        orecyclerView.setAdapter(oAdapter);
        prepareData();
    }

    private void prepareData()
    {

        OrderListInitializor list = null;
        list = new OrderListInitializor("Outdoor Furniture Polyester linen Fabric","Textile Material Fabric","pending");
        oarrayList.add(list);

        list = new OrderListInitializor("Outdoor Furniture Polyester linen Fabric","Textile Material Fabric","confirm");
        oarrayList.add(list);

        list = new OrderListInitializor("Outdoor Furniture Polyester linen Fabric","Textile Material Fabric","pending");
        oarrayList.add(list);


       oAdapter.notifyDataSetChanged();

    }
}
