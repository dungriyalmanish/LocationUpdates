package app.manish.locationupdates.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import app.manish.locationupdates.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneVerify extends Fragment {


    EditText number, otp;
    Button b_otp;
    String mob_number = "";

    public PhoneVerify() {

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
                    if (isValidNumber()) {
                        otp.setVisibility(View.VISIBLE);
                        b_otp.setText(R.string.otp_verify_button);
                        Toast.makeText(getContext(), "OTP sent to mobile", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Incorrect Mobile Number !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if(isValidOTP())
                    Toast.makeText(getContext(), "OTP verified", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private boolean isValidOTP() {
        return true;
    }

    private boolean isValidNumber() {
        mob_number = (number.getText() != null) ? number.getText().toString() : "";
        if (!mob_number.isEmpty() && mob_number.length() == 10 && mob_number.matches("^[0-9]*$")) {
            return true;
        }
        return false;
    }


}
