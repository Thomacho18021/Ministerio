package com.srcarrillo.ministerio.ui.ayudanos;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.srcarrillo.ministerio.R;

public class AyudanosFragment extends Fragment {

    private AyudanosViewModel mViewModel;

    public static AyudanosFragment newInstance() {
        return new AyudanosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ayudanos_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AyudanosViewModel.class);
        // TODO: Use the ViewModel
    }

}