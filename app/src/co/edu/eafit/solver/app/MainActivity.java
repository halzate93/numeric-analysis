package co.edu.eafit.solver.app;

import java.util.*;

import co.edu.eafit.solver.lib.methods.enums.EMethod;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity {
	
	Spinner spinner;
	private View view1;
	private TableLayout tableIncremental; 
	private TableLayout tableBisection;
	private TableLayout tableFixedpoint;
	private TableLayout tableNewton;
	private TableLayout tableSecant;
	private int actualMethod;
	private EditText mathExpression;
	private EditText x0;
	private EditText d0;
	private EditText ite;
	private EditText mathExpressionXFixedPoint;
	private Button one;
	private Button two;
	private Button three;
	private Button plus;
	private Button minus;
	private Button power;
    private Button exp;
    private Button four;
    private Button five;
	private Button six;
	private Button times;
	private Button divide;
	private Button cos;
    private Button sin;
    
    private Button seven;
   	private Button eight;
   	private Button nine;
   	private Button tan;
   	private Button log;
   	private Button ln;
    private Button del;
    
    
    private Button plusminus;
   	private Button zero;
   	private Button dot;
   	private Button x;
   	private Button openbracket;
   	private Button closebracket;
    private Button pi;
    
    private String userInput = "";
    private String userInputXfixedpoint = "";
    private Button borrar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		if(!userInput.equals("")){
			userInput = "";
		}
		spinner = (Spinner) findViewById(R.id.spinnerMetodos);
		createMethodList();
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        int selected = position;
		        switch (selected)
		        {
		        	case 0:
		        		Toast.makeText(MainActivity.this, "Incremental Search", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.VISIBLE);
		        		tableBisection.setVisibility(View.INVISIBLE);
		        		tableFixedpoint.setVisibility(View.INVISIBLE);
		        		tableNewton.setVisibility(View.INVISIBLE);
		        		tableSecant.setVisibility(View.INVISIBLE);
		        		break;
		        	case 1:
		        		Toast.makeText(MainActivity.this, "Bisection", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.INVISIBLE);
		        		tableBisection.setVisibility(View.VISIBLE);
		        		tableFixedpoint.setVisibility(View.INVISIBLE);
		        		tableNewton.setVisibility(View.INVISIBLE);
		        		tableSecant.setVisibility(View.INVISIBLE);
		        		break;
		        	case 2:
		        		Toast.makeText(MainActivity.this, "Fake Rule", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.INVISIBLE);
		        		tableBisection.setVisibility(View.VISIBLE);
		        		tableFixedpoint.setVisibility(View.INVISIBLE);
		        		tableNewton.setVisibility(View.INVISIBLE);
		        		tableSecant.setVisibility(View.INVISIBLE);
		        		break;
		        	case 3:
		        		Toast.makeText(MainActivity.this, "Fixed Point", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.INVISIBLE);
		        		tableBisection.setVisibility(View.INVISIBLE);
		        		tableFixedpoint.setVisibility(View.VISIBLE);
		        		tableNewton.setVisibility(View.INVISIBLE);
		        		tableSecant.setVisibility(View.INVISIBLE);
		        		break;
		        	case 4:
		        		Toast.makeText(MainActivity.this, "Newton", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.INVISIBLE);
		        		tableBisection.setVisibility(View.INVISIBLE);
		        		tableFixedpoint.setVisibility(View.INVISIBLE);
		        		tableNewton.setVisibility(View.VISIBLE);
		        		tableSecant.setVisibility(View.INVISIBLE);
		        		break;
		        	case 5:
		        		Toast.makeText(MainActivity.this, "Secant", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.INVISIBLE);
		        		tableBisection.setVisibility(View.INVISIBLE);
		        		tableFixedpoint.setVisibility(View.INVISIBLE);
		        		tableNewton.setVisibility(View.INVISIBLE);
		        		tableSecant.setVisibility(View.VISIBLE);
		        		break;
		        	case 6:
		        		Toast.makeText(MainActivity.this, "Multiple Roots", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.INVISIBLE);
		        		tableBisection.setVisibility(View.INVISIBLE);
		        		tableFixedpoint.setVisibility(View.INVISIBLE);
		        		tableNewton.setVisibility(View.INVISIBLE);
		        		tableSecant.setVisibility(View.INVISIBLE);
		        		break;
		        }
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
		tableIncremental = (TableLayout)findViewById(R.id.TableIncremental);
		tableBisection = (TableLayout)findViewById(R.id.TableBisection);
		tableFixedpoint = (TableLayout)findViewById(R.id.TableFixedpoint);
		tableNewton = (TableLayout)findViewById(R.id.TableNewton);
		tableSecant = (TableLayout)findViewById(R.id.TableSecant);
		
		mathExpressionXFixedPoint = (EditText)findViewById(R.id.etX);
		hideSoftKeyboard(mathExpressionXFixedPoint);
		
		mathExpression = (EditText)findViewById(R.id.mathinput);
		hideSoftKeyboard(mathExpression);
		
		x0 = (EditText)findViewById(R.id.etXo);
		d0 = (EditText)findViewById(R.id.etDelta);
		ite = (EditText)findViewById(R.id.etIterations);
		
		one = (Button)findViewById(R.id.button1);
		two = (Button)findViewById(R.id.button2);
		three = (Button)findViewById(R.id.button3);
		plus = (Button)findViewById(R.id.button4);
		minus = (Button)findViewById(R.id.button5);
		power = (Button)findViewById(R.id.button6);
		exp = (Button)findViewById(R.id.button7);
		four = (Button)findViewById(R.id.button8);
		five = (Button)findViewById(R.id.button9);
		six = (Button)findViewById(R.id.button10);
		times = (Button)findViewById(R.id.button11);
		divide = (Button)findViewById(R.id.button12);
		cos = (Button)findViewById(R.id.button13);
		sin = (Button)findViewById(R.id.button14);
		seven  = (Button)findViewById(R.id.button15);
		eight = (Button)findViewById(R.id.button16);
		nine = (Button)findViewById(R.id.button17);
		tan = (Button)findViewById(R.id.button18);
		log = (Button)findViewById(R.id.button19);
		ln = (Button)findViewById(R.id.button20);
		del = (Button)findViewById(R.id.button21);
		plusminus  = (Button)findViewById(R.id.button22);
		zero = (Button)findViewById(R.id.button23);
		dot = (Button)findViewById(R.id.button24);
		x = (Button)findViewById(R.id.button25);
		openbracket = (Button)findViewById(R.id.button26);
	    closebracket = (Button)findViewById(R.id.button27);
		pi = (Button)findViewById(R.id.button28);
		pi.setText("π");
		borrar = (Button)findViewById(R.id.btnBorrar);
		
		Button solve  = (Button)findViewById(R.id.calculatedBbutton);
		
		solve.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				switch (actualMethod)
				{
					case 0:
						if (isEmpty(x0))
						{
							Toast.makeText(MainActivity.this, "Hey! Please Check de Xo Field. It couldn´t be empty", Toast.LENGTH_LONG).show();
							return;
						}
						else if (isEmpty(d0))
						{
							Toast.makeText(MainActivity.this, "Hey! Please Check de Delta X Field. It couldn´t be empty", Toast.LENGTH_LONG).show();
							return;
						}
						else if (isEmpty(ite))
						{
							Toast.makeText(MainActivity.this, "Hey! Please Check de Iter Field. It couldn´t be empty", Toast.LENGTH_LONG).show();
							return;
						}
						else
						{
							if(userInput.equals("")){
								
								Toast.makeText(MainActivity.this, "Ups! You need to insert ecuation to evaluate", Toast.LENGTH_LONG).show();
								return;
							}
						}
						break;
					case 1:
						break;
				}
			}
		});
		
		/// single button event ///////////////////////////////////
		
				/////////////////////////////////////////////////////////
				borrar.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View arg0) {
						
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "";
							}else{
								userInput = userInput.substring(0, userInput.length() - 1);; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "";
							}else{
								userInputXfixedpoint = userInputXfixedpoint.substring(0, userInputXfixedpoint.length() - 1);; 
							}
							mathExpressionXFixedPoint.setText(userInput);
						}
					}
				});
				one.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						if(userInput.equals("")){
							userInput = "1";
						}else{
							userInput = userInput + "1"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);
					}
					
				});				
				two.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "2";
						}else{
							userInput = userInput + "2"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
				
				three.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {

						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "3";
						}else{
							userInput = userInput + "3"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
						
				plus.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "+";
						}else{
							userInput = userInput + "+"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
				
				minus.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "-";
						}else{
							userInput = userInput + "-"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);				
					}
					
				});
				
				power.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "^";
						}else{
							userInput = userInput + "^"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);		
					}
					
				});
				
				exp.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "exp(";
						}else{
							userInput = userInput + "exp("; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});

				four.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "4";
						}else{
							userInput = userInput + "4"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
				
				five.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "5";
						}else{
							userInput = userInput + "5"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
				
				six.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "6";
						}else{
							userInput = userInput + "6"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
				
				times.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "*";
						}else{
							userInput = userInput + "*"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
				
				divide.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "/";
						}else{
							userInput = userInput + "/"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);		
					}
					
				});
				
				cos.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "cos(";
						}else{
							userInput = userInput + "cos("; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
				
				sin.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "sin(";
						}else{
							userInput = userInput + "sin("; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);		
					}
					
				});
				
				seven.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "7";
						}else{
							userInput = userInput + "7"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);				
					}
					
				});
				
				eight.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "8";
						}else{
							userInput = userInput + "8"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);				
					}
					
				});
				
				nine.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "9";
						}else{
							userInput = userInput + "9"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);		
					}
					
				});
				
				tan.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "tan(";
						}else{
							userInput = userInput + "tan("; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);		
					}
					
				});
				
				log.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "logs(";
						}else{
							userInput = userInput + "logs("; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);		
					}
					
				});
				
				ln.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "ln(";
						}else{
							userInput = userInput + "ln("; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
				
				del.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "";
						}else{
							userInput = ""; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);				
					}
					
				});
				
				zero.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "0";
						}else{
							userInput = userInput + "0"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
				
				dot.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = ".";
						}else{
							userInput = userInput + "."; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);				
					}
					
				});
				
				x.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "X";
						}else{
							userInput = userInput + "X"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);		
					}
					
				});
				
				openbracket.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "(";
						}else{
							userInput = userInput + "("; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
				
				closebracket.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = ")";
						}else{
							userInput = userInput + ")"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
				
				pi.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "π";
						}else{
							userInput = userInput + "π"; 
						}
						if (mathExpression.isFocused())
							mathExpression.setText(userInput);
						if (mathExpressionXFixedPoint.isFocused())
							mathExpressionXFixedPoint.setText(userInput);			
					}
					
				});
				
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
			
		}
		return super.onOptionsItemSelected(item);
	}

	private void createMethodList()
	{
		List<String> SpinnerList = new ArrayList<String>();
		SpinnerList.add("Incremental Search");
		SpinnerList.add("Bisection");
		SpinnerList.add("Fake Rule");
		SpinnerList.add("Fixed Point");
		SpinnerList.add("Newton");
		SpinnerList.add("Secant");
		SpinnerList.add("Multiple Roots");
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SpinnerList);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
	}
	
	protected void hideSoftKeyboard(EditText input) {
		 input.setInputType(0);
		 InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		 imm.hideSoftInputFromWindow(input.getWindowToken(), 0);

	}
	
	private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

}
