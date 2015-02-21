package com.vishnu.colourmemory;

import com.vishnu.colourmemory.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Rank_Activity extends Activity implements OnClickListener {

	private Bundle bundleRank;
	private TextView textViewShowRank;
	private TextView textViewShowScore;
	private TextView textViewName;
	private Button buttonQuit;
	private Button buttonPlayAgain;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.final_rank_activity);
		bundleRank = getIntent().getExtras();
		textViewShowRank = (TextView) findViewById(R.id.textViewShowRank);
		textViewShowScore = (TextView) findViewById(R.id.textViewShowScore);
		textViewName = (TextView) findViewById(R.id.textViewName);
		buttonPlayAgain = (Button) findViewById(R.id.buttonPlayAgain);
		buttonQuit = (Button) findViewById(R.id.buttonQuit);
		String name = bundleRank.getString("name", "");
		buttonPlayAgain.setOnClickListener(this);
		buttonQuit.setOnClickListener(this);
		String scorepoint = bundleRank.getString("scorepoint", "");
		String rank = bundleRank.getString("rank", "");
		textViewShowRank.setText("Rank : "+rank);
		textViewName.setText("Thank you for playing " + name);
		textViewShowScore.setText("Score : "+scorepoint);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonQuit:
			finish();
			break;
		case R.id.buttonPlayAgain:
			Intent intentHomeActivity = new Intent(Rank_Activity.this,
					Home_Activity.class);
			startActivity(intentHomeActivity);
			finish();
			break;
		}

	}

}
