package com.vishnu.colourmemory.adapters;

/**
 * This class is for adapting card data to grid view 
 * 
 * @author Vishnupriyan P
 * 
 */
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.vishnu.colourmemory.R;
import com.vishnu.colourmemory.model.ResourceModel;

public class CustomGridViewAdapter extends ArrayAdapter<ResourceModel> {
	private Context context;
	private int layoutResourceId;
	private ArrayList<ResourceModel> data = new ArrayList<ResourceModel>();

	/**
	 * Constructor
	 * 
	 * @param context
	 * @param layoutResourceId
	 * @param data
	 */
	public CustomGridViewAdapter(Context context, int layoutResourceId,
			ArrayList<ResourceModel> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RecordHolder holder = null;
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new RecordHolder();
			holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}
		ResourceModel item = data.get(position);
		holder.imageItem.setImageBitmap(item.getImage());
		return row;
	}

	/* 
	 * view item defining  
	 */
	
	static class RecordHolder {
		ImageView imageItem;
	}
}
