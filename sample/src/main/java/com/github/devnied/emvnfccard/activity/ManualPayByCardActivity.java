package com.github.devnied.emvnfccard.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.github.devnied.emvnfccard.R;


public class ManualPayByCardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_pay_by_card);
    }



    public void backToPaymentPage(View view) {
        finish();
    }

    public void onRadioButtonClicked(View view) {
        LinearLayout ccForm  = (LinearLayout) findViewById(R.id.form_cc);
        LinearLayout dcForm = (LinearLayout) findViewById(R.id.form_dc);
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_credit:
                if (checked)
                    //Display credit form
                    Log.d("waii", "Hello");
                    dcForm.setVisibility(View.GONE);
                    ccForm.setVisibility(View.VISIBLE);
                    break;
            case R.id.radio_debit:
                if (checked)
                    Log.d("waii2", "Hi");
                    ccForm.setVisibility(View.GONE);
                    dcForm.setVisibility(View.VISIBLE);
                    //Display debit form
                    break;
        }
    }
}
