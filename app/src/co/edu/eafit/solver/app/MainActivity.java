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
	private EditText mathExpression;
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

		        		break;
		        	case 1:
		        		Toast.makeText(MainActivity.this, "Bisection", Toast.LENGTH_LONG).show();
		        		break;
		        	case 2:
		        		break;
		        	case 3:
		        		break;
		        	case 4:
		        		break;
		        	case 5:
		        		break;
		        }
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
		
		mathExpression = (EditText)findViewById(R.id.mathinput);
		mathExpression.setCursorVisible(false);
		hideSoftKeyboard(mathExpression);

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
		
		/// single button event ///////////////////////////////////
		
				/////////////////////////////////////////////////////////
				borrar.setOnClickListener(new OnClickListener(){
					@Override
					public void onClick(View arg0) {
						if(userInput.equals("")){
							userInput = "";
						}else{
							userInput = userInput.substring(0, userInput.length() - 1);; 
						}
						mathExpression.setText(userInput);
					}
				});
				one.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						hideSoftKeyboard(mathExpression);
						if(userInput.equals("")){
							userInput = "1";
						}else{
							userInput = userInput + "1"; 
						}
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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
						mathExpression.setText(userInput);				
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

}
