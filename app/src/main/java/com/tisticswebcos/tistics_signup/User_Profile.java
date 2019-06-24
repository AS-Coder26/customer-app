package com.tisticswebcos.tistics_signup;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class User_Profile extends BaseActivity {

   /* Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageView;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user__profile);


        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(2).setChecked(true);
        ConstraintLayout contentFrameLayout = (ConstraintLayout) findViewById(R.id.content_frame); //Remember this is the Constraint area within your activity_base.xml and can be any other layout
        getLayoutInflater().inflate(R.layout.activity_user__profile, contentFrameLayout);




        TextView username = (TextView) findViewById(R.id.username);
        TextView phoneno = (TextView) findViewById(R.id.phoneno);
       // imageView = (ImageView) findViewById(R.id.headerimage);
        //imageView.setImageResource(R.drawable.dp);
        username.setText("Sana Ansari");
        phoneno.setText("9867059586");


    }
}
