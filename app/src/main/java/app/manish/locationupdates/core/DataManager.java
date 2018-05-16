package app.manish.locationupdates.core;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import app.manish.locationupdates.connect.IPhoneListener;
import app.manish.locationupdates.connect.IRegisterListener;
import app.manish.locationupdates.connect.ISplashWorker;
import app.manish.locationupdates.constants.DataConstants;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public class DataManager {
    private static final String TAG = "DataManager";
    IRegisterListener mRL;
    IPhoneListener mPL;
    ISplashWorker mSW;
    private boolean alreadyLoggedIn;
    Context mContext;
    SharedPreferences sharedPreferences;
    String userKey;
    FirebaseHandler firebaseHandler;


    public DataManager(IRegisterListener registerListener) {
        mRL = registerListener;
    }

    /*public DataManager(IPhoneListener phoneListener, Context context) {
        mPL = phoneListener;
    }*/

    public DataManager(Object object, Context context) {
        if (object instanceof IPhoneListener) {
            mPL = (IPhoneListener) object;
        }
        //mSW = sw;
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(DataConstants.SHARED_PREF, Context.MODE_PRIVATE);
        firebaseHandler = new FirebaseHandler(mContext, this);
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
        userKey = sharedPreferences.getString(DataConstants.USER_ID, "");
        if (!userKey.isEmpty()) {
            return true;
        }
        return false;
    }

    public UserInformation getUserDetails() {
        String name = "", phone = "";
        UserInformation userInformation;
        if (FrontEndUtils.isInternetConnected(mContext)) {
            userInformation = firebaseHandler.getUserInformation(userKey);
        } else {
            name = sharedPreferences.getString(DataConstants.USER_NAME, "");
            phone = sharedPreferences.getString(DataConstants.USER_PHONE, "");
            userInformation = new UserInformation(name, phone);
        }
        return userInformation;
    }

    public void setAlreadyLoggedIn(boolean alreadyLoggedIn) {
        this.alreadyLoggedIn = alreadyLoggedIn;
    }

    public void sendOtpToDevice(String number, PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks) {
        String phone = "+91" + number;
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phone, 30, TimeUnit.SECONDS, (Activity) mContext, callbacks);
        Log.v(TAG, "OTP sending to: " + phone);
    }

    public void signInUsingCredentials(PhoneAuthCredential phoneAuthCredential) {
        firebaseHandler.signInUsingCredentials(phoneAuthCredential);
    }

    public void phoneVerified(boolean isVarified) {
        mPL.phoneVerified(isVarified);

    }
}
