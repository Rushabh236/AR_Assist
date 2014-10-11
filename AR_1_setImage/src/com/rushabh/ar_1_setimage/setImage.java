package com.rushabh.ar_1_setimage;

import android.util.Log;
//import android.view.View;

import com.metaio.sdk.ARViewActivity;
import com.metaio.sdk.MetaioDebug;
import com.metaio.sdk.jni.IGeometry;
import com.metaio.sdk.jni.IMetaioSDKCallback;
import com.metaio.tools.io.AssetsManager;

public class setImage extends ARViewActivity {

	private String mTrackingFile;
	
	private IGeometry mImagePlane;
	
	
	@Override
	protected int getGUILayout() {
		
		// Attaching layout to the activity
		return R.layout.set_image;
	}

	@Override
	protected IMetaioSDKCallback getMetaioSDKCallbackHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void loadContents() {
		
		try
		{
			//track file
			mTrackingFile = AssetsManager.getAssetPath(getApplicationContext(),"assets1/TrackingData_MarkerlessFast.xml");
			
			//assign track configuration
			boolean result = metaioSDK.setTrackingConfiguration(mTrackingFile); 
			MetaioDebug.log("Tracking data loaded: " + result);	
			
			//get file from assets
			String imagePath = AssetsManager.getAssetPath(getApplicationContext(), "assets1/frame.png");
			
			if (imagePath != null) 
			{
				// Loading 3D geometry
				mImagePlane = metaioSDK.createGeometryFromImage(imagePath);
				if (mImagePlane != null) 
				{
					// Set geometry properties
					mImagePlane.setScale(3.0f);
				}
				else
					MetaioDebug.log(Log.ERROR, "Error loading geometry: "+imagePath);
			}
		}
		catch (Exception e)
		{
			MetaioDebug.printStackTrace(Log.ERROR, e);
		}
		

	}

	@Override
	protected void onGeometryTouched(IGeometry geometry) {
		// TODO Auto-generated method stub

	}
	
	
	

}
