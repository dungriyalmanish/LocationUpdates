package app.manish.locationupdates.core;

import java.util.UUID;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public  class FrontEndUtils {
    public static boolean isValidMobileNumber(String number) {
        if (!number.isEmpty() && number.length() == 10 && number.matches("^[0-9]*$")) {
            return true;
        }
        return false;
    }

    public static String getId(){
        return UUID.randomUUID().toString();
    }
}
