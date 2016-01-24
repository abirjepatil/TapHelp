package com.scu.taphelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ListView lv;
    Context context;

    ArrayList prgmName;
    public static int [] prgmImages={R.drawable.plumbin, R.drawable.johnny,R.drawable.jessy};
    public static String [] prgmNameList={"ABC Plumbing World","Johhny Rodrigues","Jessy"};
    public static String [] prgmNameListDesc={"San Jose Plumbing is a professional company doing plumbing repairs and services with the knowledge to bring you the best service available. We are fully insured, which ensures your investment in us is a safe one.",
            "With Johnny's Pool Service Inc you can rest assured that your pool is receiving the attention necessary to give you years of worry free enjoyment provided by dependable, ethical pool experts.","Jessy Maid is the most well-known, trusted name in home cleaning services. For 30 years we’ve performed housekeeping services according to the wishes of our clients."};
    public static int [] prgmRatingsImages={ R.drawable.stars,R.drawable.stars,R.drawable.stars};
    public static int [] prgmCallImages={R.drawable.call, R.drawable.call,R.drawable.call,R.drawable.call};




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        context=this;

        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, prgmNameList, prgmImages,prgmNameListDesc,prgmRatingsImages,prgmCallImages));



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent myIntent = new Intent(DashboardActivity.this, FilterActivity.class);
            myIntent.putExtra("key", "hello"); //Optional parameters
            DashboardActivity.this.startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            Intent myIntent = new Intent(DashboardActivity.this, AppointmentActivity.class);
            myIntent.putExtra("key", "hello"); //Optional parameters
            DashboardActivity.this.startActivity(myIntent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            Intent myIntent = new Intent(DashboardActivity.this, PaymentActivity.class);
            myIntent.putExtra("key", "hello"); //Optional parameters
            DashboardActivity.this.startActivity(myIntent);

        } else if (id == R.id.nav_slideshow) {

            Intent myIntent = new Intent(DashboardActivity.this, NewServiceActivity.class);
            myIntent.putExtra("key", "hello"); //Optional parameters
            DashboardActivity.this.startActivity(myIntent);

        } else if (id == R.id.nav_manage) {

            Intent myIntent = new Intent(DashboardActivity.this, RequestsActivity.class);
            myIntent.putExtra("key", "hello"); //Optional parameters
            DashboardActivity.this.startActivity(myIntent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
