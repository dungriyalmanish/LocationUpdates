package app.manish.locationupdates.constants;

/**
 * Created by manish.dungriyal on 10-05-2018.
 */

public class DataConstants {
    public static final String LOGIN_SUCCESS = "login_ok";
    public static final String SHARED_PREF = "saved_data_share_pref";
    public static final String USER_ID = "user_key";
    public static final String USER_NAME = "user_name";
    public static final String USER_PHONE = "mobile_number";
    public static final String USER_INFO = "user_information";

    //validation
    public static final String INVALID_NUMBER = "invalid mobile number";
    public static final String INVALID_OTP = "Incorrect OTP";
    public static final String CODE_SENT = "OTP sent to mobile";


    //intent actions: broadcastreceiver
    public static final String INTERNET_CONNECTED = "internet_connected";

    //firebaseConstants
    public static final String USERS = "users";

    //broadcast receivers actions
    public static final String UPDATE_SHAREDPREF = "update_shared_pref";
    public static final String SHOW_PROGRESS_DIALOG = "show_progress_dialog";
    public static final String PROGRESS_DATA = "progress_data";
    public static final String HIDE_PROGRESS_DIALOG = "hide_dialog";
}
