package com.suman.kennelservice.ui.DogRegister;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DogRegisterModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DogRegisterModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}