package com.sasso.mood_diary.ui.home;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.sasso.mood_diary.R;

import java.util.Locale;

public class HomeFragment extends Fragment {
    private static final long START_TIME_IN_MILLIS = 20000;
    private static final int DIV_PROGRESS_BAR = 50;

    private TextView txtCont;
    private TextView txtTest;
    private Button btnStart;
    private ProgressBar progressBar;

    private CountDownTimer mCountDownTimer;
    private boolean mTimeRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progress_bar);
        btnStart = view.findViewById(R.id.btnStart);
        txtCont = view.findViewById(R.id.txtCont);
        txtTest = view.findViewById(R.id.txtTest);

        progressBar.setMax((int) START_TIME_IN_MILLIS / DIV_PROGRESS_BAR);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimeRunning) {
                    restartTimer();
                } else {
                    mTimeLeftInMillis = START_TIME_IN_MILLIS;
                    startTimer();
                }
            }
        });

        return view;
    }
<<<<<<<

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("millisLeft", mTimeLeftInMillis);
        outState.putBoolean("timerRunning", mTimeRunning);
    }

    /*
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
        mTimeRunning = savedInstanceState.getBoolean("timerRunning");

        updateCountDownText();
        updateProgressBar();

        if (mTimeRunning){
            startTimer();
        }
    }
    */

    private void startTimer() {
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
            }
        }.start();

        mTimeRunning = true;
    }

    private void restartTimer() {
        mCountDownTimer.cancel();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        startTimer();
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
=======

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("millisLeft", mTimeLeftInMillis);
        outState.putBoolean("timerRunning", mTimeRunning);
    }

    /*
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
        mTimeRunning = savedInstanceState.getBoolean("timerRunning");

        updateCountDownText();
        updateProgressBar();

        if (mTimeRunning){
            startTimer();
        }
    }
    */

    private void startTimer() {
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
            }
        }.start();

        mTimeRunning = true;
    }

    private void restartTimer() {
        mCountDownTimer.cancel();
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        startTimer();
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
>>>>>>>
}