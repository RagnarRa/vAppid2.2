package com.github.devnied.emvnfccard.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.devnied.emvnfccard.R;
import com.github.devnied.emvnfccard.activity.MenuActivity;

/**
 * Created by Sindri on 11/05/15.
 */
public class LogOutFragment extends Fragment{

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.activity_simple_pay, container, false);

        Intent intent = new Intent(getActivity() ,MenuActivity.class);
        startActivity(intent);

        return rootView;
    }
}
