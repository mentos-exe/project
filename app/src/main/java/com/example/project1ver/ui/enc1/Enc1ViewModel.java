package com.example.project1ver.ui.enc1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Enc1ViewModel extends ViewModel {

    private MutableLiveData<String> mText2;
    private MutableLiveData<String> mText3;
    public Enc1ViewModel() {

        mText2 = new MutableLiveData<>();
        mText2.setValue("Зашифровать");
        mText3 = new MutableLiveData<>();
        mText3.setValue("Расшифровать");
    }



    public LiveData<String> getText2() {
        return mText2;
    }

    public LiveData<String> getText3() {
        return mText3;
    }
}