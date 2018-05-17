package app.manish.locationupdates.core;

import android.app.Activity;
import android.content.Context;
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
    UserInformation userInformation;
    Context mContext;
    String userKey;
    FirebaseHandler firebaseHandler;
    SharedPreferenceManager sharedPreferenceManager;

    public <T> DataManager(T object, Context context) {
        Log.v(TAG, "object instance of IPhoneListener:" + (object instanceof IPhoneListener));
        Log.v(TAG, "object instance of IRegisterListener:" + (object instanceof IRegisterListener));
        if (object instanceof IPhoneListener) {
            mPL = (IPhoneListener) object;
        } else if (object instanceof IRegisterListener) {
            mRL = (IRegisterListener) object;
        }
        //mSW = sw;
        mContext = context;
        sharedPreferenceManager = new SharedPreferenceManager(mContext);
        firebaseHandler = new FirebaseHandler(mContext, this);
    }

    public boolean isValidCredentials(String username, String password) {
        return true;
    }

    public String saveUserData(UserInformation userInformation) {
        firebaseHandler.updateDetails(userInformation);
        return DataConstants.LOGIN_SUCCESS;
    }

    public boolean isValidNumber(String number) {
        return FrontEndUtils.isValidMobileNumber(number);
    }


    public boolean isAlreadyLoggedIn() {
        userInformation = sharedPreferenceManager.getUserData();
        if (userInformation != null) {
            return true;
        }
        return false;
    }

    public UserInformation getUserDetails() {
        UserInformation userInformation;
        if (FrontEndUtils.isInternetConnected(mContext)) {
            userInformation = firebaseHandler.getUserInformation(userKey);
        } else {
            userInformation = sharedPreferenceManager.getUserData();
        }
        return userInformation;
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

    public boolean isValidOtp(String otp) {
        return FrontEndUtils.isValidOtp(otp);
    }
}
