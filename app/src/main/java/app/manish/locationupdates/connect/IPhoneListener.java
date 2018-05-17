package app.manish.locationupdates.connect;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public interface IPhoneListener {
    void verifyPhone(String number);
    void verifyOtp(String otp);
    void phoneVerified(boolean isVerified);
}
