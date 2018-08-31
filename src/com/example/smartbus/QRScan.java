package com.example.smartbus;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QRScan extends Activity {
private static final int ACTIVITY_RESULT_QR_DRDROID = 0;
	
	TextView report;
	Button scan;
	String username,amount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qrscan);
		report = (TextView) findViewById(R.id.textView2);
		scan = (Button) findViewById(R.id.button1);
		long stime= System.currentTimeMillis();
		username=getIntent().getStringExtra("username");
		amount=getIntent().getStringExtra("amount");
		
		
		
		scan.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent("la.droid.qr.scan");
				
				try {
					
					startActivityForResult(i, ACTIVITY_RESULT_QR_DRDROID);
//					Intent inn=new Intent(getApplicationContext(),ResultPage.class);
//					inn.putExtra("username", username);
//					inn.putExtra("amount", amount);
//					startActivity(inn);
				} 
				catch (ActivityNotFoundException activity) {
					
					QRScan.qrDroidRequired(QRScan.this);
				}
			}
		});
		
	}	
 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if( ACTIVITY_RESULT_QR_DRDROID == requestCode 
				&& data != null && data.getExtras() != null ) {
			
			String result = data.getExtras().getString("la.droid.qr.result");
			
			report.setText(result);
			report.setVisibility(View.VISIBLE);
			
			Intent in=new Intent(QRScan.this,ResultPage.class);
			
			in.putExtra("username", username);
			in.putExtra("amount", amount);
			startActivity(in);
		}
	}
 
	/* 
	 * 
	 * If we don't have QRDroid Application in our Device, 
	 * It will call below method (qrDroidRequired)
	 * 
	 */
	
	protected static void qrDroidRequired(final QRScan qrScan) {
		// TODO Auto-generated method stub
		
		AlertDialog.Builder AlertBox = new AlertDialog.Builder(qrScan);
		
		AlertBox.setMessage("QRDroid Missing");
		
		AlertBox.setPositiveButton("Direct Download", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
				qrScan.startActivity(new Intent(Intent.ACTION_VIEW, 
					Uri.parse("http://droid.la/apk/qr/")));
			}
		});
		
		AlertBox.setNeutralButton("From Market", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				qrScan.startActivity(new Intent(Intent.ACTION_VIEW, 
					Uri.parse("http://market.android.com/details?id=la.droid.qr")));
			}
		});
		
		AlertBox.setNegativeButton("Cancel", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
				dialog.cancel();
			}
		});
		
		AlertBox.create().show();
	}
	
	
	private class ActiveTask1 extends AsyncTask<String,Void,Void> {
		String res=null;
		@Override
		protected void onPreExecute() {
			//pb1.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(String... params) {
			res=Callservice.ResultService(username,amount,"ResultPage");
			return null;
		}

	protected void onPostExecute(Void result) {
		//pb.setVisibility(View.INVISIBLE);
		if(res.equals("success")) {
			
				Intent inn1=new Intent(getApplicationContext(),MainActivity.class);
				startActivity(inn1);
			}				
		
		else {
			Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
		}
		
	}

	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qrscan, menu);
		return true;
	}

}
