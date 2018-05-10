package app.manish.locationupdates.view;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public interface IPhoneView {

    void validationSuccess();
    void validationFailed();
    void incorrectMobileNumber(String reason);
    void incorrectOtp();
}
