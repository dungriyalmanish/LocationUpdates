package app.manish.locationupdates.fragments;


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
import app.manish.locationupdates.view.IPhoneView;

import static app.manish.locationupdates.MainActivity.mEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneVerify extends Fragment implements IPhoneView {


    EditText number, otp;
    Button b_otp;
    Context mContext;
    public PhoneVerify() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext=context;
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
                    mEventListener.verifyMobile((number.getText() != null) ? number.getText().toString() : "");
                    otp.setVisibility(View.VISIBLE);
                    b_otp.setText(R.string.otp_verify_button);

                }
            }
        });
        return v;
    }

    @Override
    public void validationSuccess() {
        Toast.makeText(mContext,"Phone Verification Success !! ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void validationFailed() {
        Toast.makeText(mContext,"Phone Verification Failed !! ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void incorrectMobileNumber(String reason) {
        Toast.makeText(mContext,"Incorrect Mobile Number !",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void incorrectOtp() {
        Toast.makeText(mContext,"Incorrect OTP !",Toast.LENGTH_SHORT).show();
    }
}
