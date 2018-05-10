package app.manish.locationupdates.connect;


import android.support.v4.app.Fragment;

public interface IEventListener {
    void changeFragment(Fragment fragment);
    void verifyMobile(String mobileNumber);
}
