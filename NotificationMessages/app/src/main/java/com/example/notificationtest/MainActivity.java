package com.example.notificationtest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

import static com.example.notificationtest.MyBroadcastReceiver.ACTION_SNOOZE;
import static com.example.notificationtest.MyBroadcastReceiver.EXTRA_NOTIFICATION_ID;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final String TAG = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        Log.e(TAG, "onCreate");

        LongOperation lo = new LongOperation(this);
        lo.execute("Test 1", "Test 2", "Test 3");
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private class LongOperation extends AsyncTask<String, String, String> {

        private static final String TAG = "longoperation";
        private Context ctx;
        private AtomicInteger notificationId = new AtomicInteger(0);

        LongOperation(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected String doInBackground(String... params) {
            for (String s : params) {
                Log.e(TAG, s);

                publishProgress(s);

                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.interrupted();
                    }
                }
            }
            return "Executed";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            for (String title : values) {
                sendNotification(title, notificationId.incrementAndGet());
            }
        }

        void sendNotification(String title, int notificationId) {

            // Create an explicit intent for an Activity in your app
        /* Intent intent = new Intent(ctx, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 0, intent, 0); */

            Intent snoozeIntent = new Intent(ctx, MyBroadcastReceiver.class);
            snoozeIntent.setAction(ACTION_SNOOZE);
            snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, notificationId);

            Log.e(TAG, snoozeIntent.getExtras().toString());

            Log.e(TAG, "snoozeIntent id: " + snoozeIntent.getIntExtra(EXTRA_NOTIFICATION_ID, -1));

            PendingIntent snoozePendingIntent =
                    PendingIntent.getBroadcast(ctx, notificationId, snoozeIntent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(String.format("%s (id %d)", title, notificationId))
                    .setContentText("Hello Dharwin !")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(false)
                    // Add the action button
                    .addAction(R.drawable.ic_launcher_foreground, ctx.getString(R.string.snooze),
                            snoozePendingIntent);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ctx);

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(notificationId, builder.build());
        }
    }
}
