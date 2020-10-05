package com.sasso.mood_diary.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sasso.mood_diary.R;

import java.util.Locale;

public class HomeFragment extends Fragment {
    private static final long START_TIME_IN_MILLIS = 21000;
    private static final int DIV_PROGRESS_BAR = 50;

    private TextView txtCont;
    private Button btnStart;
    private ProgressBar progressBar;

    private CountDownTimer mCountDownTimer;
    private boolean mTimeRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mEndTime;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progress_bar);
        btnStart = view.findViewById(R.id.btnStart);
        txtCont = view.findViewById(R.id.txtCont);

        progressBar.setMax((int) START_TIME_IN_MILLIS / DIV_PROGRESS_BAR);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimeRunning) {
                } else {
                    mTimeLeftInMillis = START_TIME_IN_MILLIS;
                    startTimer();
                    updateVisual();
                }
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimeRunning);
        editor.putLong("endTime", mEndTime);

        editor.apply();

        if (mTimeRunning) mCountDownTimer.cancel();
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences prefs = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);

        mTimeLeftInMillis = prefs.getLong("millisLeft", START_TIME_IN_MILLIS);
        mTimeRunning = prefs.getBoolean("timerRunning", false);

        if (mTimeRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimeRunning = false;
            } else {
                startTimer();
            }
        }

        updateVisual();
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 50) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateProgressBar();
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimeRunning = false;
                txtCont.setText("End");
                updateVisual();
                mCountDownTimer.cancel();
            }
        }.start();

        mTimeRunning = true;
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        txtCont.setText(timeLeftFormatted);
    }

    private void updateProgressBar() {
        int progress = (int) ((START_TIME_IN_MILLIS / DIV_PROGRESS_BAR) - (mTimeLeftInMillis / DIV_PROGRESS_BAR));
        progressBar.setProgress(progress);
    }

    private void updateVisual() {
        if (mTimeRunning) {
            btnStart.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            txtCont.setVisibility(View.VISIBLE);
        } else {
            btnStart.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            txtCont.setVisibility(View.INVISIBLE);
        }
    }
}