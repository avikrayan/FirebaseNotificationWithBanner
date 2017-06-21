package rayan.avik.firebasefcm.FCM;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Avik on 13/06/2017.
 */
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseInsIDService";

    @Override
    public void onTokenRefresh() {
        //Get updated token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "New Token: " + refreshedToken);

        //You can save the token into third party server to do anything you want
    }
}
