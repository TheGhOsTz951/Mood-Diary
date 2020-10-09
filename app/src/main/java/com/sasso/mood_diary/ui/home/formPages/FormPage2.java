package com.sasso.mood_diary.ui.home.formPages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class FormPage2 extends Fragment {
    final String FILE_NAME = "data.txt";

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
                savePref();
                liveData.setBtnConfirmClick(true);
            }
        });

        return view;
    }

    // Al momento inutilizzato
    private void writeFile() {
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = getContext().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write("due".getBytes());

            fileOutputStream.close();
        } catch (Exception e) {}
    }

    private void savePref() {
        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int temp = prefs.getInt("test", 0);
        temp++;

        editor.putInt("test", temp);

        editor.apply();
    }
}
