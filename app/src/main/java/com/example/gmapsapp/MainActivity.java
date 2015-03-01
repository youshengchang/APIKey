package com.example.gmapsapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;

public class MainActivity extends FragmentActivity {
	
	private static final int GPS_ERRORDIALOG_REQUEST = 9001;
	GoogleMap mMap;
    //adding the map
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(servicesOK()){
			Toast.makeText(this, "Ready to map!", Toast.LENGTH_LONG).show();
			Log.d("MainActivity","Ready to Map");
            setContentView(R.layout.activity_map);
		}else{
            setContentView(R.layout.activity_main);
        }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean servicesOK(){
		
		int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		
		if(isAvailable == ConnectionResult.SUCCESS){
			return true;
		}else if(GooglePlayServicesUtil.isUserRecoverableError(isAvailable)){
			
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, GPS_ERRORDIALOG_REQUEST);
			dialog.show();
			
		}else{
			Toast.makeText(this, "Can't connect to Google Play Services", Toast.LENGTH_LONG).show();
		}
		return false;
	}
}
