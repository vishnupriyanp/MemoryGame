package com.vishnu.colourmemory;

/**
 * This class is for Showing the high scores of players  
 * 
 * @author Vishnupriyan P
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.ListView;
import android.widget.Toast;

import com.vishnu.colourmemory.R;
import com.vishnu.colourmemory.adapters.HighScore_List_Adapter;
import com.vishnu.colourmemory.model.ScoreModel;
import com.vishnu.colourmemory.sqlitehelper.SqliteDatabaseHelper;

public class HighScore_Activity extends Activity {
	public static ArrayList<HashMap<String, Object>> highScores;

	public static LayoutInflater inflater;
	static SqliteDatabaseHelper db;
	static ListView listHighScore;
	private static String Rank[];
	private static String Name[];
	private static String Score[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.highscore_activity);
		listHighScore = (ListView) findViewById(R.id.listViewHighScore);
		inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		db = new SqliteDatabaseHelper(getApplicationContext());
		db.getWritableDatabase();
		adapterlistviewStrobvideo(this);
	}
	 @Override
     public boolean onKeyDown(int keyCode, KeyEvent event) {
         if ((keyCode == KeyEvent.KEYCODE_BACK)) {
             onBackPressed();
         	Intent intentHomeActivity = new Intent(HighScore_Activity.this,
					Home_Activity.class);
			startActivity(intentHomeActivity);
			finish();
         }
		return false;
 }

/**
 * 
 * Method is for Adapter list view 
 * Fetching Highscore from database  
 * 
 * @param context
 */
	public static void adapterlistviewStrobvideo(final Context context) {
		// getting high score 
		ArrayList<ScoreModel> product_list = db.getHighScores();
		Rank = new String[product_list.size()];
		Name = new String[product_list.size()];
		Score = new String[product_list.size()];
		for (int i = 0; i < product_list.size(); i++) {
			Rank[i] = product_list.get(i).getRank();
			Name[i] = product_list.get(i).getName();
			Score[i] = product_list.get(i).getScore();
		}
		highScores = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> temp;
		int noOfScores = Rank.length;
		for (int i = 0; i < noOfScores; i++) {
			temp = new HashMap<String, Object>();
			temp.put("name", Name[i]);
			temp.put("score", Score[i]);
			temp.put("rank", Rank[i]);
			highScores.add(temp);
		}
		if (noOfScores == 0) {
			Toast.makeText(context, "Score list empty.",
					Toast.LENGTH_SHORT).show();
		} else {
			// Adapting high score to list view 
			listHighScore.setAdapter(new HighScore_List_Adapter(context,
					R.layout.highscore_activity, highScores));
		}
	}

}
