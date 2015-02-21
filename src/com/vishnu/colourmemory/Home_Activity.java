package com.vishnu.colourmemory;

/**
 * This class is for the core for card play. 
 * 
 * @author Vishnupriyan P
 * 
 */
import java.util.ArrayList;
import java.util.Collections;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnu.colourmemory.R;
import com.vishnu.colourmemory.adapters.CustomGridViewAdapter;
import com.vishnu.colourmemory.model.ResourceModel;
import com.vishnu.colourmemory.model.SelectedCardModel;
import com.vishnu.colourmemory.sqlitehelper.SqliteDatabaseHelper;

public class Home_Activity extends Activity implements OnClickListener,
		OnItemClickListener {
	private SqliteDatabaseHelper databs;
	private ArrayList<String> list;
	private GridView gridView;
	private ArrayList<ResourceModel> gridArray = new ArrayList<ResourceModel>();
	private CustomGridViewAdapter customGridAdapter;
	private Bitmap cardIcon;
	private ObjectAnimator anim;
	private String[] Cards = new String[] { "colour1", "colour1", "colour2",
			"colour2", "colour3", "colour3", "colour4", "colour4", "colour5",
			"colour5", "colour6", "colour6", "colour7", "colour7", "colour8",
			"colour8" };
	private int scorepoint = 0;
	private TextView textLiveScoreS;
	private Button imagHighscore;
	private SelectedCardModel selected;
	private PopupWindow popupWindow;
	private EditText edittextName;
	private TextView textViewHighScore;
	private int gameOver = 0;
	private int lastHighScore;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home_activity);
		anim = (ObjectAnimator) AnimatorInflater.loadAnimator(this,
				R.animator.flipping);
		gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setOnItemClickListener(null);
		textLiveScoreS = (TextView) findViewById(R.id.textLiveScore);
		textLiveScoreS.setText(scorepoint + "");
		imagHighscore = (Button) findViewById(R.id.imagHighscore);
		imagHighscore.setOnClickListener(this);
		initSetSelected();
		initGameOverPopup();
		initDataBase();
		settingAdapter();
		// Thread delay before hiding cards (3 seconds)
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				hideCards();
			}
		}, 3000);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	/**
	 * 
	 * Hiding card to after showing
	 * 
	 */
	public void hideCards() {
		gridView.setOnItemClickListener(this);
		gridArray.clear();
		for (int i = 0; i < 16; i++) {
			int drawableResourceId = this.getResources().getIdentifier(
					"card_bg", "drawable", this.getPackageName());
			cardIcon = BitmapFactory.decodeResource(this.getResources(),
					drawableResourceId);
			gridArray.add(new ResourceModel(cardIcon, drawableResourceId));
		}
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid,
				gridArray);
		gridView.setAdapter(null);
		gridView.setAdapter(customGridAdapter);
	}

	/**
	 * 
	 * Initialising selected card to zero
	 * 
	 */
	public void initSetSelected() {
		selected = new SelectedCardModel();
		selected.setSelected(0);
	}

	/**
	 * Initialising game over popup window
	 * 
	 */
	@SuppressLint("InlinedApi")
	public void initGameOverPopup() {
		LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		View popupView = layoutInflater.inflate(R.layout.popup_complete_game,
				null);
		popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		Button buttonNext = (Button) popupView
				.findViewById(R.id.buttonContinue);
		edittextName = (EditText) popupView.findViewById(R.id.edittextName);
		textViewHighScore = (TextView) popupView
				.findViewById(R.id.textViewHighScore);
		textViewHighScore.setVisibility(View.GONE);
		popupWindow.setFocusable(true);
		buttonNext.setOnClickListener(this);
	}

	/**
	 * 
	 * Initialising data base class Get last high score
	 * 
	 */
	public void initDataBase() {
		databs = new SqliteDatabaseHelper(this);
		databs.setContentDB(databs.getWritableDatabase());
		lastHighScore = databs.getHighScore();
		System.out.println("lasttt scoreedvgv   " + lastHighScore);
	}

	/**
	 * This method is for add card to list view, Shuffle the list view. set
	 * adapter to the Gridview
	 * 
	 */
	public void settingAdapter() {
		list = new ArrayList<String>();
		// Adding card to list view
		for (int i = 0; i < Cards.length; ++i) {
			list.add(Cards[i]);
		}
		// shuffle the list view
		Collections.shuffle(list);
		for (int i = 0; i < list.size(); i++) {
			int drawableResourceId = this.getResources().getIdentifier(
					list.get(i), "drawable", this.getPackageName());
			cardIcon = BitmapFactory.decodeResource(this.getResources(),
					drawableResourceId);
			gridArray.add(new ResourceModel(cardIcon, drawableResourceId));
		}
		customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid,
				gridArray);
		// Setting adapter to grid
		gridView.setAdapter(customGridAdapter);
	}

	/**
	 * This method is for animating imageview
	 * 
	 * @param imageView
	 */
	@SuppressLint("NewApi")
	public void animateImageView(ImageView imageView) {
		anim.setTarget(imageView);
		anim.setDuration(250);
		anim.start();
	}

	/**
	 * 
	 * Generating rank from score
	 * 
	 * @param scoreForRank
	 * @return
	 */
	public String getRank(int scoreForRank) {
		String rnk = "0";
		if (scoreForRank > 14)
			rnk = "1";
		if (scoreForRank < 14 && scoreForRank >= 8)
			rnk = "2";
		if (scoreForRank < 8)
			rnk = "3";
		return rnk;
	}

	/**
	 * 
	 * onClick listener
	 * 
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imagHighscore:
			// Calling high score Activity
			Intent intentHighScore = new Intent(Home_Activity.this,
					HighScore_Activity.class);
			startActivity(intentHighScore);
			finish();
			break;
		case R.id.buttonContinue:
			// trimming user input and validating (Name)
			String name = edittextName.getText().toString().trim();
			if (name.matches("")) {
				Toast.makeText(this, "You did not enter a name",
						Toast.LENGTH_LONG).show();
			} else {
				String rank;
				if (scorepoint <= 0) {
					// setting rank 0 for -ve points
					rank = "0";
				} else {
					// getting rank for score
					rank = getRank(scorepoint);
					System.out.println("rank    " + rank);
				}
				// Updating data base after entering name
				databs = new SqliteDatabaseHelper(this);
				databs.setContentDB(databs.getWritableDatabase());
				databs.addScores(name, scorepoint + "", rank + "");
				// Dismissing gameover popup
				popupWindow.dismiss();
				Intent intentFinalRank = new Intent(Home_Activity.this,
						Rank_Activity.class);
				intentFinalRank.putExtra("name", name);
				intentFinalRank.putExtra("scorepoint", scorepoint + "");
				intentFinalRank.putExtra("rank", rank + "");
				startActivity(intentFinalRank);
				finish();

			}
			break;
		}
	}

	/**
	 * Grid view item click listener
	 * 
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, final View view,
			int position, long id) {
		int drawableResourceId = 0;
		drawableResourceId = this.getResources().getIdentifier(
				list.get(position), "drawable", this.getPackageName());
		if (selected.getSelected() == 0) {
			// First case of the each selection
			ImageView im = (ImageView) view.findViewById(R.id.item_image);
			// Animating imageview
			animateImageView(im);
			cardIcon = BitmapFactory.decodeResource(this.getResources(),
					drawableResourceId);
			im.setImageBitmap(cardIcon);

			selected.setSelected(drawableResourceId);
			selected.setSelectedCardView(view);
		} else {
			if (drawableResourceId == selected.getSelected()) {
				// User selected the matching card
				final ImageView im = (ImageView) view
						.findViewById(R.id.item_image);
				final ImageView preSelectedCard = (ImageView) selected
						.getSelectedCardView().findViewById(R.id.item_image);
				animateImageView(im);
				cardIcon = BitmapFactory.decodeResource(this.getResources(),
						drawableResourceId);
				im.setImageBitmap(cardIcon);
				selected.setSelected(0);
				selected.getSelectedCardView();
				// adding score +2
				scorepoint = scorepoint + 2;
				// Setting live score to textview
				textLiveScoreS.setText(scorepoint + "");
				gameOver++;

				// Showing popup at end of the game
				Handler handlerInvisibleCard = new Handler();
				handlerInvisibleCard.postDelayed(new Runnable() {
					@Override
					public void run() {
						im.setVisibility(View.INVISIBLE);
						preSelectedCard.setVisibility(View.INVISIBLE);
					}
				}, 1000);

				// Game over detector
				if (gameOver == 8) {
					// Showing popup at end of the game
					Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
						@Override
						public void run() {

							if (lastHighScore < scorepoint)
								textViewHighScore.setVisibility(View.VISIBLE);
							popupWindow.showAtLocation(view, Gravity.CENTER, 0,
									0);

						}
					}, 1000);
				}
			} else {
				// User select wrong card
				// Removing score -1
				scorepoint = scorepoint - 1;
				textLiveScoreS.setText(scorepoint + "");
			}
		}

	}

}
