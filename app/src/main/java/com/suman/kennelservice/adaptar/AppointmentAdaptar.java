package com.suman.kennelservice.adaptar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suman.kennelservice.NavActivity;
import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.activity.RegisterActivity;
import com.suman.kennelservice.api.Userapi;
import com.suman.kennelservice.model.Appointment;
import com.suman.kennelservice.model.AppointmentCRUD;
import com.suman.kennelservice.model.Dogbreeds;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentAdaptar extends RecyclerView.Adapter<AppointmentAdaptar.AppointmentViewHolder> {
    Context mcontext;
    List<AppointmentCRUD> appointments;


    public AppointmentAdaptar(Context mcontext, List<AppointmentCRUD> appointments) {
        this.mcontext = mcontext;
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public AppointmentAdaptar.AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_l,parent,false);

        return new AppointmentAdaptar.AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentAdaptar.AppointmentViewHolder holder, int position) {

        final AppointmentCRUD appointment = appointments.get(position);
        holder.tv_owner.setText(appointment.getOwnerName());
        holder.tv_petname.setText(appointment.getPetname());
        holder.tv_breed.setText(appointment.getBreed());
        holder.tv_gender.setText(appointment.getGender());
        holder.tv_age.setText(appointment.getAge());

        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Userapi userapi = url.getInstance().create(Userapi.class);
                Call<AppointmentCRUD> deleteap = userapi.deleteappointment(url.token,appointment.get_id());
                deleteap.enqueue(new Callback<AppointmentCRUD>() {
                    @Override
                    public void onResponse(Call<AppointmentCRUD> call, Response<AppointmentCRUD> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(mcontext, "Code Error" + response.code(), Toast.LENGTH_LONG).show();
                            return;
                        }

                        Toast.makeText(mcontext, "Deleted", Toast.LENGTH_LONG).show();
                        mcontext.startActivity(new Intent(mcontext,NavActivity.class));
                    }

                    @Override
                    public void onFailure(Call<AppointmentCRUD> call, Throwable t) {
                        Toast.makeText(mcontext, "Error"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_owner,tv_breed,tv_petname,tv_gender,tv_age;
        private ImageView btndelete;
        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_owner = itemView.findViewById(R.id.tv_owner);
            tv_breed = itemView.findViewById(R.id.tv_breed);
            tv_petname = itemView.findViewById(R.id.tv_petname);
            tv_gender = itemView.findViewById(R.id.tv_gender);
            tv_age = itemView.findViewById(R.id.tv_age);
            btndelete = itemView.findViewById(R.id.btndelete);
        }
    }
}
