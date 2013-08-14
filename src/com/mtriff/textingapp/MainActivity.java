package com.mtriff.textingapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button askButton;
	Button askButtonMessenger;
	
	String phoneNumber=""; //phone number to text
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        askButton=(Button) findViewById(R.id.askButton);
        
        askButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				sendAskText();
			}
		});
        
        askButtonMessenger=(Button) findViewById(R.id.askButtonMessenger);
        
        askButtonMessenger.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i=new Intent(android.content.Intent.ACTION_VIEW);
				i.putExtra("address", phoneNumber);
				i.putExtra("sms_body", "What does 2 + 2 equal?");
				i.setType("vnd.android-dir/mms-sms");
				startActivity(i);
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void sendAskText()
    {
    	SmsManager texter=SmsManager.getDefault();
    	
    	texter.sendTextMessage("+16477865931", null, "What does 2 + 2 equal?", null, null);
    }
}
