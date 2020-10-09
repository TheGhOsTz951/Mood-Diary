package com.sasso.mood_diary;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveData extends ViewModel {
    private MutableLiveData<String> btnConfirmClick = new MutableLiveData<String>();

    public void setBtnConfirmClick(String t) {
        btnConfirmClick.setValue(t);
    }

    public MutableLiveData<String> getBtnConfirmClick() {
        return btnConfirmClick;
    }
}
