package com.nexsoft.toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.nexsoft.toolbar.component.ToolbarCustom;

/**
 * Menu need to be made on "res/menu/..." and provided on onCreateOptionsMenu
 *
 * @author Deanu Haratinu T. - 26 April 2022
 */
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ToolbarCustom appbar = view.findViewById(R.id.app_bar);
        // Material Toolbar reference for back/hamburger button onClickListener
        MaterialToolbar toolbar = appbar.getToolbarView();
//        MaterialToolbar toolbar = view.findViewById(R.id.app_bar);
        ((MainActivity) requireActivity()).setSupportActionBar(toolbar);

        // To set back button / hamburger icon
        setEnableNavigation(true);

        // To enable options menu
        setEnableMenu(true);

        // To set back button / hamburger button action
        appbar.onNavClickListener(viewNavClick ->
                Toast.makeText(requireContext(), "Clicked navigation", Toast.LENGTH_SHORT).show());

        // To set query callback
        appbar.onSubmitQuery(query ->
                Toast.makeText(requireContext(), query, Toast.LENGTH_SHORT).show());

        // To set cart action
        appbar.onCartClickListener(viewCartClick ->
                Toast.makeText(requireContext(), "Clicked cart", Toast.LENGTH_SHORT).show());

        // Set programmatically
//        setProgrammatically(appbar);

    }

    private void setEnableNavigation(boolean isEnable) {
        ActionBar actionBar = ((MainActivity) requireActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(isEnable);
    }

    private void setEnableMenu(boolean isEnable) {
        setHasOptionsMenu(isEnable);
    }

    private void setProgrammatically(ToolbarCustom appbar) {
        appbar.setToolbarTitle("Set Programmatically");
        appbar.setCenteredTitle(false);
    }

    // To inflate the menu if available
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    // Set the action of the selected menuItem
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.favorite) {
            // User chose the "Favorite" action, mark the current item
            return true;
        } else if (id == R.id.search) {
            // User chose the "Search" action, mark the current item
            return true;
        } else if (id == R.id.more) {
            // User chose the "More" action, mark the current item
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}