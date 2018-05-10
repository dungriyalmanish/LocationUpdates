package app.manish.locationupdates.connect;


import android.support.v4.app.Fragment;

import app.manish.locationupdates.view.IView;

public class AllEventListener implements IEventListener {
    IView mIview;

    public AllEventListener(IView iView) {
        this.mIview = iView;
    }

    @Override
    public void changeFragment(Fragment fragment) {
        mIview.updateFragment(fragment);
    }

    @Override
    public void verifyMobile(String mobileNumber) {

    }
}
