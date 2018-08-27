package com.peacefullwarrior.eman.a30dayschallenge.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.peacefullwarrior.eman.a30dayschallenge.R;
import com.peacefullwarrior.eman.a30dayschallenge.ui.fragment.DailyRoutineFragment;
import com.peacefullwarrior.eman.a30dayschallenge.ui.fragment.EventsFragment;
import com.peacefullwarrior.eman.a30dayschallenge.ui.fragment.HabitFragment;
import com.peacefullwarrior.eman.a30dayschallenge.ui.fragment.MyTasksFragment;
import com.peacefullwarrior.eman.a30dayschallenge.ui.fragment.ProgressFragment;
import com.peacefullwarrior.eman.a30dayschallenge.ui.fragment.ToBuyListFragment;

import android.support.v4.app.Fragment;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new MyTasksFragment()).commit();


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
        getMenuInflater().inflate(R.menu.home, menu);
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
            return true;
        } else if (id == R.id.action_add) {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.container);
            if (f instanceof ToBuyListFragment) {
                Intent intent = new Intent(HomeActivity.this, AddNewTaskActivity.class);
                intent.putExtra("buy", true);
                startActivity(intent);
            } else if (f instanceof DailyRoutineFragment) {
                Intent intent = new Intent(HomeActivity.this, AddNewDailyRoutine.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(HomeActivity.this, AddNewTaskActivity.class);
                intent.putExtra("buy", false);
                startActivity(intent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_tasks) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new MyTasksFragment()).commit();
        } else if (id == R.id.daily_routine) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new DailyRoutineFragment()).commit();
        } else if (id == R.id.habits) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new HabitFragment()).commit();

        } else if (id == R.id.events) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new EventsFragment()).commit();


        } else if (id == R.id.to_buy_list) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new ToBuyListFragment()).commit();
        } else if (id == R.id.progress) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new ProgressFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
