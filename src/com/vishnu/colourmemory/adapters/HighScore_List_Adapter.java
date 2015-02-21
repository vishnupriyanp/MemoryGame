package com.vishnu.colourmemory.adapters;

/**
 * This class is for adapting High score details to list view 
 * 
 * @author Vishnupriyan P
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vishnu.colourmemory.HighScore_Activity;
import com.vishnu.colourmemory.R;

public class HighScore_List_Adapter extends
		ArrayAdapter<HashMap<String, Object>> {

	ViewHolder viewHolder;
	ArrayList<Object> viewObj = new ArrayList<Object>();

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param resource
	 * @param Strings
	 */
	public HighScore_List_Adapter(Context context, int resource,
			ArrayList<HashMap<String, Object>> Strings) {
		super(context, resource, Strings);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			// inflate the custom layout
			try {
				convertView = HighScore_Activity.inflater.inflate(
						R.layout.highscore_listitem, null);
			} catch (Exception ex) {
			}
			viewHolder = new ViewHolder();
			// cache the views
			viewHolder.rank = (TextView) convertView
					.findViewById(R.id.textViewRank);
			viewHolder.name = (TextView) convertView
					.findViewById(R.id.textViewName);
			viewHolder.score = (TextView) convertView
					.findViewById(R.id.textViewScore);
			convertView.setTag(viewHolder);
		} else
			viewHolder = (ViewHolder) convertView.getTag();
		viewObj.add(viewHolder);
		viewHolder.rank.setText((String) HighScore_Activity.highScores.get(
				position).get("rank"));
		viewHolder.name.setText((String) HighScore_Activity.highScores.get(
				position).get("name"));
		viewHolder.score.setText((String) HighScore_Activity.highScores.get(
				position).get("score"));
		return convertView;
	}

	/*
	 * view item defining
	 */

	public class ViewHolder {
		TextView rank;
		TextView name;
		TextView score;
	}
}
