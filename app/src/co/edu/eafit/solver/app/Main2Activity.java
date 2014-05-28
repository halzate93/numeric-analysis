package co.edu.eafit.solver.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
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
	private EditText xi;
	private EditText tol;
	private EditText iter;
	private EditText lamb;
	private EditText xv;
	private EditText xn;
	private EditText fxn;
	LinearLayout layoutjs;
	RelativeLayout relativelayoutFirst;
	RelativeLayout relativelayoutSecond;
	Button solve;
	
	//global vars for Input
	String [] A;
	String [] b;
	String n = "";
	String strategy = "";
	String [] Xi;
	Double tolerance;
	int iterations;
	Double lambda;
	Double x;
	String [] Xn;
	String [] Fxn;
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
		        		layoutjs.setVisibility(View.INVISIBLE);
		        		relativelayoutFirst.setVisibility(View.VISIBLE);
		        		relativelayoutSecond.setVisibility(View.INVISIBLE);
		        		break;
		        	case 1:
		        		Toast.makeText(Main2Activity.this, "Partial Pivoting, Gaussian-Elimination", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.VISIBLE);
		        		relativelayoutSecond.setVisibility(View.INVISIBLE);
		        		layoutjs.setVisibility(View.INVISIBLE);
		        		break;
		        	case 2:
		        		Toast.makeText(Main2Activity.this, "Total Pivoting, Gaussian-Elimination", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.VISIBLE);
		        		relativelayoutSecond.setVisibility(View.INVISIBLE);
		        		layoutjs.setVisibility(View.INVISIBLE);
		        		break;
		        	case 3:
		        		Toast.makeText(Main2Activity.this, "Gaussian-Factorization-PartialPivoting", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.VISIBLE);
		        		relativelayoutSecond.setVisibility(View.INVISIBLE);
		        		layoutjs.setVisibility(View.INVISIBLE);
		        		break;
		        	case 4:
		        		Toast.makeText(Main2Activity.this, "Crout", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.VISIBLE);
		        		relativelayoutSecond.setVisibility(View.INVISIBLE);
		        		layoutjs.setVisibility(View.INVISIBLE);
		        		break;
		        	case 5:
		        		Toast.makeText(Main2Activity.this, "Dolittle", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.VISIBLE);
		        		relativelayoutSecond.setVisibility(View.INVISIBLE);
		        		layoutjs.setVisibility(View.INVISIBLE);
		        		break;
		        	case 6:
		        		Toast.makeText(Main2Activity.this, "Cholesky", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.VISIBLE);
		        		relativelayoutSecond.setVisibility(View.INVISIBLE);
		        		layoutjs.setVisibility(View.INVISIBLE);
		        		break;
		        	case 7:
		        		Toast.makeText(Main2Activity.this, "Jacobi", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		layoutjs.setVisibility(View.VISIBLE);
		        		relativelayoutFirst.setVisibility(View.VISIBLE);
		        		relativelayoutSecond.setVisibility(View.INVISIBLE);
		        		break;
		        	case 8:
		        		Toast.makeText(Main2Activity.this, "Gauss-Seidel", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		layoutjs.setVisibility(View.VISIBLE);
		        		relativelayoutFirst.setVisibility(View.VISIBLE);
		        		relativelayoutSecond.setVisibility(View.INVISIBLE);
		        		break;
		        	case 9:
		        		Toast.makeText(Main2Activity.this, "SystemEquation", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.INVISIBLE);
		        		relativelayoutSecond.setVisibility(View.VISIBLE);
		        		break;
		        	case 10:
		        		Toast.makeText(Main2Activity.this, "Newton", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.INVISIBLE);
		        		relativelayoutSecond.setVisibility(View.VISIBLE);
		        		break;
		        	case 11:
		        		Toast.makeText(Main2Activity.this, "Lagrange", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.INVISIBLE);
		        		relativelayoutSecond.setVisibility(View.VISIBLE);
		        		break;
		        	case 12:
		        		Toast.makeText(Main2Activity.this, "Neville", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.INVISIBLE);
		        		relativelayoutSecond.setVisibility(View.VISIBLE);
		        		break;
		        	case 13:
		        		Toast.makeText(Main2Activity.this, "Lineal Spline", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.INVISIBLE);
		        		relativelayoutSecond.setVisibility(View.VISIBLE);
		        		break;
		        	case 14:
		        		Toast.makeText(Main2Activity.this, "Quadratic Spline", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.INVISIBLE);
		        		relativelayoutSecond.setVisibility(View.VISIBLE);
		        		break;
		        	case 15:
		        		Toast.makeText(Main2Activity.this, "Cubic Spline", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		relativelayoutFirst.setVisibility(View.INVISIBLE);
		        		relativelayoutSecond.setVisibility(View.VISIBLE);
		        		break;
		        }
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
		layoutjs = (LinearLayout)findViewById(R.id.layoutSJ);
		relativelayoutFirst = (RelativeLayout)findViewById(R.id.layoutFirst);
		relativelayoutSecond = (RelativeLayout)findViewById(R.id.layoutSecond);
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
		xi = (EditText) findViewById(R.id.etXi);
		tol  = (EditText) findViewById(R.id.etTol);
		iter  = (EditText) findViewById(R.id.etIter);
		lamb  = (EditText) findViewById(R.id.etLambda);
		xv = (EditText) findViewById(R.id.txtX);
		xn = (EditText) findViewById(R.id.txtXn);
		fxn = (EditText) findViewById(R.id.txtFxn);
		
		solve = (Button) findViewById(R.id.btnSolve);
		solve.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				userMatrix = mathMatrix.getText().toString();
				if (actualMethod > 8)
				{
					actualSize = -1;
					if (xv.getText().toString().equals(""))
					{
						Toast.makeText(Main2Activity.this, "You need to Fill the X value", Toast.LENGTH_LONG).show();
						return;
					}
					if (xn.getText().toString().equals(""))
					{
						Toast.makeText(Main2Activity.this, "You need to Fill the Xn value", Toast.LENGTH_LONG).show();
						return;
					}
					if (fxn.getText().toString().equals(""))
					{
						Toast.makeText(Main2Activity.this, "You need to Fill the F(Xn) value", Toast.LENGTH_LONG).show();
						return;
					}
				}
				if (userMatrix.equals("") && actualMethod <= 8)
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
					    A = GetA();
					    System.out.println(Arrays.toString(A));
					    b = GetB();
					    System.out.println(Arrays.toString(b));
						break;
					case 1:
						strategy = "Partial";
						A = GetA();
					    System.out.println(Arrays.toString(A));
					    b = GetB();
					    System.out.println(Arrays.toString(b));
						break;
					case 2:
						strategy = "Total";
						A = GetA();
					    System.out.println(Arrays.toString(A));
					    b = GetB();
					    System.out.println(Arrays.toString(b));
						break;
					case 3:
						A = GetA();
					    System.out.println(Arrays.toString(A));
					    b = GetB();
					    System.out.println(Arrays.toString(b));
						break;
					case 4:
						A = GetA();
					    System.out.println(Arrays.toString(A));
					    b = GetB();
					    System.out.println(Arrays.toString(b));
						break;
					case 5:
						A = GetA();
					    System.out.println(Arrays.toString(A));
					    b = GetB();
					    System.out.println(Arrays.toString(b));
						break;
					case 6:
						A = GetA();
					    System.out.println(Arrays.toString(A));
					    b = GetB();
					    System.out.println(Arrays.toString(b));
						break;
					case 7:
						A = GetA();
					    System.out.println(Arrays.toString(A));
					    b = GetB();
					    System.out.println(Arrays.toString(b));
					    Xi = GetXi();
					    System.out.println(Arrays.toString(Xi));
					    tolerance = Double.parseDouble(tol.getText().toString());
					    iterations = Integer.parseInt(iter.getText().toString());
					    lambda = Double.parseDouble(lamb.getText().toString());
						break;
					case 8:
						A = GetA();
					    System.out.println(Arrays.toString(A));
					    b = GetB();
					    System.out.println(Arrays.toString(b));
					    Xi = GetXi();
					    System.out.println(Arrays.toString(Xi));
					    tolerance = Double.parseDouble(tol.getText().toString());
					    iterations = Integer.parseInt(iter.getText().toString());
					    lambda = Double.parseDouble(lamb.getText().toString());
						break;
					case 9:
						x = Double.parseDouble(xv.getText().toString());
						Xn = GetXn();
						Fxn = GetFxn();
						System.out.println(Arrays.toString(Xn));
						System.out.println(Arrays.toString(Fxn));
						break;
					case 10:
						x = Double.parseDouble(xv.getText().toString());
						Xn = GetXn();
						Fxn = GetFxn();
						break;
					case 11:
						x = Double.parseDouble(xv.getText().toString());
						Xn = GetXn();
						Fxn = GetFxn();
						break;
					case 12:
						x = Double.parseDouble(xv.getText().toString());
						Xn = GetXn();
						Fxn = GetFxn();
						break;
					case 13:
						x = Double.parseDouble(xv.getText().toString());
						Xn = GetXn();
						Fxn = GetFxn();
						break;
					case 14:
						x = Double.parseDouble(xv.getText().toString());
						Xn = GetXn();
						Fxn = GetFxn();
						break;
					case 15:
						x = Double.parseDouble(xv.getText().toString());
						Xn = GetXn();
						Fxn = GetFxn();
						break;
				}
				Intent mIntent = new Intent(Main2Activity.this, Solver2Activity.class);
				mIntent.putExtra("method", actualMethod);
				mIntent.putExtra("A", A);
				mIntent.putExtra("b", b);
				mIntent.putExtra("n", n);
				mIntent.putExtra("strategy", strategy);
				mIntent.putExtra("Xi", Xi);
				mIntent.putExtra("tolerance", tolerance);
				mIntent.putExtra("iterations", iterations);
				mIntent.putExtra("lambda", lambda);
				mIntent.putExtra("x", x);
				mIntent.putExtra("Xn", Xn);
				mIntent.putExtra("Fxn", Fxn);
				startActivity(mIntent);
			}
		});
	}
	
	private String[] GetA()
	{
		String[] ar;
		String nchar = "n";
		String matrix = userMatrix.replace("\n", "n");
		String bvalues = "";
		matrix = matrix + nchar;
	    final Pattern pattern = Pattern.compile("=(.*?)n");
	    return ar = pattern.split(matrix);
	}
	private String[] GetB()
	{
		String[] br;
		String nchar = "n";
		String matrix = userMatrix.replace("\n", "n");
		String bvalues = "";
		matrix = matrix + nchar;
	    Pattern pattern2 = Pattern.compile("=(.*?)n");
	    Matcher matcher = pattern2.matcher(matrix);
	    while (matcher.find()) {
	    	bvalues = bvalues + matcher.group(1)+":";
	    }
	    return br = bvalues.split(":");
	}
	private String[] GetXi()
	{
		String[] xr;
		String matrix = xi.getText().toString();
	    return xr = matrix.split(":");
	}
	private String[] GetXn()
	{
		String[] xnr;
		String matrix = xn.getText().toString().replace("\n", "n");
		return xnr = matrix.split("n");
	}
	private String[] GetFxn()
	{
		String[] fxnr;
		String matrix = fxn.getText().toString().replace("\n", "n");
		return fxnr = matrix.split("n");
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
		SpinnerList.add("Jacobi");
		SpinnerList.add("Gauss-Seidel");
		SpinnerList.add("SystemEquation");
		SpinnerList.add("Newton");
		SpinnerList.add("Lagrange");
		SpinnerList.add("Neville");
		SpinnerList.add("Lineal Spline");
		SpinnerList.add("Quadratic Spline");
		SpinnerList.add("Cubic Spline");
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
