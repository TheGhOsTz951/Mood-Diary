package com.sasso.mood_diary.ui.dashboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sasso.mood_diary.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class DashboardFragment extends Fragment {
    final String FILE_NAME = "data.txt";

    private Button button;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        final Random rand = new Random();

        button = view.findViewById(R.id.button2);
        textView = view.findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setTextColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        readPref();
    }

    // Al momento inutilizzato
    private void loadFile() {
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = getContext().openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String test = bufferedReader.readLine();

            if (test != null) {
                textView.setText("Sei " + test + " depresso!");
            }

            fileInputStream.close();
        } catch (Exception e){}
    }

    private void readPref() {
        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        textView.setText("Sei " + prefs.getInt("test", 0) + " depresso!");
    }
}