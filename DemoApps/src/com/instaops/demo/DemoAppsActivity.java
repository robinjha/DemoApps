package com.instaops.demo;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DemoAppsActivity extends Activity 
{
	
	private static final String PREFERENCES = "PREFERENCES";
	private static final String URL = "url";
	private String lastUrl;
	private EditText urlText;
	private TextView textView;
	String[] websites = new String[5];
	
	public void assignment(String[] websites){
		websites[0] = new String("http://www.google.com");
		websites[1] = new String("http://www.facebook.com");
		websites[2] = new String("http://www.cnn.com");
		websites[3] = new String("http://www.twitter.com");
		websites[4] = new String("http://www.linkedin.com");
	}
	
	
   // Called when the activity is first created. 


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		urlText = (EditText) findViewById(R.id.address);
		textView = (TextView) findViewById(R.id.pagetext);

		loadPreferences();
		urlText.setText(lastUrl);
		System.out.println("Website : "+websites[0]);
	}

	
   // Demonstrates loading of preferences The last value in the URL string will * be loaded                          

	private void loadPreferences() {
		SharedPreferences preferences = getSharedPreferences(PREFERENCES,
				Activity.MODE_PRIVATE);
		// Set this to the Google Homepage
		lastUrl = preferences.getString(URL, "http://209.85.229.147");
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences preferences = getSharedPreferences(PREFERENCES,
				Activity.MODE_PRIVATE);
		Editor preferenceEditor = preferences.edit();
		preferenceEditor.putString(URL, urlText.getText().toString());
		// You have to commit otherwise the changes will not be remembered
		preferenceEditor.commit();
	}
	/*
	public void defaultSites(View view, String[] websites) throws ClientProtocolException, IOException{
			for(int i = 0; i < websites.length; i++){
				textView.setText("");
				HttpClient client = new DefaultHttpClient();
				Calendar c1 = Calendar.getInstance(); 
				long time1 = c1.getTimeInMillis();
				HttpGet request = new HttpGet(websites[i]);
				HttpResponse response = client.execute(request);
				Calendar c2 = Calendar.getInstance(); 
				long time2 = c2.getTimeInMillis();
				int length = (int)response.getEntity().getContentLength();//doesn't always return the correct length
				textView.append(urlText.getText().toString()+"\tResponse Time : "+ (time2 - time1)+ "Content Length: "+length);
			}
	}
	*/
	public void myClickHandler(View view) {
		switch (view.getId()) {
		case R.id.ReadWebPage:
			try {
				
				textView.setText("");
				HttpClient client1 = new DefaultHttpClient();
				Calendar c1 = Calendar.getInstance(); 
				long time1 = c1.getTimeInMillis();
				HttpGet request1 = new HttpGet("http://www.linkedin.com");
				HttpResponse response1 = client1.execute(request1);
				Calendar c2 = Calendar.getInstance(); 
				long time2 = c2.getTimeInMillis();
				int length = (int)response1.getEntity().getContentLength();//doesn't always return the correct length
				textView.append("www.linkedin.com"+"\tResponse Time : "+ (time2 - time1)+ "Content Length: "+length);
				
				//textView.setText("\n");
				HttpClient client = new DefaultHttpClient();
				Calendar c3 = Calendar.getInstance(); 
				long time3 = c3.getTimeInMillis();
				HttpGet request = new HttpGet(urlText.getText().toString());
				System.out.println(urlText.getText().toString());
				HttpResponse response = client.execute(request);
				Calendar c4 = Calendar.getInstance(); 
				long time4 = c4.getTimeInMillis();
				// Get the response
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
				int length1 = (int)response.getEntity().getContentLength();//doesn't always return the correct length
				textView.append("\n"+urlText.getText().toString()+"\tResponse Time : "+ (time4 - time3)+ "Content Length: "+length1);
				String line = "";
				while ((line = rd.readLine()) != null) {
					//textView.append(line);
				}
			}

			catch (Exception e) {
				System.out.println("Throws Exception!!");
				textView.setText(e.getMessage());
			}
		break;
		}
	}	    
}
    
