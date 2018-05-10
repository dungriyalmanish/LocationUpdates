package app.manish.locationupdates;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import app.manish.locationupdates.connect.AllEventListener;
import app.manish.locationupdates.connect.IEventListener;
import app.manish.locationupdates.fragments.PhoneVerify;
import app.manish.locationupdates.fragments.SignUp;
import app.manish.locationupdates.view.IView;

public class MainActivity extends AppCompatActivity implements IView {

    Fragment fragment;
    FragmentTransaction fragmentTransaction;
    public static IEventListener mEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new SignUp();
        mEventListener = new AllEventListener(this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mEventListener.changeFragment(new SignUp());
    }

    @Override
    public void updateFragment(Fragment fragment) {
        if (fragment != null) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment, fragment).commit();
        }
    }
}
