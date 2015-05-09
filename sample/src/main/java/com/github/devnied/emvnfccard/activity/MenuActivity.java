package com.github.devnied.emvnfccard.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.github.devnied.emvnfccard.R;
import com.github.devnied.emvnfccard.dialog.LoginDialogFragment;

//import android.support.v7.app.AppCompatActivity; //Notudum fyrst.. breyttum i extends Activity f. Theme.Holo


public class MenuActivity extends FragmentActivity
                          implements LoginDialogFragment.LoginDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void registerUser(View view) {
        //Okkar context.. svo class a activity sem tekur vid..
        Intent intent = new Intent(MenuActivity.this, RegisterUserActivity.class);
        startActivity(intent); //Kveikjum a activity..
    }

    public void logIn(View view) {
        /*
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent); */
        LoginDialogFragment loginDialog = new LoginDialogFragment();
        loginDialog.show(getFragmentManager(), "Login_Dialog");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        Intent intent = new Intent(this, SimplePayActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // User touched the dialog's negative button
    }

    @Override
    public void onDialogNeutralClick(DialogFragment dialog) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }
}
