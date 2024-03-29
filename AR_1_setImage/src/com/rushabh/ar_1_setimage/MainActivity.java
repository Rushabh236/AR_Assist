package com.rushabh.ar_1_setimage;

import java.io.IOException;


//import com.metaio.Example.BuildConfig;
import com.metaio.sdk.MetaioDebug;
import com.metaio.tools.io.AssetsManager;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
//import android.view.View;


public class MainActivity extends ActionBarActivity {
	
	
	AssetsExtracter mTask;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
    mTask = new AssetsExtracter();
    mTask.execute(0);
    
    Intent intent = new Intent (getApplicationContext(),setImage.class );
    startActivity(intent);
    } 


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    
    private class AssetsExtracter extends AsyncTask<Integer, Integer, Boolean>
	{	
		@Override
		protected Boolean doInBackground(Integer... params) 
		{
			try 
			{
				// Extract all assets and overwrite existing files if debug build
				AssetsManager.extractAllAssets(getApplicationContext(), BuildConfig.DEBUG);
			} 
			catch (IOException e) 
			{
				MetaioDebug.printStackTrace(Log.ERROR, e);
				return false;
			}

			return true;
		}
	}
}
