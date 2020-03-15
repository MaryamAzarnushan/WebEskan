package com.azarnush.webeskan;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Resident_panelFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        HomeActivity.toolbar.setTitle("پنل ساکنین");

        HomeActivity.navigationView.getMenu().findItem(R.id.nav_exit_Account).setVisible(true);



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resident_panel, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        HomeActivity.toolbar.setTitle("پنل ساکنین");
    }
}
