package com.suman.kennelservice.ui.DogRegister;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.suman.kennelservice.R;
import com.suman.kennelservice.activity.DogRegisterActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class DogRegisterFragment extends Fragment {

    private EditText etdogname,etpettype,etbreed,etage,etwieght,etvaccination;
    private CircleImageView profileimg;
    private Button btnadd;

    private DogRegisterModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(DogRegisterModel.class);
        View view = inflater.inflate(R.layout.fragment_send, container, false);
//        final TextView textView = root.findViewById(R.id.text_send);
//        sendViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        btnadd= view.findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DogRegisterActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

}