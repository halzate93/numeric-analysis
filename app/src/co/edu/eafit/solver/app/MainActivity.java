package co.edu.eafit.solver.app;

import java.util.*;

import co.edu.eafit.solver.lib.rootfinding.functions.Function;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.EvaluationHelper;
import net.sourceforge.jeval.Evaluator;
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
	
	private Evaluator evalutor;
	Spinner spinner;
	private TableLayout tableIncremental; 
	private TableLayout tableBisection;
	private TableLayout tableFixedpoint;
	private TableLayout tableNewton;
	private TableLayout tableSecant;
	private TableLayout tableMultipleroots;
	private int actualMethod;
	private EditText mathExpression;
	private EditText x0;
	private EditText d0;
	private EditText ite;
	//bisection
	private EditText xi;
	private EditText xs;
	private EditText iteb;
	private EditText tolb;
	//false position
	private EditText xif;
	private EditText xsf;
	private EditText itef;
	private EditText tolf;
	//fixed point
	private EditText itep;
	private EditText tolp;
	//newton
	private EditText x0n;
	private EditText iten;
	private EditText toln;
	//secant
	private EditText x0se;
	private EditText x1se;
	private EditText ites;
	private EditText tolse;
	//multiple roots
	private EditText x0m;
	private EditText item;
	private EditText tolm;
	
	private EditText mathExpressionXFixedPoint;
	
	private EditText mathExpressionFpNewton;
	private EditText mathExpressionFpMultipleroots;
	private EditText mathExpressionF2pMultipleroots;
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
    private String userInputFpnewton = "";
    private String userInputFpMultipleroots = "";
    private String userInputF2pMultipleroots = "";
    private Button borrar;
    
    private ArrayList<String> allSeries;
    private double powerNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			if(!userInput.equals("")){
				userInput = "";
			}
		evalutor = new Evaluator();		
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
		        		tableMultipleroots.setVisibility(View.INVISIBLE);
		        		break;
		        	case 1:
		        		Toast.makeText(MainActivity.this, "Bisection", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.INVISIBLE);
		        		tableBisection.setVisibility(View.VISIBLE);
		        		tableFixedpoint.setVisibility(View.INVISIBLE);
		        		tableNewton.setVisibility(View.INVISIBLE);
		        		tableSecant.setVisibility(View.INVISIBLE);
		        		tableMultipleroots.setVisibility(View.INVISIBLE);
		        		break;
		        	case 2:
		        		Toast.makeText(MainActivity.this, "False Position", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.INVISIBLE);
		        		tableBisection.setVisibility(View.VISIBLE);
		        		tableFixedpoint.setVisibility(View.INVISIBLE);
		        		tableNewton.setVisibility(View.INVISIBLE);
		        		tableSecant.setVisibility(View.INVISIBLE);
		        		tableMultipleroots.setVisibility(View.INVISIBLE);
		        		break;
		        	case 3:
		        		Toast.makeText(MainActivity.this, "Fixed Point", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.INVISIBLE);
		        		tableBisection.setVisibility(View.INVISIBLE);
		        		tableFixedpoint.setVisibility(View.VISIBLE);
		        		tableNewton.setVisibility(View.INVISIBLE);
		        		tableSecant.setVisibility(View.INVISIBLE);
		        		tableMultipleroots.setVisibility(View.INVISIBLE);
		        		break;
		        	case 4:
		        		Toast.makeText(MainActivity.this, "Newton", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.INVISIBLE);
		        		tableBisection.setVisibility(View.INVISIBLE);
		        		tableFixedpoint.setVisibility(View.INVISIBLE);
		        		tableNewton.setVisibility(View.VISIBLE);
		        		tableSecant.setVisibility(View.INVISIBLE);
		        		tableMultipleroots.setVisibility(View.INVISIBLE);
		        		break;
		        	case 5:
		        		Toast.makeText(MainActivity.this, "Secant", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.INVISIBLE);
		        		tableBisection.setVisibility(View.INVISIBLE);
		        		tableFixedpoint.setVisibility(View.INVISIBLE);
		        		tableNewton.setVisibility(View.INVISIBLE);
		        		tableSecant.setVisibility(View.VISIBLE);
		        		tableMultipleroots.setVisibility(View.INVISIBLE);
		        		break;
		        	case 6:
		        		Toast.makeText(MainActivity.this, "Multiple Roots", Toast.LENGTH_LONG).show();
		        		actualMethod = selected;
		        		tableIncremental.setVisibility(View.INVISIBLE);
		        		tableBisection.setVisibility(View.INVISIBLE);
		        		tableFixedpoint.setVisibility(View.INVISIBLE);
		        		tableNewton.setVisibility(View.INVISIBLE);
		        		tableSecant.setVisibility(View.INVISIBLE);
		        		tableMultipleroots.setVisibility(View.VISIBLE);
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
		tableMultipleroots = (TableLayout)findViewById(R.id.TableMultipleroots);
		
		mathExpression = (EditText)findViewById(R.id.mathinput);
		hideSoftKeyboard(mathExpression);
		
		x0 = (EditText)findViewById(R.id.etXo);
		d0 = (EditText)findViewById(R.id.etDelta);
		ite = (EditText)findViewById(R.id.etIterations);
		//bisection
		xi = (EditText)findViewById(R.id.etXi);
		xs = (EditText)findViewById(R.id.etXs);
		iteb = (EditText)findViewById(R.id.etIterationsb);
		tolb = (EditText)findViewById(R.id.etTolb);
		//false position
		xif = (EditText)findViewById(R.id.etXi);
		xsf = (EditText)findViewById(R.id.etXs);
		itef = (EditText)findViewById(R.id.etIterationsb);
		tolf = (EditText)findViewById(R.id.etTolb);
		//fixed point
		mathExpressionXFixedPoint = (EditText)findViewById(R.id.etX);
		hideSoftKeyboard(mathExpressionXFixedPoint);
		hideSoftKeyboard(mathExpressionXFixedPoint);
		itep = (EditText)findViewById(R.id.etIterationsf);
		tolp = (EditText)findViewById(R.id.etTolf);
		//newton
		x0n = (EditText)findViewById(R.id.etXon);
		iten = (EditText)findViewById(R.id.etIterationsn);
		toln = (EditText)findViewById(R.id.etToln);
		mathExpressionFpNewton = (EditText)findViewById(R.id.etFpn);
		hideSoftKeyboard(mathExpressionFpNewton);
		//secant
		x0se = (EditText)findViewById(R.id.etXos);
		x1se = (EditText)findViewById(R.id.etX1s);
		ites = (EditText)findViewById(R.id.etIterationss);
		tolse = (EditText)findViewById(R.id.etTols);
		//multiple roots
		x0m = (EditText)findViewById(R.id.etXor);
		item = (EditText)findViewById(R.id.etIterationsr);
		tolm = (EditText)findViewById(R.id.etTolr);
		mathExpressionFpMultipleroots = (EditText)findViewById(R.id.etFpr);
		hideSoftKeyboard(mathExpressionFpMultipleroots);		
		mathExpressionF2pMultipleroots = (EditText)findViewById(R.id.etFp2r);
		hideSoftKeyboard(mathExpressionF2pMultipleroots);
		
		
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
		
		mathExpression.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				hideSoftKeyboard(mathExpression);
			}
		});
		
		Button solve  = (Button)findViewById(R.id.calculatedBbutton);
		solve.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				
				String x0s = "";
				String dxs = "";
				String iters = "";
				String tols = "";
				String xis = "";
				String xss = "";
				String xst = "";
				String fps = "";
				String x1s = "";
				String f2ps = "";
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
						x0s = x0.getText().toString();
						dxs = d0.getText().toString();
						iters = ite.getText().toString();
						tols = "";
						xis = "";
						xss = "";
						xst = "";
						fps = "";
						x1s = "";
						f2ps = "";
						break;
					case 1:
						x0s = "";
						dxs = "";
						iters = iteb.getText().toString();
						tols = tolb.getText().toString();
						xis = xi.getText().toString();
						xss = xs.getText().toString();
						xst = "";
						fps = "";
						x1s = "";
						f2ps = "";
						break;
					case 2:
						x0s = "";
						dxs = "";
						iters = itef.getText().toString();
						tols = tolf.getText().toString();
						xis = xif.getText().toString();
						xss = xsf.getText().toString();
						xst = "";
						fps = "";
						x1s = "";
						f2ps = "";
						break;
					case 3:
						x0s = "";
						dxs = "";
						iters = itep.getText().toString();
						tols = tolp.getText().toString();
						xis = "";
						xss = "";
						xst = mathExpressionXFixedPoint.getText().toString();
						fps = "";
						x1s = "";
						f2ps = "";
						break;
					case 4:
						x0s = x0n.getText().toString();
						dxs = "";
						iters = iten.getText().toString();
						tols = toln.getText().toString();
						xis = "";
						xss = "";
						xst = "";
						fps = mathExpressionFpNewton.getText().toString();
						x1s = "";
						f2ps = "";
						break;
					case 5:
						x0s = x0se.getText().toString();
						dxs = "";
						iters = ites.getText().toString();
						tols = tolse.getText().toString();
						xis = "";
						xss = "";
						xst = "";
						fps = "";
						x1s = x1se.getText().toString();
						f2ps = "";
						break;
					case 6:
						x0s = x0m.getText().toString();
						dxs = "";
						iters = item.getText().toString();
						tols = tolm.getText().toString();
						xis = "";
						xss = "";
						xst = "";
						fps = mathExpressionFpMultipleroots.getText().toString();
						x1s = "";
						f2ps = mathExpressionF2pMultipleroots.getText().toString();
						break;
				}
				//Evaluate ecuation for get axis X and Y
				evalutor.clearFunctions();
				String xAxis = "";
				String yAxis = "";
				userInput = mathExpression.getText().toString();
				
				if(userInput.equals("")){
					
					Toast.makeText(MainActivity.this, "Input field must be filled", Toast.LENGTH_LONG).show();
					return;
				}
                
				try {
					
					// replace some string with new ones in the expression string
					userInput = EvaluationHelper.replaceAll(userInput, "ln", "log");
					userInput = EvaluationHelper.replaceAll(userInput, "log10", "logs");
					userInput = EvaluationHelper.replaceAll(userInput, "π", "3.14159265359");
					if(!userInput.contains("x")){
						
						Toast.makeText(MainActivity.this, "Equation must contain x variable which is used for solving equations and graph plotting", Toast.LENGTH_LONG).show();
						return;
					}
					
	                
	                if(!isXLog(userInput) && userInput.contains("logs"))
	                {
	                	userInput = replaceCaratLogWithMath(userInput);
						System.out.println("inner test = : " + userInput);
	                }
					
	                if(!isXPower(userInput) && userInput.contains("^")){
						userInput = replaceCaratWithMath(userInput);
						System.out.println("inner test = : " + userInput);
					}
					
					
					System.out.println("New Expression is = : " + userInput);
					
					if(userInput.contains("x")){
						
						allSeries = new ArrayList<String>();
						allSeries.clear();						
						
						String threeString = "";						
						double powerOfValue = 0;
						
						for(int i = -10; i < 10; i++){						
							
							
							if(userInput.contains("x^")){
								
								int indexs = userInput.indexOf("^");
								threeString = userInput.substring(indexs - 1, indexs + 2);;
													
								powerNumber = Double.parseDouble(String.valueOf(userInput.charAt(indexs + 1)));	
								
								powerOfValue = Math.pow(i, powerNumber);
								
								System.out.println("math pow = : " + powerOfValue);
								
								evalutor.putVariable("p", String.valueOf(powerOfValue));
								
								userInput = EvaluationHelper.replaceAll(userInput, threeString, "#{p}");
								
								System.out.println("Value of userinput = : " + userInput + " " + "#{p}");
															
							}
							if(userInput.contains("#{p}")){
								
								powerOfValue = Math.pow(i, powerNumber);
								evalutor.putVariable("p", String.valueOf(powerOfValue));
							}
				
							/////////////////////////////////////////////////////////////////
							
							evalutor.putVariable("a", String.valueOf(i));							
							//System.out.println("Variable = : " + "#{a}"wink;
							userInput = EvaluationHelper.replaceAll(userInput, "exp", "ep");
							userInput = EvaluationHelper.replaceAll(userInput, "x", "#{a}");							
							userInput = EvaluationHelper.replaceAll(userInput, "ep", "exp");
							System.out.println("New Expression =  : " + userInput);								
							
							String resultInString = evalutor.evaluate(userInput);
							
							
							//System.out.println("Real Values =  : " + resultInString);		
							
							xAxis = xAxis + String.valueOf(i) + ",";
							yAxis = yAxis + resultInString + ",";
							
							//evalutor.clearVariables();
													
						}
						System.out.println("Size of x =  : " + xAxis + "size of y : " +   yAxis);
						evalutor.clearFunctions();
						
					}else{
						
						Toast.makeText(MainActivity.this, "Please enter x in the input field function to plot a graph", Toast.LENGTH_SHORT).show();
						evalutor.clearFunctions();						
						return;
					}
					
					
				} catch (EvaluationException e) {
					// TODO Auto-generated catch block
					Toast.makeText(MainActivity.this, "Make sure the equation is correct and add multiplication sign where necessary", Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
				if(xAxis.equals("") || xAxis.equals("")){
					Toast.makeText(MainActivity.this, "Make sure the equation is correct and add multiplication sign where necessary", Toast.LENGTH_LONG).show();
					return;
				}
				evalutor.clearFunctions();
				//mathExpression.setText("");
				Intent mIntent = new Intent(MainActivity.this, SolverActivity.class);
    			mIntent.putExtra("xaxis", xAxis);
    			mIntent.putExtra("yaxis", yAxis);
    			//method type
    			mIntent.putExtra("method", String.valueOf(actualMethod));
    			//method's vars
    			mIntent.putExtra("f", mathExpression.getText().toString());
				mIntent.putExtra("x0", x0s);
    			mIntent.putExtra("dx", dxs);
    			mIntent.putExtra("iter", iters);
    			mIntent.putExtra("tol", tols);
    			mIntent.putExtra("xi", xis);
    			mIntent.putExtra("xs", xss);
    			mIntent.putExtra("x", xst);
    			mIntent.putExtra("fp", fps);
    			mIntent.putExtra("x1", x1s);
    			mIntent.putExtra("f2p", f2ps);
    			startActivity(mIntent);
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
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "";
							}else{
								userInputFpnewton = userInputFpnewton.substring(0, userInputFpnewton.length() - 1);; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots.substring(0, userInputFpMultipleroots.length() - 1);; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots.substring(0, userInputF2pMultipleroots.length() - 1);; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}
					}
				});
				one.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "1";
							}else{
								userInput = userInput + "1"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "1";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "1"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "1";
							}else{
								userInputFpnewton = userInputFpnewton + "1"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "1";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "1"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "1";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "1"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}
					}
					
				});				
				two.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "2";
							}else{
								userInput = userInput + "2"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "2";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "2"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "2";
							}else{
								userInputFpnewton = userInputFpnewton + "2"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "2";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "2"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "2";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "2"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}		
					}
					
				});
				
				three.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "3";
							}else{
								userInput = userInput + "3"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "3";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "3"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "3";
							}else{
								userInputFpnewton = userInputFpnewton + "3"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "3";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "3"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "3";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "3"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}			
					}
					
				});
						
				plus.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "+";
							}else{
								userInput = userInput + "+"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "+";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "+"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "+";
							}else{
								userInputFpnewton = userInputFpnewton + "+"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "+";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "+"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "+";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "+"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}			
					}
				});
				
				minus.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "-";
							}else{
								userInput = userInput + "-"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "-";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "-"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "-";
							}else{
								userInputFpnewton = userInputFpnewton + "-"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "-";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "-"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "-";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "-"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}		
					}
					
				});
				
				power.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "^";
							}else{
								userInput = userInput + "^"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "^";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "^"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "^";
							}else{
								userInputFpnewton = userInputFpnewton + "^"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "^";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "^"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "^";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "^"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}		
					}
					
				});
				
				exp.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "exp(";
							}else{
								userInput = userInput + "exp("; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "exp(";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "exp("; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "exp(";
							}else{
								userInputFpnewton = userInputFpnewton + "exp("; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "exp(";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "exp("; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "exp(";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "exp("; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}		
					}
					
				});

				four.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "4";
							}else{
								userInput = userInput + "4"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "4";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "4"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "4";
							}else{
								userInputFpnewton = userInputFpnewton + "4"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "4";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "4"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "4";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "4"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}			
					}
					
				});
				
				five.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "5";
							}else{
								userInput = userInput + "5"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "5";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "5"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "5";
							}else{
								userInputFpnewton = userInputFpnewton + "5"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "5";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "5"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "5";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "5"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}		
					}
					
				});
				
				six.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "6";
							}else{
								userInput = userInput + "6"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "6";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "6"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "6";
							}else{
								userInputFpnewton = userInputFpnewton + "6"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "6";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "6"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "6";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "6"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}		
					}
					
				});
				
				times.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "*";
							}else{
								userInput = userInput + "*"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "*";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "*"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "*";
							}else{
								userInputFpnewton = userInputFpnewton + "*"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "*";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "*"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "*";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "*"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}			
					}
					
				});
				
				divide.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "/";
							}else{
								userInput = userInput + "/"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "/";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "/"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "/";
							}else{
								userInputFpnewton = userInputFpnewton + "/"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "/";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "/"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "/";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "/"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}		
					}
					
				});
				
				cos.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "cos(";
							}else{
								userInput = userInput + "cos("; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "cos(";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "cos("; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "cos(";
							}else{
								userInputFpnewton = userInputFpnewton + "cos("; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "cos(";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "cos("; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "cos(";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "cos("; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}		
					}
					
				});
				
				sin.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "sin(";
							}else{
								userInput = userInput + "sin("; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "sin(";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "sin("; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "sin(";
							}else{
								userInputFpnewton = userInputFpnewton + "sin("; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "sin(";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "sin("; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "sin(";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "sin("; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}		
					}
					
				});
				
				seven.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "7";
							}else{
								userInput = userInput + "7"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "7";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "7"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "7";
							}else{
								userInputFpnewton = userInputFpnewton + "7"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "7";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "7"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "7";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "7"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}				
					}
					
				});
				
				eight.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "8";
							}else{
								userInput = userInput + "8"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "8";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "8"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "8";
							}else{
								userInputFpnewton = userInputFpnewton + "8"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "8";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "8"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "8";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "8"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}				
					}
					
				});
				
				nine.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "9";
							}else{
								userInput = userInput + "9"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "9";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "9"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "9";
							}else{
								userInputFpnewton = userInputFpnewton + "9"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "9";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "9"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "9";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "9"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}		
					}
					
				});
				
				tan.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "tan(";
							}else{
								userInput = userInput + "tan("; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "tan(";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "tan("; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "tan(";
							}else{
								userInputFpnewton = userInputFpnewton + "tan("; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "tan(";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "tan("; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "tan(";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "tan("; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}		
					}
					
				});
				
				log.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "logs(";
							}else{
								userInput = userInput + "logs("; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "logs(";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "logs("; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "logs(";
							}else{
								userInputFpnewton = userInputFpnewton + "logs("; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "logs(";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "logs("; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "logs(";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "logs("; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}	
					}
					
				});
				
				ln.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "ln(";
							}else{
								userInput = userInput + "ln("; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "ln(";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "ln("; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "ln(";
							}else{
								userInputFpnewton = userInputFpnewton + "ln("; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "ln(";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "ln("; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "ln(";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "ln("; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}			
					}
					
				});
				
				del.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "";
							}else{
								userInput =  ""; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "";
							}else{
								userInputXfixedpoint =  ""; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "";
							}else{
								userInputFpnewton =  ""; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "";
							}else{
								userInputFpMultipleroots =  ""; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "";
							}else{
								userInputF2pMultipleroots =  ""; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}				
					}
					
				});
				
				zero.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "0";
							}else{
								userInput = userInput + "0"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "0";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "0"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "0";
							}else{
								userInputFpnewton = userInputFpnewton + "0"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "0";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "0"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "0";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "0"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}			
					}
					
				});
				
				dot.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = ".";
							}else{
								userInput = userInput + "."; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = ".";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "."; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = ".";
							}else{
								userInputFpnewton = userInputFpnewton + "."; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = ".";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "."; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = ".";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "."; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}				
					}
					
				});
				
				x.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "x";
							}else{
								userInput = userInput + "x"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "x";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "x"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "x";
							}else{
								userInputFpnewton = userInputFpnewton + "x"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "x";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "x"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "x";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "x"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}		
					}
					
				});
				
				openbracket.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "(";
							}else{
								userInput = userInput + "("; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "(";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "("; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "(";
							}else{
								userInputFpnewton = userInputFpnewton + "("; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "(";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "("; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "(";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "("; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}			
					}
					
				});
				
				closebracket.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = ")";
							}else{
								userInput = userInput + ")"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = ")";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + ")"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = ")";
							}else{
								userInputFpnewton = userInputFpnewton + ")"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = ")";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + ")"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = ")";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + ")"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}			
					}
					
				});
				
				pi.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						hideSoftKeyboard(mathExpressionXFixedPoint);
						hideSoftKeyboard(mathExpressionFpNewton);
						hideSoftKeyboard(mathExpressionFpMultipleroots);
						hideSoftKeyboard(mathExpressionF2pMultipleroots);
						if (mathExpression.isFocused())
						{
							if(userInput.equals("")){
								userInput = "π";
							}else{
								userInput = userInput + "π"; 
							}
							mathExpression.setText(userInput);
						}
							
						if (mathExpressionXFixedPoint.isFocused())
						{
							if(userInputXfixedpoint.equals("")){
								userInputXfixedpoint = "π";
							}else{
								userInputXfixedpoint = userInputXfixedpoint + "π"; 
							}
							mathExpressionXFixedPoint.setText(userInputXfixedpoint);
						}
						
						if (mathExpressionFpNewton.isFocused())
						{
							if(userInputFpnewton.equals("")){
								userInputFpnewton = "π";
							}else{
								userInputFpnewton = userInputFpnewton + "π"; 
							}
							mathExpressionFpNewton.setText(userInputFpnewton);
						}
						if (mathExpressionFpMultipleroots.isFocused())
						{
							if(userInputFpMultipleroots.equals("")){
								userInputFpMultipleroots = "π";
							}else{
								userInputFpMultipleroots = userInputFpMultipleroots + "π"; 
							}
							mathExpressionFpMultipleroots.setText(userInputFpMultipleroots);
						}
						if (mathExpressionF2pMultipleroots.isFocused())
						{
							if(userInputF2pMultipleroots.equals("")){
								userInputF2pMultipleroots = "π";
							}else{
								userInputF2pMultipleroots = userInputF2pMultipleroots + "π"; 
							}
							mathExpressionF2pMultipleroots.setText(userInputF2pMultipleroots);
						}
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
			Intent intent = new Intent(MainActivity.this, HelpActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}

	private void createMethodList()
	{
		List<String> SpinnerList = new ArrayList<String>();
		SpinnerList.add("Incremental Search");
		SpinnerList.add("Bisection");
		SpinnerList.add("False Position");
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

	private boolean isXPower(String expression){

		 //int powerIndex = expression.indexOf("x^"wink;
		 if(expression.contains("x^")){
		 return true;
		 } 
		 return false;
	}
	private boolean isXLog(String expression){

		 //int powerIndex = expression.indexOf("x^"wink;
		 if(expression.contains("xlogs")){
		 return true;
		 } 
		 return false;
	}
	private String replaceCaratWithMath(String expression){

		 String newString = "";
		 int powerIndex = expression.indexOf("^");
		 String powerOfString = expression.substring(powerIndex - 1, powerIndex + 2); 

		 double powerNumber = Double.parseDouble(String.valueOf(expression.charAt(powerIndex + 1))); 
		 double baseNumber = Double.parseDouble(String.valueOf(expression.charAt(powerIndex - 1)));

		 double rootOfPower = Math.pow(baseNumber, powerNumber); 
		 newString = expression.replace(powerOfString, String.valueOf(rootOfPower));


		 return newString; 
	 }
	private String replaceCaratLogWithMath(String expression){

		 String newString = "";
		 String number = expression.substring(expression.indexOf("logs(")+5,expression.indexOf(")"));

		 double numberd = Double.parseDouble(number); 

		 double lognumber = Math.log10(numberd); 
		 newString = String.valueOf(lognumber);


		 return newString;
	 }

}
