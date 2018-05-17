package app.manish.locationupdates;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import app.manish.locationupdates.connect.AllEventListener;
import app.manish.locationupdates.connect.IEventListener;
import app.manish.locationupdates.constants.DataConstants;
import app.manish.locationupdates.fragments.PhoneVerify;
import app.manish.locationupdates.fragments.SignUp;
import app.manish.locationupdates.handlers.InformationReceiver;
import app.manish.locationupdates.view.IView;

public class MainActivity extends AppCompatActivity implements IView {

    Fragment fragment;
    FragmentTransaction fragmentTransaction;
    public static IEventListener mEventListener;
    InformationReceiver informationReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new SignUp();
        mEventListener = new AllEventListener(this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mEventListener.changeFragment(new PhoneVerify());
    }

    @Override
    protected void onStart() {
        super.onStart();
        informationReceiver = new InformationReceiver(new ProgressDialog(this));
        registerLocalReceiver();
    }

    private void registerLocalReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DataConstants.UPDATE_SHAREDPREF);
        intentFilter.addAction(DataConstants.SHOW_PROGRESS_DIALOG);
        intentFilter.addAction(DataConstants.HIDE_PROGRESS_DIALOG);
        registerReceiver(informationReceiver, intentFilter);
    }

    @Override
    public void updateFragment(Fragment fragment) {
        if (fragment != null) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment, fragment).commit();
        }
    }

    @Override
    public void moveToHome() {
        Intent i = new Intent(this, HomePage.class);
        startActivity(i);
        unregisterReceiver(informationReceiver);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
