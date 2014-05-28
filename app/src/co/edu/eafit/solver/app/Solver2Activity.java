package co.edu.eafit.solver.app;

import java.util.Arrays;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class Solver2Activity extends Activity {

	//Global vars
    int actualMethod = 0;
    String[] A;
    String[] b;
    int n = 0;
    String strategy = "";
    String[] Xi;
    Double tolerance = 0.0;
    int iterations = 0;
    Double lambda = 0.0;
    Double x = 0.0;
    String[] Xn;
    String[] Fxn;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solver2);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		actualMethod = getMethod();
		System.out.println("Actual Method: "+actualMethod);
		A = getA();
		System.out.println("A Matrix: "+Arrays.toString(A));
		b = getb();
		System.out.println("b Matrix: "+Arrays.toString(b));
		n = getn();
		System.out.println("Size of Matrix: "+n);
		strategy = getstrategy();
		System.out.println("Pivot Strategy: "+strategy);
		Xi = getXi();
		System.out.println("Initial Values Xi: "+Arrays.toString(Xi));
		tolerance = gettolerance();
		System.out.println("Tolerance: "+tolerance);
		iterations = getiterations();
		System.out.println("Iterations: "+iterations);
		lambda = getlambda();
		System.out.println("Lambda: "+lambda);
		x = getx();
		System.out.println("X value for evaluation: "+x);
		Xn = getXn();
		System.out.println("Xn values: "+Arrays.toString(Xn));
		Fxn = getFxn();
		System.out.println("F(Xn) values: "+Arrays.toString(Fxn));
		
	}
	
	public int getMethod(){
		int topicid = 0;
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			try
			{
				topicid  = Integer.parseInt(mIntent.getString("method"));
			}
			catch(Exception e){return topicid;}
		}
		return topicid;
	}
	
	public String[] getA(){
		String[] a;
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null)
		{
			try
			{
				return a = mIntent.getString("A").split(",");
			}
			catch(Exception e){return a = null;}
		}
		else
		{
			return a = null;
		}
	}
	
	public String[] getb(){
		String[] b;
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null)
		{
			try
			{
				return b = mIntent.getString("b").split(",");
			}
			catch(Exception e){return b = null;}
		}
		else
		{
			return b = null;
		}
	}
	
	public int getn(){
		int n = 0;
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null)
		{
			try
			{
				n = Integer.parseInt(mIntent.getString("n"));
			}
			catch(Exception e){return n;}
			
		}
		return n;
	}
	
	public String getstrategy(){
		String strategy = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null)
		{
			try
			{
				strategy = mIntent.getString("strategy");
			}
			catch(Exception e){return strategy;}
		}
		return strategy;
	}
	
	public String[] getXi(){
		String[] Xi = null;
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null)
		{
			try
			{
				Xi = mIntent.getString("Xi").split(",");
			}
			catch(Exception e){return Xi;}
		}
		return Xi;
	}
	
	public Double gettolerance(){
		Double tolerance = 0.0;
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null)
		{
			try
			{
				tolerance = Double.parseDouble(mIntent.getString("tolerance"));
			}
			catch(Exception e){return tolerance;}
		}
		return tolerance;
	}
	
	public int getiterations(){
		int iterations = 0;
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null)
		{
			try
			{
				iterations = Integer.parseInt(mIntent.getString("iterations"));
			}
			catch(Exception e){return iterations;}
		}
		return iterations;
	}
	
	public Double getlambda(){
		Double lambda = 0.0;
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null)
		{
			try
			{
				lambda = Double.parseDouble(mIntent.getString("lambda"));
			}
			catch(Exception e){return lambda;}
		}
		return lambda;
	}
	
	public Double getx(){
		Double x = 0.0;
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null)
		{
			try
			{
				x = Double.parseDouble(mIntent.getString("x"));
			}
			catch(Exception e){return x;}
		}
		return x;
	}
	
	public String[] getXn(){
		String[] Xn = null;
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null)
		{
			try
			{
				Xn = mIntent.getString("Xn").split(",");
			}
			catch(Exception e){return Xn;}
		}
		return Xn;
	}
	
	public String[] getFxn(){
		String[] Fxn = null;
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null)
		{
			try
			{
				Fxn = mIntent.getString("Fxn").split(",");
			}
			catch(Exception e){return Fxn;}
		}
		return Fxn;
	}

}
