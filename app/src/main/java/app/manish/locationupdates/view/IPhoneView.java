package app.manish.locationupdates.view;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public interface IPhoneView {

    void otpVerificationSuccess();

    void otpVerificationFailed(String message);

    void mobileNumberValidationFailed(String reason);

    void mobileNumberValidationSuccess();

    void showOtpStatus(String otpStatus);

    void showDialog(String message);

    void hideDialog();
}
