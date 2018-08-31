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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class HomePage extends Activity {

	String dlist;
	String username;
	TextView t2;
	Button b1;
	ProgressBar pb;
	String distance;
	CalculateDistance caldis = new CalculateDistance();
	double slat1,slon1,elat1,elon1;
	String amount="20";
	int distanc=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		
		t2=(TextView)findViewById(R.id.textView2);
		b1=(Button)findViewById(R.id.button1);
		pb=(ProgressBar)findViewById(R.id.progressBar1);
		
		dlist=getIntent().getStringExtra("list");
		username=getIntent().getStringExtra("username");
		Toast.makeText(getApplicationContext(), username, Toast.LENGTH_LONG).show();
		
		//String[] parts=dlist.split("-");
		t2.setText(dlist);
		b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ActiveTask1 task=new ActiveTask1();
				task.execute();
				/*Intent inn=new Intent(getApplicationContext(),QRScan.class);
				startActivity(inn);*/
			}
			
		});
		
	}
	
	private class ActiveTask1 extends AsyncTask<String,Void,Void> {
		String res=null;
		@Override
		protected void onPreExecute() {
			pb.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(String... params) {
			res=Callservice.PaymentService(username,"MakePayment");
			return null;
		}

	protected void onPostExecute(Void result) {
		pb.setVisibility(View.INVISIBLE);
		Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
		String[] parts=res.split("-");
		String slat=parts[0];
		String slon=parts[1];
		String elat=parts[2];
		String elon=parts[3];
		slat1=Double.parseDouble(slat);
		slon1=Double.parseDouble(slon);
		elat1=Double.parseDouble(elat);
		elon1=Double.parseDouble(elon);
		GetDeviceDistance gd = new GetDeviceDistance();
		gd.execute();
		/*distance=CalculateDistance.getDistanceFromJson(slat1,slon1,elat1,elon1);
		Toast.makeText(getApplicationContext(), distance, Toast.LENGTH_LONG).show();*/
		
	}

	}
	
	private class GetDeviceDistance extends AsyncTask<String, Void, Void> {
		String update=null,dis=null;
		
		@Override
		protected void onPreExecute() {
			Toast.makeText(getApplicationContext(),"please wait...",Toast.LENGTH_SHORT).show();
			
		}
		@Override
		protected Void doInBackground(String... params) {
			dis=caldis.getDistanceFromJson(slat1,slon1,elat1,elon1);
			//update = CallService.updateMessage(dis, username, selecteddevice,phno, "updateMessage");
			
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			System.out.println("+++++++++++"+dis);
			Toast.makeText(getApplicationContext(),dis,Toast.LENGTH_SHORT).show();
			AlertDialog.Builder dia=new AlertDialog.Builder(HomePage.this);
			//dia.setIcon(icon);
			dia.setTitle("Distance Calculation");
			dia.setMessage("distance is "+dis);	
			dia.setCancelable(false);	
			//rakesh
			dia.setPositiveButton("Calculate Amount", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					String[] parts=dis.split(" ");
					String dist=parts[0];
					String val=parts[1];
					 distanc=Integer.parseInt(dist);
					if((distanc>100)||(distanc<200))
					{
						Toast.makeText(getApplicationContext(), "Amount is: "+amount, Toast.LENGTH_LONG).show();
						Intent inn=new Intent(getApplicationContext(),QRScan.class);
						inn.putExtra("username", username);
						inn.putExtra("amount", amount);
						startActivity(inn);
						
					}
					//Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://maps.google.co.in/maps?saddr=madurai&daddr=Chennai,+Tamil+Nadu&hl=en&sll=13.033994,80.236588&sspn=0.403379,0.676346&geocode=FVFylwAdXwOoBCkzlhixgsUAOzEzOfZkcluV3A%3BFf4pxwAdyYfIBClhM31P6mVSOjEz1GNoC6dhbg&oq=chennai&mra=ls&t=m&z=8"));
//					SmsManager sms = SmsManager.getDefault();
//					sms.sendTextMessage("8760938846", null,"Your Tracker Long-->"+dlon+"Lat-->"+dlan , null, null);
//					Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://maps.google.com/maps?q="+dlan+","+dlon));
//					startActivity(intent);
//					dialog.cancel();					
				}				
			});
//			dia.setNegativeButton("No Thanks", new DialogInterface.OnClickListener(){
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.cancel();
//					
//				}				
//			});
			AlertDialog dialo=dia.create();
			dialo.show();
			//Toast.makeText(getApplicationContext(), update, Toast.LENGTH_LONG).show();
			
		}
		
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_page, menu);
		return true;
	}

}
