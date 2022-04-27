package com.nexsoft.toolbar;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.nexsoft.toolbar.component.NavigationBarCustom;

import java.util.ArrayList;
import java.util.List;

/**
 * Navigation Bottom - Custom Made
 * @author Deanu Haratinu T. - 27 April 2022
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);

        // Set Nav Bottom menu list
        initNavBarMenu();
    }

    private void initNavBarMenu() {
        List<NavigationBarCustom.NavBarMenuModel> menuList = new ArrayList<>();
        menuList.add(new NavigationBarCustom.NavBarMenuModel(R.drawable.ic_scan, "One"));
        menuList.add(new NavigationBarCustom.NavBarMenuModel(R.drawable.ic_upload, "Two"));
        menuList.add(new NavigationBarCustom.NavBarMenuModel(R.drawable.ic_create, "Three"));
        menuList.add(new NavigationBarCustom.NavBarMenuModel(R.drawable.ic_notification, "Four"));
        menuList.add(new NavigationBarCustom.NavBarMenuModel(R.drawable.ic_setting, "Five"));

        NavigationBarCustom navBar = findViewById(R.id.nav_bar_custom);
        navBar.setMenuItem(menuList);

        // Button number is used to determine what action to take when the button is clicked
        // We can use switch case, with the button number constant i.e : NavigationBarCustom.MENU_ONE
        navBar.actionListener((view, buttonNumber) -> {
            // For navigating between fragment
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.replace(R.id.fragment_container, OneFragment.newInstance());
            transaction.commit();

            // For showing toast based on the clicked button number
            Toast.makeText(this, "Button number : " + buttonNumber, Toast.LENGTH_SHORT).show();
        });
    }
}