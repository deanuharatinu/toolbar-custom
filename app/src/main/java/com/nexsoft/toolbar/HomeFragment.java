package com.nexsoft.toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.MaterialToolbar;
import com.nexsoft.toolbar.component.ToolbarCustom;

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
        MaterialToolbar toolbar = appbar.getToolbarView();

//        MaterialToolbar toolbar = view.findViewById(R.id.top_app_bar);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        // To set back button / hamburger icon
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);

        appbar.onSubmitQuery(new ToolbarCustom.SearchInterface() {
            @Override
            public void onQuerySubmit(String query) {
                Toast.makeText(requireContext(), query, Toast.LENGTH_SHORT).show();
            }
        });

        toolbar.setNavigationOnClickListener(viewOnClick ->
                Toast.makeText(requireContext(), "Clicked hamburger", Toast.LENGTH_SHORT).show());


    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.search_toolbar_menu, menu);
//        MenuItem search = menu.findItem(R.id.search_toolbar);
//
//    }
}