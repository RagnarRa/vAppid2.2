package com.github.devnied.emvnfccard.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.devnied.emvnfccard.R;

/**
 * Created by Sindri on 30/04/15.
 */
public class helloFragment extends Fragment {


    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.helloworld, container, false);

    }
}
