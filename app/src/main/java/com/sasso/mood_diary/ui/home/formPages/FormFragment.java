package com.sasso.mood_diary.ui.home.formPages;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import com.sasso.mood_diary.R;

import java.util.ArrayList;
import java.util.List;

public class FormFragment extends DialogFragment {
    private ViewPager formPager;
    private FormPagerAdapter formPagerAdapter;

    private int yAxis;

    public FormFragment(int yAxis){
        this.yAxis = yAxis;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_popup, container, false);

        formPager = view.findViewById(R.id.formPager);

        List fragmentList = new ArrayList();
        fragmentList.add(new FormPage1());
        fragmentList.add(new FormPage2());

        formPagerAdapter = new FormPagerAdapter(getChildFragmentManager(), fragmentList);
        formPager.setAdapter(formPagerAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Imposta il layout del popup
        WindowManager.LayoutParams wlp = getDialog().getWindow().getAttributes();
        wlp.gravity = Gravity.TOP;
        wlp.height = yAxis;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND; Rimuove background opacita
        getDialog().getWindow().setAttributes(wlp);
    }
}
