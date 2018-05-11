package app.manish.locationupdates.connect;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import app.manish.locationupdates.view.IHomeView;

/**
 * Created by manish.dungriyal on 11-05-2018.
 */

public class HomeConnector implements IHomeListener {
    IHomeView mHV;

    public HomeConnector(IHomeView homeView) {
        mHV = homeView;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
