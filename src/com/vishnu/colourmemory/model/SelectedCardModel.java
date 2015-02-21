package com.vishnu.colourmemory.model;

import android.view.View;

/**
 * This class is an Object for last selected card id remembering 
 * 
 * @author Vishnupriyan P
 * 
 */

public class SelectedCardModel {
	int selected;
	View cardView;

	public int getSelected() {
		return selected;
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}
	public View getSelectedCardView() {
		return cardView;
	}
	public void setSelectedCardView(View cardView) {
		this.cardView = cardView;
	}
}
