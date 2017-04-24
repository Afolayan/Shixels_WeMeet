package com.shixels.wemeet;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.FragmentDrawerListener,
        LoginFragment.OnFragmentInteractionListener,
        MainFragment.OnFragmentInteractionListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private NavigationDrawerFragment drawerFragment;
    private CharSequence mTitle;

    private static TextView textViewPageTitle;
    Fragment fragment;
    Boolean action = false;
    int page = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new MainFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container_body, fragment).commit();
        mTitle = getResources().getString(R.string.page_1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        textViewPageTitle = (TextView) toolbar.findViewById(R.id.toolbar_text);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setTitle(mTitle);
        Log.e(TAG, "onCreate: "+textViewPageTitle.getText().toString().trim() );

        drawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        drawerFragment.setDrawerListener(this);
    }

    @Override
    public void onBackPressed() {

        if (drawerFragment.isDrawerOpen()) {
            drawerFragment.closeDrawer();

        } else {

            super.onBackPressed();
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        textViewPageTitle.setText(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_items) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
          action = false;
        switch( position){
            case 0:
            case 1: {
                page = 1;
                Log.e(TAG, "onDrawerItemSelected: on case 1" );
                fragment = new MainFragment();
                textViewPageTitle.setText(R.string.page_1);
                action = true;
                break;
            }
            case 2: {
                page = 2;
                //change fragment class as appropriate
                fragment = new MainFragment();
                textViewPageTitle.setText(R.string.page_2);
                action = true;
                break;
            }
            case 3: {
                page = 3;
                //change fragment class as appropriate
                fragment = new MainFragment();
                textViewPageTitle.setText(R.string.page_3);
                action = true;
                break;
            }
            case 4: {
                page = 4;
                //change fragment class as appropriate
                fragment = new MainFragment();
                textViewPageTitle.setText(R.string.page_4);
                action = true;
                break;
            }
            case 5: {
                page = 5;
                //change fragment class as appropriate
                textViewPageTitle.setText(R.string.page_5);
                action = true;
                break;
            }
            default: {
                fragment = new MainFragment();
            }
        }
        if (action) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container_body, fragment)
                    .commit();
        }

    }
}
