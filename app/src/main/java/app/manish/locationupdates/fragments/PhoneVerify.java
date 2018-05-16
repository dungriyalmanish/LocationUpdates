package app.manish.locationupdates.fragments;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.manish.locationupdates.R;
import app.manish.locationupdates.connect.IPhoneListener;
import app.manish.locationupdates.connect.PhoneConnector;
import app.manish.locationupdates.view.IPhoneView;
import app.manish.locationupdates.view.IView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneVerify extends Fragment implements IPhoneView {


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
                    phoneListener.verifyPhone((number.getText() != null) ? number.getText().toString() : "");
                } else {
                    phoneListener.verfiyOtp((otp.getText() != null) ? otp.getText().toString() : "");
                }
            }
        });
        return v;
    }

    @Override
    public void mobileNumberValidationSuccess() {
        //Toast.makeText(mContext, "OTP is sent to your device", Toast.LENGTH_SHORT).show();
        AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.fade_in_edittext);
        animatorSet.start();
        otp.setVisibility(View.VISIBLE);
        number.setEnabled(false);
        b_otp.setText(R.string.otp_verify_button);
    }

    @Override
    public void showOtpStatus(String otpStatus) {
        Toast.makeText(mContext, otpStatus, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mobileNumberValidationFailed(String reason) {
        Toast.makeText(mContext, "Mobile validation failed. Reason: " + reason, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void otpVerificationSuccess() {
        Toast.makeText(mContext, "OTP Verification Success !! ", Toast.LENGTH_SHORT).show();
        iView.updateFragment(new SignUp());
    }

    @Override
    public void otpVerificationFailed(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
