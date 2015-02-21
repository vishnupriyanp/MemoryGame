package com.vishnu.colourmemory;

/**
 * This class is for Showing splash screen for few second 
 * 
 * @author Vishnupriyan P
 * 
 */
import com.vishnu.colourmemory.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash_screen_activity);
		// Thread delay for Showing the Splash screen for 3 second
		Thread splashThread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while (waited < 3000) {
						sleep(100);
						waited += 100;
					}
				} catch (InterruptedException e) {
					// do nothing
				} finally {
					Intent intentHomeActivity = new Intent(SplashScreen.this,
							Home_Activity.class);
					startActivity(intentHomeActivity);
					finish();
				}
			}
		};
		splashThread.start();
	}

}
