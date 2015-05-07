package com.github.devnied.emvnfccard.activity;

import android.content.Intent;
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


public class AddToCartActivity extends FragmentActivity implements AdapterView.OnItemClickListener, View.OnClickListener  {
    ArrayList<Integer> prices;
    Button button;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prices = new ArrayList<Integer>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        Integer total = 0;
        for (Integer num : prices) {
            total += num;
        }

        TextView temp = (TextView) findViewById(R.id.text_total);
        temp.setText("Upphæð til greiðslu: " + total);


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

    public void onClick(final View v) {
        if (mDrawerListView != null) {
            mDrawerListView.performItemClick(mDrawerListView, 2, mDrawerListView.getItemIdAtPosition(2));
        }

    }

    public void addToCart(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_price);
        Integer price = Integer.parseInt(editText.getText().toString());
        prices.add(price);
        editText.setText("");
    }

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





}
