package app.manish.locationupdates.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.FirebaseDatabase;

import app.manish.locationupdates.constants.DataConstants;

/**
 * Created by manish.dungriyal on 16-05-2018.
 */

public class FirebaseHandler {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    //InformationReceiver informationReceiver;
    Intent intent;

    Context mContext;
    private boolean isSuccess = false;
    DataManager mDM;

    FirebaseHandler(Context context, DataManager dataManager) {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        mDM = dataManager;
        mContext = context;
        //informationReceiver = new InformationReceiver();
        //registerReceiver();

    }

    /*   private void registerReceiver() {
           IntentFilter intentFilter = new IntentFilter();
           intentFilter.addAction(DataConstants.UPDATE_SHAREDPREF);
           mContext.registerReceiver(informationReceiver, intentFilter);
       }
   */
    public UserInformation getUserInformation(String userKey) {
        return null;
    }


    public void signInUsingCredentials(PhoneAuthCredential phoneAuthCredential) {
        firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mDM.phoneVerified(true);
                    firebaseUser = task.getResult().getUser();
                } else {
                    mDM.phoneVerified(false);
                }
            }
        });
    }

    public void updateDetails(UserInformation userInformation) {
        firebaseDatabase.getReference(DataConstants.USERS).child(userInformation.getPhone()).setValue(userInformation);
        intent = new Intent();
        intent.setAction(DataConstants.UPDATE_SHAREDPREF);
        intent.putExtra(DataConstants.USER_INFO, userInformation);
        mContext.sendBroadcast(intent);

    }
}
