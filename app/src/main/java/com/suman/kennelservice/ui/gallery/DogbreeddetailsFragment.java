package com.suman.kennelservice.ui.gallery;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suman.kennelservice.R;

public class DogbreeddetailsFragment extends Fragment {

    private DogbreeddetailsViewModel mViewModel;

    public static DogbreeddetailsFragment newInstance() {
        return new DogbreeddetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dogbreeddetails_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DogbreeddetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}
