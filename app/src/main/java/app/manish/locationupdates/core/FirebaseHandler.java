package app.manish.locationupdates.core;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

/**
 * Created by manish.dungriyal on 16-05-2018.
 */

public class FirebaseHandler {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    Context mContext;
    private boolean isSuccess = false;
    DataManager mDM;

    FirebaseHandler(Context context, DataManager dataManager) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        mDM = dataManager;
        mContext = context;

    }

    public UserInformation getUserInformation(String userKey) {
        return null;
    }


    public void signInUsingCredentials(PhoneAuthCredential phoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mDM.phoneVerified(true);
                } else {
                    mDM.phoneVerified(false);
                }
            }
        });
    }
}
