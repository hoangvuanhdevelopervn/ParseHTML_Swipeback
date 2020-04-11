package com.hvasoftware.parsehtmlusejsoup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.hvasoftware.parsehtmlusejsoup.swipeback.BaseSwipeBackFragment;
import com.hvasoftware.parsehtmlusejsoup.swipeback.SwipeBackActivity;

public abstract class BaseActivity extends SwipeBackActivity implements BaseSwipeBackFragment.OnAddFragmentListener {

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void setUpView();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        setUpView();
    }


    @Override
    public boolean swipeBackPriority() {
        return super.swipeBackPriority();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public void loadFragment(Fragment toFragment, int container) {
        getSupportFragmentManager().beginTransaction()
                .add(container, toFragment, toFragment.getClass().getSimpleName())
                .addToBackStack(toFragment.getClass().getSimpleName())
                .commit();
    }

    @Override
    public void onAddFragment(Fragment fromFragment, Fragment toFragment, int container) {
        addFragment(fromFragment, toFragment, container);
    }

    public void addFragment(Fragment fromFragment, Fragment toFragment, int container) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.h_fragment_enter, R.anim.h_fragment_exit, R.anim.h_fragment_pop_enter, R.anim.h_fragment_pop_exit)
                .add(container, toFragment, toFragment.getClass().getSimpleName())
                .hide(fromFragment)
                .addToBackStack(toFragment.getClass().getSimpleName())
                .commit();
    }


}
