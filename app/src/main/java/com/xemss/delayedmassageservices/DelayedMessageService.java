package com.xemss.delayedmassageservices;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static android.widget.Toast.makeText;

/**
 * Created by xemss on 13.11.2017.
 */

public class DelayedMessageService extends IntentService {
    public final static String EXTRA_MESSAGE = "message";
    // TODO: 14.11.2017 remake to use notification 
//    private Handler handler;
    // TODO: 14.11.2017 notification
    private static final int NOTIFICATION_ID = 5453;


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public DelayedMessageService(String name) {
        super(name);
    }

    // TODO: 13.11.2017 create constructor
    public DelayedMessageService() {
        super("DelayedMessageService");
    }

//    @Override
//    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        // TODO: 14.11.2017 remake to use notification
//        handler = new Handler() {
//            @Override
//            public void publish(LogRecord record) {
//
//            }
//
//            @Override
//            public void flush() {
//
//            }
//
//            @Override
//            public void close() throws SecurityException {
//
//            }
//        }
//        return super.onStartCommand(intent, flags, startId);
//    }

    // TODO: 13.11.2017 implement intent handler
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // TODO: 13.11.2017 synchronize 
        synchronized (this) {
            try {
                // TODO: 13.11.2017 wait 
                wait(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error start timer", Toast.LENGTH_SHORT).show();
            }

//            String text = "default";
            String text = intent.getStringExtra(EXTRA_MESSAGE);
            showText(text);
        }
    }

    private void showText(final String text) {
        // TODO: 14.11.2017 remake to use notification
//        handler.post(new Runnable() {
//           @Override
//            public void run() {
//                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
//           }
//        });
        // TODO: 13.11.2017 testing to log 
//        Log.v("DelayedMessageService", "The message " + text);

        // TODO: 14.11.2017 implementation of notification
        // TODO: 14.11.2017  create new intent
        Intent intent = new Intent(this, MainActivity.class);

        // TODO: 14.11.2017 create object task builder
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);

        // TODO: 14.11.2017 add for back tracking activity
        taskStackBuilder.addParentStack(MainActivity.class);
        taskStackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // TODO: 14.11.2017 create notification object
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(text)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .build();

        // TODO: 14.11.2017  manager to operate notification
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // TODO: 13.11.2017 show notification
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
