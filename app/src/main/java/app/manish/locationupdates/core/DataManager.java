package app.manish.locationupdates.core;

import app.manish.locationupdates.connect.IPhoneListener;
import app.manish.locationupdates.connect.IRegisterListener;
import app.manish.locationupdates.connect.ISplashWorker;
import app.manish.locationupdates.constants.DataConstants;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public class DataManager {

    IRegisterListener mRL;
    IPhoneListener mPL;
    ISplashWorker mSW;
    private boolean alreadyLoggedIn;

    public DataManager(IRegisterListener registerListener) {
        mRL = registerListener;
    }

    public DataManager(IPhoneListener phoneListener) {
        mPL = phoneListener;
    }

    public DataManager(ISplashWorker sw) {
        mSW = sw;
    }

    public boolean isValidCredentials(String username, String password) {
        return true;
    }

    public String loginUsingCredentials(String username, String password) {
        return DataConstants.LOGIN_SUCCESS;
    }

    public boolean isValidNumber(String number) {
        return FrontEndUtils.isValidMobileNumber(number);
    }


    public boolean isAlreadyLoggedIn() {
        String id = FrontEndUtils.getId();
        //Check ID with firebase database.s
        return true;
    }

    public void setAlreadyLoggedIn(boolean alreadyLoggedIn) {
        this.alreadyLoggedIn = alreadyLoggedIn;
    }
}
