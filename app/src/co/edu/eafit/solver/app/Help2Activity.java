package co.edu.eafit.solver.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class Help2Activity extends Activity {

	private Button  btnMore;
	Spinner spinner;
	private String urlmethod;
	private TextView textHelp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help2);
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		spinner = (Spinner) findViewById(R.id.spinnerMethods);
		textHelp = (TextView)findViewById(R.id.txtAyuda);
		createMethodList();
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
		        int selected = position;
		        switch (selected)
		        {
		        	case 0:
		        		urlmethod = "https://github.com/halzate93/solver/wiki/Incremental-Search";
		        		textHelp.setText("Starting with a given continuous function f(x) we will find and interval where it crosses the x axis, this method doesn't always finds a solution, however it is the most easy and understandable of all the equation solving methods.\nWe must ensure that the function is continuous because it gives us the ability to assume that when f(xi) * f(xf) < 0 we have a sign change in f(x), therefore we crossed the x axis.\nEventhough f(x) can cross the x axis many times, we will point only the first one found in this iteration, if you want to find other roots, you can run the program again starting after the already found root.\n\nInput\n• x0: The starting x value.\n• dx: The change in x for each iteration, also known as step.\n• n: Max iteration count before exit.\n• f(x): A continuos function to evaluate y value for each x.");
		        		break;
		        	case 1:
		        		urlmethod = "https://github.com/halzate93/solver/wiki/Bisection";
		        		textHelp.setText("This method consist in to dividing the given interval that contains a root in two subintervals of the same size. Then, with the sign of the values corresponding to the evaluation of the function in the interval extremes and in the middle point, we decide wich interval contains the root to continue the proccess. The obtained succession converges to one of the roots of the equation.\n\nInput•\n xi: The starting x value of an interval that contains a root.\n• xs: The ending x value of an interval that contains a root.\n• tolerance: Max error before ending the proccess.\n• f(x): The function to be evaluated\n• n: Max iteration count before exit.");
		        		break;
		        	case 2:
		        		urlmethod = "https://github.com/halzate93/solver/wiki/Fake-Rule";
		        		textHelp.setText("");
		        		break;
		        	case 3:
		        		urlmethod = "https://github.com/halzate93/solver/wiki/Fixed-Point";
		        		textHelp.setText("This is the first one of the open methods, here we don't have an interval where we will look further, instead we have just an starting approximation that could or could not be near a root. The trick is having a function called g(x), where we will replace our last approximation and get a better one, hoping to move closer to a root.\nThe method will keep refining the approximation until the error is less than a given tolerance or a max count of iterations is reached. The better our g(x) function the faster will this method be.\n\nInput\n• x0: The starting x value, or the initial approximation.\n• n: Max iteration count before exit.\n• f(x): A continuos function to evaluate y value for each x.\n• g(x): A function in terms of x to get a better approximation each time.\n• t: The error allowed in this execution of the method, it could be absolute or relative.");
		        		break;
		        	case 4:
		        		urlmethod = "https://github.com/halzate93/solver/wiki/Newton's";
		        		textHelp.setText("Newton's method is a derivative from the Fixed Point method, with a preset g function, g(x) = x-f(x)/f'(x); this function g is a good one since Newton's convergence is quadratic this means that in each iteration Newton's will multiply the amount of correct digits by 2, getting a fast response.\nThe only and biggest drawback is that the user must provide the derivative of the original function, this isn't always an easy task.\nThe convergence of this method is cuadratic, it means that it will get fast to an acceptable approximation. But we have an exception, if f'(x) goes near to zero, the method will slow down, and if it reaches zero, we will have an exception.\n\nInput\n• x0: The starting x value, or the initial approximation.\n• n: Max iteration count before exit.\n• f(x): A continuos function to evaluate y value for each x.\n• f'(x): The first derivative of f.\n• t: The error allowed in this execution of the method, it could be absolute or relative.");
		        		break;
		        	case 5:
		        		urlmethod = "https://github.com/halzate93/solver/wiki/Secant";
		        		textHelp.setText("Secants method is a derivative from Fixed Point, as Newton's it has a supplementary G function to calculate approximations for the next xa value; but it works a little different, instead of using the first derivative for fx, we will use an approximation to it, exactly we will use secants instead of tangents, this method convergence is 1.7, it is almost as fast as Newton's but you don't have to derive.\nThe convergence of this method is almost cuadratic, exactly it is 1.7 it means that it will sometimes get fast to an acceptable approximation. But we have an exception, if f(x1) - f(x0) goes near to zero, the method will slow down, and if it reaches zero, we will have an exception.\n\nInput\n• x0: The starting x value, or the initial approximation.\n• x1: Another approximation to x value, or the complementary approximation.\n• n: Max iteration count before exit.\n• f(x): A continuos function to evaluate y value for each x.\n• t: The error allowed in this execution of the method, it could be absolute or relative.");
		        		break;
		        	case 6:
		        		urlmethod = "https://github.com/halzate93/solver/wiki/Multiple-Roots";
		        		textHelp.setText("Multiple Roots is a derivative of Fixed Point, it is intended to work where Newton's and Secant could fail, given an equation that can be expressed in the form f(x) = (x-xv)^m q(x) we can tell that xv is a multiple root.\nAs the root value can be repeated in some cases, we would have denominators aproaching zero, here is where this method comes handy, it finds a root of a given multiplicity using the g function g(x) = x - f(x)*f'(x) / (f'(x)^2 - f(x)*f''(x)).\nIn this method we have to give two derivatives, and it's convergence time isn't great, but it is useful when it comes to solve problems where Secant and Newton have failed.\n\nInput\n• x0: The starting x value, or the initial approximation.\n• n: Max iteration count before exit.\n• f(x): A continuos function to evaluate y value for each x.\n• f'(x): The first derivative of f.\n• f''(x): The second derivative of f.\n• t: The error allowed in this execution of the method, it could be absolute or relative.");
		        		break;
		        }
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }

		});
		btnMore = (Button)findViewById(R.id.btnhelpSearch);
		btnMore.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(urlmethod));
				startActivity(intent);
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
		spinner.setAdapter(adapter);
	}
}
