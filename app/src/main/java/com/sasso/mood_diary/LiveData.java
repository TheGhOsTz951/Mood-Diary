package com.sasso.mood_diary;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LiveData extends ViewModel {
    private MutableLiveData<Boolean> btnConfirmClick = new MutableLiveData<Boolean>();

    public void setBtnConfirmClick(Boolean t) {
        btnConfirmClick.setValue(t);
    }

    public MutableLiveData<Boolean> getBtnConfirmClick() {
        return btnConfirmClick;
    }
}
