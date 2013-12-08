package com.example.gcmdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	
	private static final String TAG = "GCMIntentServer";
	
	// Add default constructor is very important!!
	public GCMIntentService() {
		super("MyTestGCMService");
	}

	@Override
	protected void onError(Context context, String errId) {
		Log.i(TAG, "onError, error " + errId);
	}

	@Override
	protected void onMessage(Context context, Intent gcmIntent) {
		Log.i(TAG, "onMessage");
		
		String message = gcmIntent.getExtras().getString("message");
		
		Log.i(TAG, "receive message " + message);
		
		Intent intent = new Intent();
        // Pass data to the new activity
        intent.putExtra("message", message);
        // Starts the activity on notification click
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        // Create the notification with a notification builder
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Android GCM Tutorial")
                .setContentText(message).setContentIntent(pIntent)
                .getNotification();
        // Remove the notification on click
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
 
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(R.string.app_name, notification);
	}

	@Override
	protected void onRegistered(Context context, String regId) {
		Log.i(TAG, "onRegistered, regId " + regId);
		
	}

	@Override
	protected void onUnregistered(Context context, String regId) {
		Log.i(TAG, "onUnregistered, regId " + regId);
		
	}

}
