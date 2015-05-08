package com.github.devnied.emvnfccard.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.devnied.emvnfccard.R;
import com.github.devnied.emvnfccard.adapter.MenuDrawerAdapter;
import com.github.devnied.emvnfccard.fragment.AboutFragment;
import com.github.devnied.emvnfccard.fragment.BillingFragment;
import com.github.devnied.emvnfccard.fragment.ConfigurationFragment;
import com.github.devnied.emvnfccard.fragment.IRefreshable;
import com.github.devnied.emvnfccard.fragment.ViewPagerFragment;
import com.github.devnied.emvnfccard.utils.ConstantUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import de.keyboardsurfer.android.widget.crouton.Crouton;


public class SimplePayActivity extends FragmentActivity implements AdapterView.OnItemClickListener, View.OnClickListener  {
    ArrayList<Integer> price = new ArrayList<Integer>();
    Button oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton, zeroButton, back;
    /**
     * last selected Menu
     */
    private int mLastSelectedMenu = -1;
    /**
     * Reference for refreshable content
     */
    private WeakReference<IRefreshable> mRefreshableContent;
    /**
     * Action bar drawer toggle
     */
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    /**
     * Drawer layout
     */
    private DrawerLayout mDrawerLayout;

    /**
     * Menu adapter
     */
    private MenuDrawerAdapter mMenuAdapter;


    /**
     * ListView drawer
     */
    private ListView mDrawerListView;


    public final static String EXTRA_CART_CONTENTS = "com.rbrjas.vappid.CART_CONTENTS";
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //prices = new ArrayList<Integer>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_pay);

        /* Sidebar menu */
        // get ListView defined in activity_main.xml
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mMenuAdapter = new MenuDrawerAdapter(this);
        mDrawerListView.setAdapter(mMenuAdapter);
        mDrawerListView.setOnItemClickListener(this);

        // 2. App Icon
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // 2.1 create ActionBarDrawerToggle
        mActionBarDrawerToggle = new ActionBarDrawerToggle(/* */
                this, /* host Activity */
                mDrawerLayout, /* DrawerLayout object */
                R.drawable.ic_drawer, /* nav drawer icon to replace 'Up' caret */
                R.string.navigation_menu_open, /* "open drawer" description */
                R.string.navigation_menu_close /* "close drawer" description */
        );

        // 2.2 Set actionBarDrawerToggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayUseLogoEnabled(false);
        getActionBar().setDisplayShowCustomEnabled(true);


        oneButton = (Button) findViewById(R.id.numberOne);
        twoButton = (Button) findViewById(R.id.numberTwo);
        threeButton = (Button) findViewById(R.id.numberThree);
        fourButton = (Button) findViewById(R.id.numberFour);
        fiveButton = (Button) findViewById(R.id.numberFive);
        sixButton = (Button) findViewById(R.id.numberSix);
        sevenButton = (Button) findViewById(R.id.numberSeven);
        eightButton = (Button) findViewById(R.id.numberEight);
        nineButton = (Button) findViewById(R.id.numberNine);
        zeroButton = (Button) findViewById(R.id.numberZero);
        back = (Button) findViewById(R.id.numberBack);



        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);
        zeroButton.setOnClickListener(this);

        back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.numberOne:
                price.add(1);
                break;
            case R.id.numberTwo:
                price.add(2);
                break;
            case R.id.numberThree:
                price.add(3);
                break;
            case R.id.numberFour:
                price.add(4);
                break;
            case R.id.numberFive:
                price.add(5);
                break;
            case R.id.numberSix:
                price.add(6);
                break;
            case R.id.numberSeven:
                price.add(7);
                break;
            case R.id.numberEight:
                price.add(8);
                break;
            case R.id.numberNine:
                price.add(9);
                break;
            case R.id.numberZero:
                price.add(0);
                break;

            case R.id.numberBack:
                TextView tx = (TextView) findViewById(R.id.edit_price);
                String str = tx.getText().toString();
                tx.setText("");
                str = str.substring ( 0, str.length() - 1 );
                // Now set this Text to your edit text
                tx.append(str);
                break;
            default:
                break;
        }
        TextView editText = (TextView) findViewById(R.id.edit_price);
        for (int i = 0; i < price.size(); i++){
            String n = price.get(i).toString();
            editText = (TextView) findViewById(R.id.edit_price);
            editText.append(n);
            price.clear();
        }



    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (mLastSelectedMenu == ConstantUtils.ABOUT) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
            if (fragment != null) {
                BillingFragment billing = (BillingFragment) fragment.getChildFragmentManager().findFragmentById(R.id.about_inapp_content);
                if (billing != null) {
                    billing.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (mActionBarDrawerToggle.onOptionsItemSelected(item)) {
            Crouton.cancelAllCroutons();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

   /* public void onClick(final View v) {
        if (mDrawerListView != null) {
            mDrawerListView.performItemClick(mDrawerListView, 2, mDrawerListView.getItemIdAtPosition(2));
        }

    }*/




    public void goPay(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
        if (mLastSelectedMenu != position) {
            Fragment fragment = null;
            switch (position) {
                case ConstantUtils.CARDS_DETAILS:
                    fragment = new ViewPagerFragment();
                    refreshContent();
                    break;
                case ConstantUtils.CONFIGURATION:
                    fragment = new ConfigurationFragment();
                    break;
                case ConstantUtils.ABOUT:
                    fragment = new AboutFragment();
                    break;
                default:
                    break;
            }
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
            }
            mLastSelectedMenu = position;
        }
        mDrawerLayout.closeDrawer(mDrawerListView);
    }

    private void refreshContent() {
        if (mRefreshableContent != null && mRefreshableContent.get() != null) {
            mRefreshableContent.get().update();
        }
    }



    public void quickPay(View view) {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }

}
