package com.azarnush.webeskan;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.azarnush.webeskan.Account_register.RegisterAccount;

import org.json.JSONException;
import org.json.JSONObject;

public class Resident_informationFragment extends Fragment {

    SharedPreferences shPref;
    JSONObject jsonObject;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_resident_information, container, false);
        HomeActivity.toolbar.setTitle("ورود ساکنین");
        TextView txt_number_phone = root.findViewById(R.id.txt_number_phone);
        final EditText edt_name_Residents = root.findViewById(R.id.edt_name_Residents);
        final EditText edt_family_Residents = root.findViewById(R.id.edt_family_Residents);
        final EditText edt_Password = root.findViewById(R.id.edt_Password);
        final EditText edt_Repeat_Password = root.findViewById(R.id.edt_Repeat_Password);
        final EditText edt_imail = root.findViewById(R.id.edt_imail);
        Button btn_registration = root.findViewById(R.id.btn_registration);
        txt_number_phone.setText(Get_number_residentFragment.mobile_number);
        shPref = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);

        btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    jsonObject = new JSONObject();
                    jsonObject.put("firstName", edt_name_Residents.getText().toString());
                    jsonObject.put("lastName", edt_family_Residents.getText().toString());
                    jsonObject.put("password", edt_Password.getText().toString());
                    jsonObject.put("confirmPassword", edt_Repeat_Password.getText().toString());
                    jsonObject.put("email", edt_imail.getText().toString());
                    sendJsonObjectRequest_user_register();

                    SharedPreferences.Editor sEdit = shPref.edit();
                    sEdit.putString("firstName", edt_name_Residents.getText().toString());
                    sEdit.putString("lastName", edt_family_Residents.getText().toString());
                    sEdit.putString("password", edt_Password.getText().toString());
                    sEdit.putString("confirmPassword", edt_Repeat_Password.getText().toString());
                    sEdit.putString("email", edt_imail.getText().toString());
                    sEdit.apply();
                    // Toast.makeText(getContext(), "شما ثبت نام شدید", Toast.LENGTH_LONG).show();

                    //  Toast.makeText(getContext(), registerAccount.toString(), Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        return root;
    }


    private void sendJsonObjectRequest_user_register() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url = "http://api.webeskan.com/api/v1/users/register";

        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        };

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, listener, errorListener);
        queue.add(request);

    }

    @Override
    public void onResume() {
        super.onResume();
        HomeActivity.toolbar.setTitle("ورود ساکنین");
    }

}
