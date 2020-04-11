package com.hvasoftware.parsehtmlusejsoup.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.hvasoftware.parsehtmlusejsoup.R;
import com.hvasoftware.parsehtmlusejsoup.swipeback.BaseSwipeBackFragment;

public class SecondFragment extends BaseSwipeBackFragment {


    public static SecondFragment newInstance() {

        Bundle args = new Bundle();

        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        return attachToSwipeBack(view);
    }


}
