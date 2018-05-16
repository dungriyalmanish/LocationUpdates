package app.manish.locationupdates.connect;

import android.content.Context;
import android.util.Log;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import app.manish.locationupdates.constants.DataConstants;
import app.manish.locationupdates.core.DataManager;
import app.manish.locationupdates.view.IPhoneView;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public class PhoneConnector implements IPhoneListener {
    private static final String TAG = "PhoneConnector";
    DataManager mDM;
    IPhoneView mPhoneView;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks phoneAuthCallback;
    String verificationID;

    public PhoneConnector(IPhoneView iPhoneView, Context context) {
        mPhoneView = iPhoneView;
        mDM = new DataManager(this, context);
        phoneAuthCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Log.v(TAG, "OTP verified. Now login with credentials");
                mDM.signInUsingCredentials(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.v(TAG, "OTP verification failed: " + e.getMessage());
                mPhoneView.otpVerificationFailed(e.getMessage());
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                mPhoneView.showOtpStatus(DataConstants.CODE_SENT);
                Log.v(TAG, "OTP sent to mobile");
                verificationID = s;
            }
        };
    }

    @Override
    public void verifyPhone(String number) {
        if (mDM.isValidNumber(number)) {
            mPhoneView.mobileNumberValidationSuccess();
            mDM.sendOtpToDevice(number, phoneAuthCallback);
        } else {
            mPhoneView.mobileNumberValidationFailed(DataConstants.INVALID_NUMBER);
        }
    }

    @Override
    public void verfiyOtp(String otp) {
        if (!otp.isEmpty()) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, otp);
            mDM.signInUsingCredentials(credential);
        } else {
            mPhoneView.otpVerificationFailed("Enter OTP first");
        }
    }

    @Override
    public void phoneVerified(boolean isVerified) {
        if (isVerified) {
            mPhoneView.otpVerificationSuccess();
        } else {
            mPhoneView.otpVerificationFailed(DataConstants.INVALID_OTP);
        }
    }
}
