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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.azarnush.webeskan.Account_register.BooleanRequest;
import com.azarnush.webeskan.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

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
        final TextView txt_number_phone = root.findViewById(R.id.txt_number_phone);
        final EditText edt_name_Residents = root.findViewById(R.id.edt_name_Residents);
        final EditText edt_family_Residents = root.findViewById(R.id.edt_family_Residents);
        final EditText edt_Password = root.findViewById(R.id.edt_Password);
        final EditText edt_Repeat_Password = root.findViewById(R.id.edt_Repeat_Password);
        final EditText edt_imail = root.findViewById(R.id.edt_imail);
        final Button btn_registration = root.findViewById(R.id.btn_registration);
        txt_number_phone.setText(Get_number_residentFragment.mobile_number);
        shPref = getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);


        btn_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String mobile = txt_number_phone.getText().toString();
                    String firstName = edt_name_Residents.getText().toString();
                    String lastName = edt_family_Residents.getText().toString();
                    String password = edt_Password.getText().toString();
                    String confirmPassword = edt_Repeat_Password.getText().toString();
                    String email = edt_imail.getText().toString();

                    if (!firstName.equals("") & !lastName.equals("") & !password.equals("") & !confirmPassword.equals("") & !email.equals("")) {
                        if (password.equalsIgnoreCase(confirmPassword)) {
                            jsonObject = new JSONObject();
                            jsonObject.put("Mobile", mobile);
                            jsonObject.put("FirstName", firstName);
                            jsonObject.put("LastName", lastName);
                            jsonObject.put("Password", password);
                            jsonObject.put("ConfirmPassword", confirmPassword);
                            jsonObject.put("Email", email);
                            booleanRequest_user_register();

                            SharedPreferences.Editor sEdit = shPref.edit();
                            sEdit.putString("Mobile", mobile);
                            sEdit.putString("FirstName", firstName);
                            sEdit.putString("LastName", lastName);
                            sEdit.putString("Password", password);
                            sEdit.putString("ConfirmPassword", confirmPassword);
                            sEdit.putString("Email", email);
                            sEdit.apply();
                        } else
                            Snackbar.make(btn_registration, "تکرار پسورد اشتباه میباشد", Snackbar.LENGTH_LONG).show();

                    } else {
                        Snackbar.make(btn_registration, "لطفا تمام گزینه هارو تکمیل فرمایید", Snackbar.LENGTH_LONG).show();
                    }

                    // Toast.makeText(getContext(), "شما ثبت نام شدید", Toast.LENGTH_LONG).show();
                    //  Toast.makeText(getContext(), registerAccount.toString(), Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return root;
    }

    private void booleanRequest_user_register() {
        RequestQueue queue = Volley.newRequestQueue(getContext());

        String url = "http://api.webeskan.com/api/v1/users/register";

        try {

            BooleanRequest booleanRequest = new BooleanRequest(Request.Method.POST, url, jsonObject.toString(), new Response.Listener<Boolean>() {
                @Override
                public void onResponse(Boolean response) {
                    Toast.makeText(getContext(), String.valueOf(response), Toast.LENGTH_SHORT).show();
                    if (response == true) {
                        HomeActivity.fragmentManager.popBackStack();
                        Fragment fragment = new Resident_panelFragment();
                        HomeActivity.fragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment)
                                .addToBackStack(null).commit();

                        SharedPreferences.Editor sEdit = shPref.edit();
                        sEdit.putBoolean("is register", true);
                        sEdit.apply();

                        SharedPreferences.Editor sEdit2 = HomeFragment.homePref.edit();
                        sEdit2.putBoolean("is login", true);
                        sEdit2.apply();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            // Add the request to the RequestQueue.
            queue.add(booleanRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        HomeActivity.toolbar.setTitle("ورود ساکنین");
    }

}