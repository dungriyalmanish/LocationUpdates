package app.manish.locationupdates.connect;

import app.manish.locationupdates.core.DataManager;
import app.manish.locationupdates.view.IPhoneView;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public class PhoneConnector implements IPhoneListener {
    DataManager mDM;
    IPhoneView mPhoneView;

    public PhoneConnector(IPhoneView phoneView) {
        mPhoneView = phoneView;
    }

    @Override
    public void verifyPhone(String number) {
        mPhoneView.mobileNumberValidationSuccess();
    }

    @Override
    public void verfiyOtp(String otp) {
        mPhoneView.otpVerificationSuccess();
    }
}
