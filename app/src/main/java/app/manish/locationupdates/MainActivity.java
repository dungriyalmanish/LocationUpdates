package app.manish.locationupdates;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import app.manish.locationupdates.fragments.PhoneVerify;
import app.manish.locationupdates.fragments.SignUp;

public class MainActivity extends AppCompatActivity implements ChangeFragmentListener {

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new SignUp();
        updateFragment();
    }

    @Override
    public void changeFragment() {
        fragment = new PhoneVerify();
        updateFragment();
    }

    public void updateFragment() {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().detach(new SignUp());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
        }
    }
}
