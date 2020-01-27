package com.suman.kennelservice.adaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suman.kennelservice.R;
import com.suman.kennelservice.Url.url;
import com.suman.kennelservice.model.Appointment;
import com.suman.kennelservice.model.Dogbreeds;

import java.util.List;

public class AppointmentAdaptar extends RecyclerView.Adapter<AppointmentAdaptar.AppointmentViewHolder> {
    Context mcontext;
    List<Appointment> appointments;

    public AppointmentAdaptar(Context mcontext, List<Appointment> appointments) {
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

        final Appointment appointment = appointments.get(position);
        holder.tv_owner.setText(appointment.getOwnerName());
        holder.tv_petname.setText(appointment.getPetname());
        holder.tv_breed.setText(appointment.getBreed());
        holder.tv_gender.setText(appointment.getGender());
        holder.tv_age.setText(appointment.getAge());
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_owner,tv_breed,tv_petname,tv_gender,tv_age;
        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_owner = itemView.findViewById(R.id.tv_owner);
            tv_breed = itemView.findViewById(R.id.tv_breed);
            tv_petname = itemView.findViewById(R.id.tv_petname);
            tv_gender = itemView.findViewById(R.id.tv_gender);
            tv_age = itemView.findViewById(R.id.tv_age);
        }
    }
}
