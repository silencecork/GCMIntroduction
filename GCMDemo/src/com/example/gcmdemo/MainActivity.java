package com.example.gcmdemo;

import com.google.android.gcm.GCMRegistrar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.app.Activity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);

		String regId = GCMRegistrar.getRegistrationId(this);
		
		if (TextUtils.isEmpty(regId)) {
			GCMRegistrar.register(this, "");
		} else {
			Log.d("GCM", "registration id " + regId);
		}
	}

}
