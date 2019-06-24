package com.tisticswebcos.tistics_signup;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
               // final String appPackageName = getPackageName();

                switch (item.getItemId()) {

                    case R.id.home:
                        Intent home = new Intent(getApplicationContext(),Home.class);
                        startActivity(home);
                        finish();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.orders:
                        Intent myorders = new Intent(getApplicationContext(),MyOrder.class);
                        startActivity(myorders);
                        finish();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.myaccount:
                        Intent myacc = new Intent(getApplicationContext(),User_Profile.class);
                        startActivity(myacc);
                        finish();
                        drawerLayout.closeDrawers();
                        break;

                }
                return false;
            }
        });
    }

    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    public void finish()
    {
        super.finish();
        overridePendingTransitionExit();
    }

    public void startActivity(Intent intent)
    {
        super.startActivity(intent);
        overridePendingTransitionEnter();
    }

    protected void overridePendingTransitionEnter()
    {
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }

    protected void overridePendingTransitionExit()
    {
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }
}
