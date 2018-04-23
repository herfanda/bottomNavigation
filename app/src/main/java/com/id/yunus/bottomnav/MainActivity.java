package com.id.yunus.bottomnav;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private static final String SELECTED_ITEM = "arg_selected_item";

    private BottomNavigationView bottomNav;
    private int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        MenuItem selectedMenuItem;
        if (savedInstanceState != null) {
            selectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedMenuItem = bottomNav.getMenu().findItem(selectedItem);
        } else {
            selectedMenuItem = bottomNav.getMenu().getItem(0);
        }
        selectFragment(selectedMenuItem);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, selectedItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        MenuItem homeItem = bottomNav.getMenu().getItem(0);
        if (selectedItem != homeItem.getItemId()) {
            // select home item
            selectFragment(homeItem);
        } else {
            super.onBackPressed();
        }
    }

    private void selectFragment(MenuItem item) {
        Fragment frag = null;
        // init corresponding fragment
        switch (item.getItemId()) {
            case R.id.menu_home:
                frag = MenuFragment.newInstance(getString(R.string.text_home),
                        getColorFromRes(R.color.color_home));
                break;
            case R.id.menu_notifications:
                frag = MenuFragment.newInstance(getString(R.string.text_notifications),
                        getColorFromRes(R.color.color_notifications));
                break;
            case R.id.menu_search:
                frag = MenuFragment.newInstance(getString(R.string.text_search),
                        getColorFromRes(R.color.color_search));
                break;
        }

        // update selected item
        selectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i< bottomNav.getMenu().size(); i++) {
            MenuItem menuItem = bottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        updateToolbarText(item.getTitle());

        if (frag != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, frag, frag.getTag());
            ft.commit();
        }
    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }

    private int getColorFromRes(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }
}
