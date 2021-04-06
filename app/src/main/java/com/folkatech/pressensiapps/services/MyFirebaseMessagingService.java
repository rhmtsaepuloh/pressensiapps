package com.folkatech.pressensiapps.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.folkatech.pressensi.model.Informasi;
import com.folkatech.pressensiapps.R;
import com.folkatech.pressensiapps.common.constant.AppConstants;
import com.folkatech.pressensiapps.common.utils.NotificationUtils;
import com.folkatech.pressensiapps.ui.activity.home.HomeActivity;
import com.folkatech.pressensiapps.ui.activity.notification.NotificationActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rahmat Saefulloh on 10/01/21.
 * Folka Indonesia Teknologi
 * rhmtsaepuloh@gmail.com
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    private NotificationUtils notificationUtils;

    private void scheduleJob() {
        // [START dispatch_job]
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = dispatcher.newJobBuilder()
                .setService(MyJobService.class)
                .setTag("my-job-tag")
                .build();
        dispatcher.schedule(myJob);
        // [END dispatch_job]
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage == null)
            return;
        Log.d(TAG, "onMessageReceived: " + remoteMessage.getData().get("message"));
        Log.d(TAG, "onMessageReceived: " + remoteMessage.getData().size());
        Log.d(TAG, "onMessageReceived: " + remoteMessage.getData());


        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "DataHistoryPengajuan Payload: " + remoteMessage.getData().toString());
            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
                //sendNotification(remoteMessage.getData().get("body"), remoteMessage.getData().get("title"));
                try {
                    JSONObject json = new JSONObject();
                    json.put("image", remoteMessage.getData().get("image"));
                    json.put("timestamp", remoteMessage.getData().get("timestamp"));
                    json.put("is_background", remoteMessage.getData().get("is_background"));
                    //json.put("payload",remoteMessage.getData().get("payload"));
                    json.put("title", remoteMessage.getData().get("title"));
                    json.put("message", remoteMessage.getData().get("message"));
                    json.put("tipe", remoteMessage.getData().get("tipe"));
                    json.put("kategori", remoteMessage.getData().get("kategori"));

                    handleDataMessage(json);
                } catch (Exception e) {
                    Log.e(TAG, "Exception satu: " + e.getMessage());
                }
            } else {
                handleNow();

            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getData().toString());
//            try {
//                JSONObject json = new JSONObject(remoteMessage.getData().toString());
//                JSONObject data = null;
//                data = json.getJSONObject("data");
            String imageUrl = remoteMessage.getData().get("image");
            String tgl = remoteMessage.getData().get("timestamp");
            String tipe = remoteMessage.getData().get("tipe");
            String kategori = remoteMessage.getData().get("kategori");

            sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle(), imageUrl, tgl, tipe, kategori);
            handleNotification(remoteMessage.getNotification().getBody());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

        }
    }

    private void handleNow() {
        System.out.println("Short lived task is done.");
    }

    private void handleNotification(String message) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent(AppConstants.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();
        } else {
            // If the app is in background, firebase itself handles the notification
        }
    }

    private void handleDataMessage(JSONObject json) {
        Log.e(TAG, "push json: " + json.toString());

        try {
            JSONObject data = json;

            String title = data.getString("title");
            String message = data.getString("message");
            boolean isBackground = data.getBoolean("is_background");
            String imageUrl = data.getString("image");
            String timestamp = data.getString("timestamp");
            String tipe = data.getString("tipe");
            String kategori = data.getString("kategori");

            //JSONObject payload = data.getJSONObject("payload");

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + message);
            Log.e(TAG, "isBackground: " + isBackground);
            //Log.e(TAG, "payload: " + payload.toString());
            Log.e(TAG, "imageUrl: " + imageUrl);
            Log.e(TAG, "timestamp: " + timestamp);
            sendNotification(message, title, imageUrl, timestamp, tipe, kategori);
            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                // app is in foreground, broadcast the push message
                Intent pushNotification = new Intent(AppConstants.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", message);
                pushNotification.putExtra("title", title);

                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                // play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();

            } else {
                // app is in background, show the notification in notification tray
                Intent resultIntent = new Intent(getApplicationContext(), HomeActivity.class);
                resultIntent.putExtra("message", message);
                resultIntent.putExtra("title", title);

                // check for image attachment
                if (TextUtils.isEmpty(imageUrl)) {
                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
                } else {
                    // image is present, show notification with image
                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }

    private void sendNotification(String messageBody, String title, String imageUrl, String tgl, String tipe, String kategori) {
        Intent intent = null;
        if (kategori != null)
            if (kategori.toLowerCase().equals("pengumuman")) {
                intent = new Intent(this, NotificationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Informasi obj = new Informasi();
                obj.setDeskripsiPengumuman(messageBody);
                obj.setJudulPengumuman(title);
                obj.setUrlFilePengumuman(imageUrl);
                obj.setTanggal(tgl);
                intent.putExtra(Informasi.class.getName(), String.valueOf(obj));
            } else {
                intent = new Intent(this, NotificationActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("isNotif", true);
                intent.putExtra("isJenisCuti", tipe);

            }
        if (intent != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            String channelId = "Default";
            final Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + this.getPackageName() + "/raw/notification");
            final int icon = R.drawable.logo;

            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.setBigContentTitle(title);
            bigPictureStyle.setSummaryText(Html.fromHtml(messageBody).toString());

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setSound(alarmSound)
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), icon))
                    .setAutoCancel(true).setContentIntent(pendingIntent);
            if (!TextUtils.isEmpty(imageUrl)) {
                if (imageUrl != null && imageUrl.length() > 4 && Patterns.WEB_URL.matcher(imageUrl).matches()) {
                    Bitmap bitmap = NotificationUtils.getBitmapFromURL(imageUrl);
                    bigPictureStyle.bigPicture(bitmap);
                    builder.setStyle(bigPictureStyle);
                }
            }
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
                manager.createNotificationChannel(channel);
            }
            manager.notify(0, builder.build());
        }
    }
}