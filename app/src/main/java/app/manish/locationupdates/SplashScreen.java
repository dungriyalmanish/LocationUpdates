package app.manish.locationupdates;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.manish.locationupdates.connect.ISplashWorker;
import app.manish.locationupdates.connect.SplashConnector;
import app.manish.locationupdates.core.UserInformation;
import app.manish.locationupdates.view.ISplashVIew;

import static app.manish.locationupdates.constants.DataConstants.USER_INFO;

public class SplashScreen extends AppCompatActivity implements ISplashVIew {

    private static final String TAG = "SplashScreen";
    ISplashWorker mSW;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mSW = new SplashConnector(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mSW.checkUserExistence();
                } catch (InterruptedException e) {
                }
            }
        }).start();

    }

    @Override
    public void skipRegistration(boolean skip, UserInformation userInformation) {
        Intent intent;
        if (skip) {
            intent = new Intent(this, HomePage.class);
            intent.putExtra(USER_INFO, userInformation);
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
