package co.edu.eafit.solver.app;

import java.util.Scanner;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONException;
import org.json.JSONObject;

import co.edu.eafit.solver.lib.control.Solver;
import co.edu.eafit.solver.lib.methods.enums.EMethod;
import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class SolverActivity extends Activity {
	//
	//Creation of the Chart
	private GraphicalView mChartView;
	
	private XYSeries graphSeries ;
	private XYMultipleSeriesDataset xyMultipleSeriesDataset;
	
	private XYSeriesRenderer graphRenderer;
	private XYMultipleSeriesRenderer xyMultipleSeriesRenderer;	
	
	private XYSeries xyDataSeries;
	
	
	private void chartInitialization(){
		
		graphSeries = new XYSeries("Equation Graph");		
		xyMultipleSeriesDataset = new XYMultipleSeriesDataset();
		
		//xyMultipleSeriesDataset.addSeries(graphSeries);
		
		
		// set up render
		graphRenderer = new XYSeriesRenderer();
		graphRenderer.setColor(Color.BLUE);
		graphRenderer.setPointStyle(PointStyle.DIAMOND);
		graphRenderer.setFillPoints(true);
		graphRenderer.setLineWidth(3);
		graphRenderer.setDisplayChartValues(true);
		
		xyMultipleSeriesRenderer = new XYMultipleSeriesRenderer();
		
		xyMultipleSeriesRenderer.setChartTitle("Function Graph");
        xyMultipleSeriesRenderer.setXTitle("x - axis");
        xyMultipleSeriesRenderer.setYTitle("y - axis");
        xyMultipleSeriesRenderer.setShowGrid(true);
        xyMultipleSeriesRenderer.setInScroll(true);
        xyMultipleSeriesRenderer.setZoomButtonsVisible(true);
        
        xyMultipleSeriesRenderer.addSeriesRenderer(graphRenderer);
		
	}
	//End
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitleColor(Color.WHITE);
		setContentView(R.layout.activity_solver);		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		

		chartInitialization();
		
		if(xyDataSeries != null){
			xyMultipleSeriesDataset.removeSeries(xyDataSeries);
		}
		
		xyMultipleSeriesRenderer.setXAxisMin(-15);
	    xyMultipleSeriesRenderer.setXAxisMax(15);
	 
	    xyMultipleSeriesRenderer.setYAxisMin(-15);
	    xyMultipleSeriesRenderer.setYAxisMax(15);
	    
	    String forXAxis = getXAxis();
	    String forYAxis = getYAxis();
	    
	    xyDataSeries = graphSeries(forXAxis, forYAxis, graphSeries);
	    
	    xyMultipleSeriesDataset.addSeries(xyDataSeries);
	    
	    if (mChartView == null) {
			
	        LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
	        mChartView = ChartFactory.getLineChartView(getApplicationContext(), xyMultipleSeriesDataset, xyMultipleSeriesRenderer);
	        layout.addView(mChartView);

	      } else {
	        mChartView.repaint();
	      }
	    
	    //Solver
	    EMethod methodEnum = EMethod.values()[Integer.parseInt(getMethod())];
	    try {
			run(methodEnum);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void run(EMethod method) throws Exception {
		
		Solver s = new Solver();
		s.setMethodPrototype(method);
		
		JSONObject params = getConfiguration(s.getRequiredParameters(), method);
		
		boolean ready = false;
		while(!ready){
			try{
				s.setMethodParams(params);
				ready = true;
			} catch(InvalidParameterException e){
				e.printStackTrace();
			}
		}
		
		s.solve();
		System.out.println(s.getLastResult().toString(2));
	}
	
	public JSONObject getConfiguration(EParameter[] required, EMethod method) throws JSONException {
		JSONObject parameters = new JSONObject();
		
		for(EParameter p : required){
			System.out.println("Input value for " + p.toString() + ":");
			if (p.toString() == "F")
			parameters.put(p.toString(), getF());
			if (p.toString() == "Dx")
				parameters.put(p.toString(), getdx());
			if (p.toString() == "X0")
				parameters.put(p.toString(), getXo());
			if (p.toString() == "N")
				parameters.put(p.toString(), getiter());
			if (p.toString() == "G")
				parameters.put(p.toString(), getx());
			if (p.toString() == "Tolerance")
				parameters.put(p.toString(), gettol());
			if (p.toString() == "ErrorType")
				parameters.put(p.toString(), getdx());
			if (p.toString() == "Df")
				parameters.put(p.toString(), getfp());
			if (p.toString() == "Xi")
				parameters.put(p.toString(), getxi());
			if (p.toString() == "Xs")
				parameters.put(p.toString(), getxs());
			if (p.toString() == "LastX")
				parameters.put(p.toString(), getXo());
		}
		
		return parameters;
	}
	
	private XYSeries graphSeries(String xaxis, String yaxis, XYSeries graphSeries){
		
		String[] xPlot = convertStringToArray(xaxis);
		String[] yPlot = convertStringToArray(yaxis);
		
		for(int i = 0; i < xPlot.length; i++){
			
			double xGraphValue = Double.parseDouble(xPlot[i]);
    		double yGraphValue = Double.parseDouble(yPlot[i]);
    		
    		System.out.println("Values of X and Y " + xGraphValue + " " + yGraphValue);
    		
    		graphSeries.add(xGraphValue, yGraphValue);
		}
		
		return graphSeries;
	}
	
	private String[] convertStringToArray(String stringtoarray){
		
		stringtoarray = removeTailingComma(stringtoarray);
		String[] arrays = stringtoarray.split(",");
		return arrays;
	}
	
	private String removeTailingComma(String stringFormat){
		
		String trunkcateString = stringFormat.substring(0, stringFormat.length() - 1);
		return trunkcateString;
	}

	public String getXAxis(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("xaxis");
		}
		return topicid;
	}
	
	public String getYAxis(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("yaxis");
		}
		return topicid;
	}
	//Get Extras from MainActivity
	public String getMethod(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("method");
		}
		return topicid;
	}
	public String getF(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("f");
		}
		return topicid;
	}
	public String getXo(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("x0s");
		}
		return topicid;
	}
	public String getdx(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("dx");
		}
		return topicid;
	}
	public String getiter(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("iter");
		}
		return topicid;
	}
	public String gettol(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("tol");
		}
		return topicid;
	}
	public String getxi(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("xi");
		}
		return topicid;
	}
	public String getxs(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("xs");
		}
		return topicid;
	}
	public String getx(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("x");
		}
		return topicid;
	}
	public String getfp(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("fp");
		}
		return topicid;
	}
	public String getx1(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("x1");
		}
		return topicid;
	}
	public String getf2p(){
		String topicid = "";
		Intent myIntent = getIntent();
		Bundle mIntent = myIntent.getExtras();
		if(mIntent != null){
			topicid  = mIntent.getString("f2p");
		}
		return topicid;
	}
}
