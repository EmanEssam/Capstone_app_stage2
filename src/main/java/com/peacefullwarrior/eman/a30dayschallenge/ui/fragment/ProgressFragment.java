package com.peacefullwarrior.eman.a30dayschallenge.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peacefullwarrior.eman.a30dayschallenge.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressFragment extends Fragment {


    public ProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(getString(R.string.my_progress));
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

}
