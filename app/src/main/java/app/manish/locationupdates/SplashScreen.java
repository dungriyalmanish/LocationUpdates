package app.manish.locationupdates;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.UUID;

import app.manish.locationupdates.connect.ISplashWorker;
import app.manish.locationupdates.connect.SplashConnector;
import app.manish.locationupdates.view.ISplashVIew;

public class SplashScreen extends AppCompatActivity implements ISplashVIew {

    private static final String TAG = "SplashScreen";
    AccountManager accountManager;
    ISplashWorker mSW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        accountManager = AccountManager.get(this);
        mSW = new SplashConnector(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    mSW.checkUserExistance(SplashScreen.this);
                } catch (InterruptedException e) {
                }
            }
        }).start();

    }

    @Override
    public void skipRegistration(boolean skip) {
        Intent intent;
        if (skip) {
            intent = new Intent(this, HomePage.class);
        } else {
            intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
    }
}
