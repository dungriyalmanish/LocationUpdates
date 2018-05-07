package app.manish.locationupdates;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import app.manish.locationupdates.fragments.PhoneVerify;
import app.manish.locationupdates.fragments.SignUp;

public class MainActivity extends AppCompatActivity implements ChangeFragmentListener {

    Fragment fragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new SignUp();
        //fragmentManager = getSupportFragmentManager();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        updateFragment();
    }

    @Override
    public void changeFragment() {
        fragment = new PhoneVerify();
        updateFragment();
    }

    public void updateFragment() {
        if (fragment != null) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment, fragment).commit();
        }
    }
}
