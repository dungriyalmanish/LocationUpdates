package app.manish.locationupdates.core;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public class BaseUtils implements IMainUtils {

    @Override
    public boolean isValidMobileNumber(String number) {
        if (!number.isEmpty() && number.length() == 10 && number.matches("^[0-9]*$")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidOTP(String number) {
        return false;
    }
}
