package com.sasso.mood_diary.ui.home.formPages;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sasso.mood_diary.LiveData;
import com.sasso.mood_diary.R;

public class FormPage2 extends Fragment {
    private Button btnConfirm;
    private LiveData liveData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_page2, container, false);

        btnConfirm = view.findViewById(R.id.btnConfirm);

        liveData = new ViewModelProvider(requireActivity()).get(LiveData.class);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveData.setBtnConfirmClick("");
            }
        });

        return view;
    }
}
