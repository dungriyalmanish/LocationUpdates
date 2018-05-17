package app.manish.locationupdates.fragments;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.manish.locationupdates.R;
import app.manish.locationupdates.connect.IPhoneListener;
import app.manish.locationupdates.connect.PhoneConnector;
import app.manish.locationupdates.constants.DataConstants;
import app.manish.locationupdates.view.IPhoneView;
import app.manish.locationupdates.view.IView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneVerify extends Fragment implements IPhoneView {

    private static final String TAG = "PhoneVerify";

    EditText number, otp;
    Button b_otp;
    Context mContext;
    IPhoneListener phoneListener;
    IView iView;

    public PhoneVerify() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        iView = (IView) mContext;
        phoneListener = new PhoneConnector(this, mContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phone_verify, container, false);
        number = v.findViewById(R.id.mobile_number);
        otp = v.findViewById(R.id.otp);
        b_otp = v.findViewById(R.id.otp_verify);
        b_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (b_otp.getText().toString().equalsIgnoreCase(getString(R.string.otp_get_button))) {
                    ///////////////////////////////////////////////////////////////
                    //testing skipping below line
                    //phoneListener.verifyPhone((number.getText() != null) ? number.getText().toString() : "");
                    showOtpStatus(DataConstants.CODE_SENT); //need to remove later
                    /////////////////////////////////////////////////////////////////
                } else {
                    ///////////////////////////////////////////////////////////////
                    otpVerificationSuccess(); //need to remove after
                    //phoneListener.verifyOtp((otp.getText() != null) ? otp.getText().toString() : "");
                    ///////////////////////////////////////////////////////////////
                }
            }
        });
        return v;
    }

    @Override
    public void mobileNumberValidationSuccess() {
        //Toast.makeText(mContext, "OTP is sent to your device", Toast.LENGTH_SHORT).show();
        mContext.sendOrderedBroadcast(new Intent(DataConstants.HIDE_PROGRESS_DIALOG), null);
    }

    @Override
    public void showOtpStatus(String otpStatus) {
        Toast.makeText(mContext, otpStatus, Toast.LENGTH_SHORT).show();
        if (otpStatus.equals(DataConstants.CODE_SENT)) {
            number.setEnabled(false);
            AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.fade_in_edittext);
            animatorSet.start();
            otp.setVisibility(View.VISIBLE);
            b_otp.setText(R.string.otp_verify_button);
        }

    }

    @Override
    public void showDialog(String message) {
        mContext.sendOrderedBroadcast(new Intent(DataConstants.SHOW_PROGRESS_DIALOG)
                .putExtra(DataConstants.PROGRESS_DATA, message), null);
    }

    @Override
    public void hideDialog() {
        mContext.sendOrderedBroadcast(new Intent(DataConstants.HIDE_PROGRESS_DIALOG), null);
    }

    @Override
    public void mobileNumberValidationFailed(String reason) {
        Toast.makeText(mContext, "Mobile validation failed. Reason: " + reason, Toast.LENGTH_SHORT).show();
        hideDialog();
    }

    @Override
    public void otpVerificationSuccess() {
        Toast.makeText(mContext, "OTP Verification Success !! ", Toast.LENGTH_SHORT).show();
        SignUp signUpFragment = new SignUp();
        Bundle b = new Bundle();
        b.putString(DataConstants.USER_INFO, "+91" + number.getText().toString());
        signUpFragment.setArguments(b);
        iView.updateFragment(signUpFragment);
    }

    @Override
    public void otpVerificationFailed(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
        mContext.sendOrderedBroadcast(new Intent(DataConstants.HIDE_PROGRESS_DIALOG), null);
        Log.v(TAG, "hide progress dialog as no otp entered");
    }
}
