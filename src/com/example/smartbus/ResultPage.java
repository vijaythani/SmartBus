package com.example.smartbus;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultPage extends Activity {

	TextView t1;
	Button b1;
	String username,amount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_page);
		t1=(TextView)findViewById(R.id.textView1);
		b1=(Button)findViewById(R.id.button1);
		username=getIntent().getStringExtra("username");
		amount=getIntent().getStringExtra("amount");
		
		t1.setText("Total Amount is: "+amount);
		b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ActiveTask1 task=new ActiveTask1();
				task.execute();
			}
			
		});
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
		getMenuInflater().inflate(R.menu.result_page, menu);
		return true;
	}

}
