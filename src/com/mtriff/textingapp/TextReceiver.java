package com.mtriff.textingapp;

import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class TextReceiver extends BroadcastReceiver {
	
	String phoneNumber=""; //phone number to text
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle=intent.getExtras();
		SmsMessage[] messages=null;
		Log.d("Received", "Text received.");

		if(bundle!=null)
		{
			Log.d("Inside", "Past if");
			Object[] pdus=(Object[]) bundle.get("pdus");
			messages=new SmsMessage[pdus.length];
			
			for(int i=0; i<messages.length;i++)
			{
				messages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
				Log.d("From", messages[i].getOriginatingAddress().toString());
				if(messages[i].getOriginatingAddress().toString().compareTo(phoneNumber)==0)
				{
					String answer=messages[i].getMessageBody().toString().toLowerCase(Locale.US);
					Log.d("Answer", answer);
					if(answer.contains("4") || answer.contains("four"))
					{
						Log.d("Toast","Pre-toast");
						Toast.makeText(context, "They correctly answered "+answer+"!", Toast.LENGTH_LONG).show();
						Log.d("Toast","Post-toast");
					}
				}				
			}
			
		}
		
	}

}
