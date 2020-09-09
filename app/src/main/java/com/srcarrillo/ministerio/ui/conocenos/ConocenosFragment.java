package com.srcarrillo.ministerio.ui.conocenos;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srcarrillo.ministerio.R;

public class ConocenosFragment extends Fragment {

    private ConocenosViewModel mViewModel;

    public static ConocenosFragment newInstance() {
        return new ConocenosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.conocenos_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ConocenosViewModel.class);
        // TODO: Use the ViewModel
    }

}