package com.suman.kennelservice.ui.send;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.suman.kennelservice.BLL.LoginBLL;
import com.suman.kennelservice.NavActivity;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.Userlogin;
import com.suman.kennelservice.serverresponse.SignupResponse;
import com.suman.kennelservice.strictmode.StrictModeClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class SendFragment extends Fragment {
    EditText etusername, etpassword;
    ImageView profileimg;
    Button btnlogin;
    TextView reg;

    private SendViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View view = inflater.inflate(R.layout.fragment_send, container, false);
//        final TextView textView = root.findViewById(R.id.text_send);
//        sendViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        etusername = view.findViewById(R.id.etusername);
        etpassword = view.findViewById(R.id.etpassword);
        profileimg = view.findViewById(R.id.profileimg);
        btnlogin = view.findViewById(R.id.btnlogin);
        reg = view.findViewById(R.id.reg);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        return view;
    }

    private void login() {

        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();


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
}