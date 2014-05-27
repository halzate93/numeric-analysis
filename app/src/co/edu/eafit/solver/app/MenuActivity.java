package co.edu.eafit.solver.app;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Build;

public class MenuActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		Button option1  = (Button)findViewById(R.id.btnOption1);
		
		option1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent mainIntent = new Intent().setClass(
                        MenuActivity.this, MainActivity.class);
                startActivity(mainIntent);
			}
		});
		
		Button option2  = (Button)findViewById(R.id.btnOption2);
		
		option2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Intent mainIntent = new Intent().setClass(
						MenuActivity.this, Main2Activity.class);
                startActivity(mainIntent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
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

}
