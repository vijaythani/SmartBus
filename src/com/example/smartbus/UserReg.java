package com.example.smartbus;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class UserReg extends Activity {
	EditText u,p,e,m;
	Button reg;
	ProgressBar pb1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_reg);
		

		u=(EditText)findViewById(R.id.editText1);
		p=(EditText)findViewById(R.id.editText2);
		//cpass=(EditText)findViewById(R.id.editText3);
		e=(EditText)findViewById(R.id.editText3);
		m=(EditText)findViewById(R.id.editText4);
		reg=(Button)findViewById(R.id.button1);
		pb1=(ProgressBar)findViewById(R.id.progressBar1);
		reg.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			//	String uname=user.getText().toString();
				
				  String MobilePattern = "[0-9]{10}";
				    
				
				if((u.equals(""))||(p.equals(""))||(e.equals(""))||(m.equals("")))
				{
					Toast.makeText(getApplicationContext(), "Please fill all fields", Toast.LENGTH_LONG).show();
				}
				
				else if(!m.getText().toString().matches(MobilePattern)) {

			        Toast.makeText(getApplicationContext(), "Please enter valid 10 digit phone number", Toast.LENGTH_SHORT).show();
				}
				else
				{
					ActiveTask1 task1=new ActiveTask1();
					task1.execute();
				}
			}
			
		});
	}
	private class ActiveTask1 extends AsyncTask<String,Void,Void> {
		String s1=u.getText().toString();
		String s2=p.getText().toString();
		String s4=e.getText().toString();
		String s5=m.getText().toString();
		String res=null;
		@Override
		protected void onPreExecute() {
			pb1.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(String... params) {
			res=Callservice.registerService(s1,s2,s4,s5,"Register");
			return null;
			
		}
	
	protected void onPostExecute(Void result) {
		pb1.setVisibility(View.INVISIBLE);
		if(res.equals("success")) {

				
			AlertDialog.Builder dia=new AlertDialog.Builder(UserReg.this);
			dia.setTitle("trus again");
			dia.setMessage("account created");
			dia.setCancelable(false);
			dia.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent intent=new Intent(getBaseContext(),Userlogin.class);
					startActivity(intent);
					dialog.cancel();
				}
			});
			AlertDialog dialo=dia.create();
			dialo.show();
					
					
				}				
		
		else {
			Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
		}
		
	}
 
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_reg, menu);
		return true;
	}

}
