package com.azarnush.webeskan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;


public class Resident_panelFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HomeActivity.toolbar.setTitle("پنل ساکنین");

        HomeActivity.navigationView.getMenu().findItem(R.id.nav_exit_Account).setVisible(true);

        View root = inflater.inflate(R.layout.fragment_resident_panel, container, false);

        FloatingActionButton fab = root.findViewById(R.id.fab_new);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        HomeActivity.toolbar.setTitle("پنل ساکنین");
    }
}
