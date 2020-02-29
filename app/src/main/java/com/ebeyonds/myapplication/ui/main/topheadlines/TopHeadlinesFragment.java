package com.ebeyonds.myapplication.ui.main.topheadlines;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebeyonds.myapplication.R;

public class TopHeadlinesFragment extends Fragment {

    private TopHeadlinesViewModel mViewModel;

    public static TopHeadlinesFragment newInstance() {
        return new TopHeadlinesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.top_headlines_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TopHeadlinesViewModel.class);
        // TODO: Use the ViewModel
    }

}
