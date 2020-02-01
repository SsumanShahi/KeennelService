package com.suman.kennelservice.ui.Appointment;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.suman.kennelservice.NavActivity;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.activity.AppointmentActivity;
import com.suman.kennelservice.activity.DogDetailActivity;
import com.suman.kennelservice.activity.DogListActivity;
import com.suman.kennelservice.activity.DogRegisterActivity;
import com.suman.kennelservice.adaptar.AppointmentAdaptar;
import com.suman.kennelservice.adaptar.DogListAdaptar;
import com.suman.kennelservice.api.MyDogsapi;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.Appointment;
import com.suman.kennelservice.model.AppointmentCRUD;
import com.suman.kennelservice.model.MyDogCRUD;
import com.suman.kennelservice.model.User;
import com.suman.kennelservice.model.UserCRUD;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentFragment extends Fragment {

    private RecyclerView r1;

    private TextView btn_appointment;


    AppointmentAdaptar appointmentAdaptar;
    List<AppointmentCRUD> appointmentList;
    User user;

    private ShareViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
//        final TextView textView = root.findViewById(R.id.text_share);
//        shareViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        r1 = root.findViewById(R.id.r1);
        btn_appointment = root.findViewById(R.id.btn_appointment);

        user=new User();


        btn_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), AppointmentActivity.class));
                Intent intent = new Intent(getContext(),AppointmentActivity.class);
                intent.putExtra("User",user);
                startActivity(intent);
            }
        });

        appointmentList = new ArrayList<>();
        Userapi userapi = url.getInstance().create(Userapi.class);
        Call<List<AppointmentCRUD>> listCall = userapi.getappoint(url.token);
        listCall.enqueue(new Callback<List<AppointmentCRUD>>() {
            @Override
            public void onResponse(Call<List<AppointmentCRUD>> call, Response<List<AppointmentCRUD>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Code Error" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                appointmentList= response.body();
                appointmentAdaptar = new AppointmentAdaptar(getActivity(),appointmentList);
                r1.setHasFixedSize(true);
                r1.setAdapter(appointmentAdaptar);
                r1.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<AppointmentCRUD>> call, Throwable t) {
                Toast.makeText(getContext(), "Error"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


        return root;
    }
}