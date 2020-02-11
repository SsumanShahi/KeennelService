package com.suman.kennelservice.ui.Sensor;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suman.kennelservice.R;
import com.suman.kennelservice.activity.ProximityActivity;

public class SensorFragment extends Fragment {

    private SensorViewModel mViewModel;

    public static SensorFragment newInstance() {
        return new SensorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         View view =inflater.inflate(R.layout.sensor_fragment, container, false);
            Intent intent = new Intent(getContext(), ProximityActivity.class);
            startActivity(intent);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SensorViewModel.class);
        // TODO: Use the ViewModel
    }

}
