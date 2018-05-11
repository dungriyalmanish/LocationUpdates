package app.manish.locationupdates.connect;

import android.content.Context;

import java.util.UUID;

import app.manish.locationupdates.core.DataManager;
import app.manish.locationupdates.view.ISplashVIew;

/**
 * Created by manish.dungriyal on 11-05-2018.
 */

public class SplashConnector implements ISplashWorker {
    ISplashVIew mSV;
    DataManager mDM;

    public SplashConnector(ISplashVIew iSplashVIew) {
        mSV = iSplashVIew;
        mDM = new DataManager(this);
    }

    @Override
    public void checkUserExistance() {
        boolean loggedIn = mDM.isAlreadyLoggedIn();
        mSV.skipRegistration(true);
    }
}
