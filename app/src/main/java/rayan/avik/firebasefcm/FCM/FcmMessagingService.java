package rayan.avik.firebasefcm.FCM;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import rayan.avik.firebasefcm.MainActivity;
import rayan.avik.firebasefcm.MySingleton;
import rayan.avik.firebasefcm.R;

/**
 * Created by Avik on 13-06-2017.
 */

public class FcmMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {

            String title, message, img_url;

            title = remoteMessage.getData().get("title");
            message = remoteMessage.getData().get("message");
            img_url = remoteMessage.getData().get("img_url");

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0/*Request code*/, intent, PendingIntent.FLAG_ONE_SHOT);

            Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            final NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(this);
            notifiBuilder.setSmallIcon(R.mipmap.ic_firebaselogo);
            notifiBuilder.setContentTitle(title);
            notifiBuilder.setContentText(message);
            notifiBuilder.setAutoCancel(true);
            notifiBuilder.setSound(notificationSound);
            notifiBuilder.setContentIntent(pendingIntent);

            ImageRequest imageRequest = new ImageRequest(img_url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {

                    notifiBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(response));
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(0,notifiBuilder.build());

                }
            }, 0, 0, null, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            MySingleton.getmInstance(this).addToRequestQue(imageRequest);
        }
    }
}
