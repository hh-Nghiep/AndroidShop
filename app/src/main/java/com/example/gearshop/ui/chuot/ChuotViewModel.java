package com.example.gearshop.ui.chuot;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChuotViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ChuotViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Chuột fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
