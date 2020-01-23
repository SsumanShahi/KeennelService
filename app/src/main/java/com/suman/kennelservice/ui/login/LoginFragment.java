package com.suman.kennelservice.ui.login;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.suman.kennelservice.BLL.LoginBLL;
import com.suman.kennelservice.NavActivity;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.activity.RegisterActivity;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.Userlogin;
import com.suman.kennelservice.serverresponse.SignupResponse;
import com.suman.kennelservice.strictmode.StrictModeClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    private EditText ettusername, ettpassword;
    ImageView profileimage;
    Button btnlogin;
    TextView reg;

    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        ettusername = view.findViewById(R.id.ettusername);
        ettpassword = view.findViewById(R.id.ettpassword);
        profileimage = view.findViewById(R.id.profileimage);
        btnlogin = view.findViewById(R.id.btnlogin);
        reg = view.findViewById(R.id.reg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegisterActivity.class));
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        return view;
    }

    private void login() {
        String username = ettusername.getText().toString();
        String password = ettpassword.getText().toString();


        LoginBLL loginBLL = new LoginBLL();
        Userlogin userlogin = new Userlogin(username, password);

        StrictModeClass.StrictMode();
        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<SignupResponse> userCall = userapi.checklogin(userlogin);
        userCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignupResponse> call,@NonNull Response<SignupResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_LONG).show();
                url.token += response.body().getToken();
                Intent intent = new Intent(getContext(), NavActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(@NonNull Call<SignupResponse> call,@NonNull Throwable t) {
                Toast.makeText(getActivity(), "error is = " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

}
