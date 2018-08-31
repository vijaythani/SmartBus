package com.example.smartbus;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	
	// ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar
	ActionBarDrawerToggle mDrawerToggle;	
	
	// Title of the action bar
	String mTitle="";
EditText e1,e2;
Button b1,b2;
ProgressBar pb1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
mTitle = (String) getTitle();		
		
		
		// Getting reference to the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		
		
		mDrawerList = (ListView) findViewById(R.id.drawer_list);
		// Getting reference to the ActionBarDrawerToggle
		mDrawerToggle = new ActionBarDrawerToggle(	this, 
													mDrawerLayout, 
													R.drawable.ic_drawer, 
													R.string.drawer_open,
													R.string.drawer_close){
			
			/** Called when drawer is closed */
            public void onDrawerClosed(View view) {
            	getActionBar().setTitle(mTitle);
            	invalidateOptionsMenu();
                
            }

            /** Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle("Select Menu");
                invalidateOptionsMenu();
            }
			
		};
		
		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		// Creating an ArrayAdapter to add items to the listview mDrawerList
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					getBaseContext(), 
					R.layout.drawer_list_item  , 
					getResources().getStringArray(R.array.Menu) 
				);
		
		// Setting the adapter on mDrawerList
		mDrawerList.setAdapter(adapter);
		
		// Enabling Home button
		getActionBar().setHomeButtonEnabled(true);
		
		// Enabling Up navigation
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		// Setting item click listener for the listview mDrawerList
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			
			@Override
			public void onItemClick(AdapterView<?> parent,
							View view,
							int position,
							long id) {			
				
				// Getting an array of rivers
				String[] rivers = getResources().getStringArray(R.array.Menu);
				
				//Currently selected river
				mTitle = rivers[position];				
				if(mTitle.equals("User Login"))
				{
					Intent inn=new Intent(getApplicationContext(),Userlogin.class);
					startActivity(inn);
				}
				else if(mTitle.equals("Bank Details"))
				{
					Intent inn1=new Intent(getApplicationContext(),Bankdetails.class);
					startActivity(inn1);
				}
				else if(mTitle.equals("About"))
				{
					Intent inn1=new Intent(getApplicationContext(),About.class);
					startActivity(inn1);
				}
NavFragment rFragment = new NavFragment();
				
				// Creating a Bundle object
				Bundle data = new Bundle();
				
				// Setting the index of the currently selected item of mDrawerList
				data.putInt("position", position);
				
				// Setting the position to the fragment
				rFragment.setArguments(data);
mDrawerLayout.closeDrawer(mDrawerList);				
				
			

			}
		});	
	}

	 @Override
	 protected void onPostCreate(Bundle savedInstanceState) {
		 super.onPostCreate(savedInstanceState);	     
	     mDrawerToggle.syncState();	
	 }

	/** Handling the touch event of app icon */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {     
	    if (mDrawerToggle.onOptionsItemSelected(item)) {
	      return true;
	    }
	    return super.onOptionsItemSelected(item);
	}


	/** Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
	    return super.onPrepareOptionsMenu(menu);
	}

			
		}
