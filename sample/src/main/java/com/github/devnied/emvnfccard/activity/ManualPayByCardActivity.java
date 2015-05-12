package com.github.devnied.emvnfccard.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.github.devnied.emvnfccard.R;


public class ManualPayByCardActivity extends Activity{

    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_pay_by_card);
        progress = new ProgressDialog(this);




       /* Button btn = (Button) findViewById(R.id.ccPay);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*progress.setMessage("Greiðsla í vinnslu...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);
                progress.show();

                Toast.makeText(getApplicationContext(), "Þú hefur greitt með kreditkortinu þínu!",
                        Toast.LENGTH_LONG).show();
            }
        });*/
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
                    dcForm.setVisibility(View.GONE);
                    ccForm.setVisibility(View.VISIBLE);
                    break;
            case R.id.radio_debit:
                if (checked)
                    ccForm.setVisibility(View.GONE);
                    dcForm.setVisibility(View.VISIBLE);
                    //Display debit form
                    break;
        }
    }

    public void cccpayed(View view) {
        progress.setMessage("Greiðsla í vinnslu...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(ManualPayByCardActivity.this, SimplePayActivity.class);
                Toast.makeText(getApplicationContext(), "Þú hefur greitt með kreditkortinu þínu!",
                        Toast.LENGTH_LONG).show();
                startActivity(intent);

            }
        }, 3000);


    }



}
