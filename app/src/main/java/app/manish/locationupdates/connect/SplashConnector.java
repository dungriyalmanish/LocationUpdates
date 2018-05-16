package app.manish.locationupdates.connect;

import android.content.Context;
import android.util.Log;

import app.manish.locationupdates.core.DataManager;
import app.manish.locationupdates.core.UserInformation;
import app.manish.locationupdates.view.ISplashVIew;

/**
 * Created by manish.dungriyal on 11-05-2018.
 */

public class SplashConnector implements ISplashWorker {
    private static final String TAG = "SplashConnector";
    ISplashVIew mSV;
    DataManager mDM;
    Context mContext;

    public SplashConnector(Context context) {
        mSV = (ISplashVIew) context;
        mContext = context;
        mDM = new DataManager(this, mContext);
    }

    @Override
    public void checkUserExistence() {
        Log.v(TAG, "checkUserExistance");
        boolean loggedIn = mDM.isAlreadyLoggedIn();
        if (loggedIn) {
            UserInformation userInformation = mDM.getUserDetails();
            mSV.skipRegistration(true, userInformation);
        } else {
            mSV.skipRegistration(false, null);
        }

    }

    @Override
    public void phoneVerified(boolean isVarified) {
        if (isVarified) {

        } else {
        }
    }
}
