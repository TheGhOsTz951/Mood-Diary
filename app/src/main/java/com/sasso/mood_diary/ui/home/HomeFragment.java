package com.sasso.mood_diary.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.sasso.mood_diary.R;
import com.sasso.mood_diary.ui.home.formPages.*;

import java.util.ArrayList;
import java.util.List;
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

    // TODO: Provare a cambiarela progressbar in modo tale che usi un animazione e non il timer per migliorare le prestazioni
    //  IDEA: Animazione dura mTimeLeftInMillis

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressBar = view.findViewById(R.id.progress_bar);
        btnStart = view.findViewById(R.id.btnStart);
        txtCont = view.findViewById(R.id.txtCont);

        ConstraintLayout constraintLayout = view.findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        progressBar.setMax((int) START_TIME_IN_MILLIS / DIV_PROGRESS_BAR);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] coordinate = new int[2];
                btnStart.getLocationInWindow(coordinate);

                new FormFragment(coordinate[1]).show(getChildFragmentManager(), "");
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
            btnStart.setEnabled(false);
        } else {
            btnStart.setEnabled(true);
            progressBar.setProgress(0);
            txtCont.setText("00:00");
        }
    }

}
