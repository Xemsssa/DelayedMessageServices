package com.xemss.delayedmassageservices;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import static android.widget.Toast.makeText;

/**
 * Created by xemss on 13.11.2017.
 */

public class DelayedMessageService extends IntentService {
    public final static String EXTRA_MESSAGE = "message";

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
        Log.v("DelayedMessageService", "The message " + text);

    }
}
