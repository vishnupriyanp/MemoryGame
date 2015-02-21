package com.vishnu.colourmemory.model;

import android.graphics.Bitmap;

/**
 * This class is an Object for gird data handling 
 * 
 * @author Vishnupriyan P
 * 
 */

public class ResourceModel {
	Bitmap image;
	int resourceID;

	public ResourceModel(Bitmap image, int resourceID) {
		super();
		this.image = image;
		this.resourceID = resourceID;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public int getResourceID() {
		return resourceID;
	}

	public void setResourceID(int resourceID) {
		this.resourceID = resourceID;
	}

}
