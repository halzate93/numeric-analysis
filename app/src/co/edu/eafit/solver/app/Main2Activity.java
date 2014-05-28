package co.edu.eafit.solver.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Main2Activity extends Activity {

	Spinner spinnermethods;
	Spinner spinnersize;
	private int actualMethod;
	private int actualSize;
	TextView tvrows;
	private String userMatrix = "";
	private EditText mathMatrix;
	Button solve;
	//global vars for Input
	String [] A;
	String [] b;
	String n = "";
	String strategy = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		spinnermethods = (Spinner) findViewById(R.id.spinnerMethods);
		spinnersize = (Spinner) findViewById(R.id.spinnerSize);
		tvrows = (TextView) findViewById(R.id.txtRows);
		createMethodList();
		createSizeList();
		spinnermethods.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        int selected = position;
		        switch (selected)
		        {
		        	case 0:
		        		Toast.makeText(Main2Activity.this, "Gaussian-Elimination", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		break;
		        	case 1:
		        		Toast.makeText(Main2Activity.this, "Partial Pivoting, Gaussian-Elimination", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		break;
		        	case 2:
		        		Toast.makeText(Main2Activity.this, "Total Pivoting, Gaussian-Elimination", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		break;
		        	case 3:
		        		Toast.makeText(Main2Activity.this, "Gaussian-Factorization-PartialPivoting", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		break;
		        	case 4:
		        		Toast.makeText(Main2Activity.this, "Crout", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		break;
		        	case 5:
		        		Toast.makeText(Main2Activity.this, "Dolittle", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		break;
		        	case 6:
		        		Toast.makeText(Main2Activity.this, "Cholesky", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		break;
		        }
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});

		spinnersize.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        int selected = position;
		        switch (selected)
		        {
		        	case 0:
		        		actualSize = selected;
		        		tvrows.setText("1\n2");
		        		break;
		        	case 1:
		        		actualSize = selected;
		        		tvrows.setText("1\n2\n3");
		        		break;
		        	case 2:
		        		actualSize = selected;
		        		tvrows.setText("1\n2\n3\n4");
		        		break;
		        	case 3:
		        		actualSize = selected;
		        		tvrows.setText("1\n2\n3\n4\n5");
		        		break;
		        	case 4:
		        		actualSize = selected;
		        		tvrows.setText("1\n2\n3\n4\n5\n6");
		        		break;
		        	case 5:
		        		actualSize = selected;
		        		tvrows.setText("1\n2\n3\n4\n5\n6\n7");
		        		break;
		        	case 6:
		        		actualSize = selected;
		        		tvrows.setText("1\n2\n3\n4\n5\n6\n7\n8");
		        		break;
		        	case 7:
		        		actualSize = selected;
		        		tvrows.setText("1\n2\n3\n4\n5\n6\n7\n8\n9");
		        		break;
		        	case 8:
		        		actualSize = selected;
		        		tvrows.setText("1\n2\n3\n4\n5\n6\n7\n8\n9\n10");
		        		break;
		        	case 9:
		        		actualSize = selected;
		        		tvrows.setText("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11");
		        		break;
		        	case 10:
		        		actualSize = selected;
		        		tvrows.setText("1\n2\n3\n4\n5\n6\n7\n8\n9\n10\n11\n12");
		        		break;
		        }
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
		
		mathMatrix = (EditText) findViewById(R.id.txtMatriz);
		
		solve = (Button) findViewById(R.id.btnSolve);
		solve.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				userMatrix = mathMatrix.getText().toString();
				System.out.println(userMatrix);
				if (userMatrix.equals(""))
				{
					Toast.makeText(Main2Activity.this, "You need to Fill the Matrix to evaluate", Toast.LENGTH_LONG).show();
					return;
				}
				if (userMatrix.contains(" "))
				{
					Toast.makeText(Main2Activity.this, "You have some white spaces please remove them", Toast.LENGTH_LONG).show();
					return;
				}
				int occurance = userMatrix.length() - userMatrix.replace(":", "").length();
				int occuranceequal = userMatrix.length() - userMatrix.replace("=", "").length();
				switch(actualSize)
				{
					case 0:
						if(occurance != 2)
						{
							Toast.makeText(Main2Activity.this, "Please check for colon':' sing between data", Toast.LENGTH_LONG).show();
							return;
						}
						if(occuranceequal != 2)
						{
							Toast.makeText(Main2Activity.this, "Please check '=' sing is missing", Toast.LENGTH_LONG).show();
							return;
						}
						n = "2";
						break;
					case 1:
						if(occurance != 6)
						{
							Toast.makeText(Main2Activity.this, "Please check for colon':' sing between data", Toast.LENGTH_LONG).show();
							return;
						}
						if(occuranceequal != 3)
						{
							Toast.makeText(Main2Activity.this, "Please check '=' sing is missing", Toast.LENGTH_LONG).show();
							return;
						}
						n = "3";
						break;
					case 2:
						if(occurance != 12)
						{
							Toast.makeText(Main2Activity.this, "Please check for colon':' sing between data", Toast.LENGTH_LONG).show();
							return;
						}
						if(occuranceequal != 4)
						{
							Toast.makeText(Main2Activity.this, "Please check '=' sing is missing", Toast.LENGTH_LONG).show();
							return;
						}
						n = "4";
						break;
					case 3:
						if(occurance != 20)
						{
							Toast.makeText(Main2Activity.this, "Please check for colon':' sing between data", Toast.LENGTH_LONG).show();
							return;
						}
						if(occuranceequal != 5)
						{
							Toast.makeText(Main2Activity.this, "Please check '=' sing is missing", Toast.LENGTH_LONG).show();
							return;
						}
						n = "5";
						break;
					case 4:
						if(occurance != 30)
						{
							Toast.makeText(Main2Activity.this, "Please check for colon':' sing between data", Toast.LENGTH_LONG).show();
							return;
						}
						if(occuranceequal != 6)
						{
							Toast.makeText(Main2Activity.this, "Please check '=' sing is missing", Toast.LENGTH_LONG).show();
							return;
						}
						n = "6";
						break;
					case 5:
						if(occurance != 42)
						{
							Toast.makeText(Main2Activity.this, "Please check for colon':' sing between data", Toast.LENGTH_LONG).show();
							return;
						}
						if(occuranceequal != 7)
						{
							Toast.makeText(Main2Activity.this, "Please check '=' sing is missing", Toast.LENGTH_LONG).show();
							return;
						}
						n = "7";
						break;
					case 6:
						if(occurance != 56)
						{
							Toast.makeText(Main2Activity.this, "Please check for colon':' sing between data", Toast.LENGTH_LONG).show();
							return;
						}
						if(occuranceequal != 8)
						{
							Toast.makeText(Main2Activity.this, "Please check '=' sing is missing", Toast.LENGTH_LONG).show();
							return;
						}
						n = "8";
						break;
					case 7:
						if(occurance != 72)
						{
							Toast.makeText(Main2Activity.this, "Please check for colon':' sing between data", Toast.LENGTH_LONG).show();
							return;
						}
						if(occuranceequal != 9)
						{
							Toast.makeText(Main2Activity.this, "Please check '=' sing is missing", Toast.LENGTH_LONG).show();
							return;
						}
						n = "9";
						break;
					case 8:
						if(occurance != 90)
						{
							Toast.makeText(Main2Activity.this, "Please check for colon':' sing between data", Toast.LENGTH_LONG).show();
							return;
						}
						if(occuranceequal != 10)
						{
							Toast.makeText(Main2Activity.this, "Please check '=' sing is missing", Toast.LENGTH_LONG).show();
							return;
						}
						n = "10";
						break;
					case 9:
						if(occurance != 110)
						{
							Toast.makeText(Main2Activity.this, "Please check for colon':' sing between data", Toast.LENGTH_LONG).show();
							return;
						}
						if(occuranceequal != 11)
						{
							Toast.makeText(Main2Activity.this, "Please check '=' sing is missing", Toast.LENGTH_LONG).show();
							return;
						}
						n = "11";
						break;
					case 10:
						if(occurance != 132)
						{
							Toast.makeText(Main2Activity.this, "Please check for colon':' sing between data", Toast.LENGTH_LONG).show();
							return;
						}
						if(occuranceequal != 12)
						{
							Toast.makeText(Main2Activity.this, "Please check '=' sing is missing", Toast.LENGTH_LONG).show();
							return;
						}
						n = "12";
						break;
				}
				switch(actualMethod)
				{
					case 0:
						strategy = "None";
					    final Pattern pattern = Pattern.compile("[\\*=\"]");
					    b = pattern.split(userMatrix);
					    System.out.println(Arrays.toString(b));
						break;
					case 1:
						strategy = "Partial";
						break;
					case 2:
						strategy = "Total";
						break;
					case 3:
						
						break;
					case 4:
						
						break;
					case 5:
						
						break;
					case 6:
						
						break;
				}
				
			}
		});
	}
	
	private void createMethodList()
	{
		List<String> SpinnerList = new ArrayList<String>();
		SpinnerList.add("Gaussian-Elimination");
		SpinnerList.add("Partial Pivoting, Gaussian-Elimination");
		SpinnerList.add("Total Pivoting, Gaussian-Elimination");
		SpinnerList.add("Gaussian-Factorization-PartialPivoting");
		SpinnerList.add("Crout");
		SpinnerList.add("Dolittle");
		SpinnerList.add("Cholesky");
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SpinnerList);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnermethods.setAdapter(adapter);
	}
	
	private void createSizeList()
	{
		List<String> SpinnerList = new ArrayList<String>();
		SpinnerList.add("2 x 2");
		SpinnerList.add("3 x 3");
		SpinnerList.add("4 x 4");
		SpinnerList.add("5 x 5");
		SpinnerList.add("6 x 6");
		SpinnerList.add("7 x 7");
		SpinnerList.add("8 x 8");
		SpinnerList.add("9 x 9");
		SpinnerList.add("10 x 10");
		SpinnerList.add("11 x 11");
		SpinnerList.add("12 x 12");
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SpinnerList);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinnersize.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			//Navego a la página de Ayuda
			Intent intent = new Intent(Main2Activity.this, Help2Activity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
}
