package co.edu.eafit.solver.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelpActivity extends Activity {
	
	private Button  btnIncremental;
	private Button  btnBisection;
	private Button  btnFakerule;
	private Button  btnFixedpoint;
	private Button  btnNewton;
	private Button  btnSecant;
	private Button  btnMultipleroots;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		btnIncremental = (Button)findViewById(R.id.btnhelpSearch);
		btnBisection = (Button)findViewById(R.id.btnhelpBisection);
		btnFakerule = (Button)findViewById(R.id.btnhelpFakerule);
		btnFixedpoint = (Button)findViewById(R.id.btnhelpFixedpoint);
		btnNewton = (Button)findViewById(R.id.btnhelpNewton);
		btnSecant = (Button)findViewById(R.id.btnhelpSecant);
		btnMultipleroots = (Button)findViewById(R.id.btnhelpMultipleroots);
		
		btnIncremental.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String url = "https://github.com/halzate93/solver/wiki/Incremental-Search";
				final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
				startActivity(intent);
			}
		});
		
		btnBisection.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String url = "https://github.com/halzate93/solver/wiki/Bisection";
				final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
				startActivity(intent);
			}
		});
		
		btnFakerule.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String url = "https://github.com/halzate93/solver/wiki/Fake-Rule";
				final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
				startActivity(intent);
			}
		});
		btnFixedpoint.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String url = "https://github.com/halzate93/solver/wiki/Fixed-Point";
				final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
				startActivity(intent);
			}
		});
		btnNewton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String url = "https://github.com/halzate93/solver/wiki/Newton's";
				final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
				startActivity(intent);
			}
		});
		btnSecant.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String url = "https://github.com/halzate93/solver/wiki/Secant";
				final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
				startActivity(intent);
			}
		});
		btnMultipleroots.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				String url = "https://github.com/halzate93/solver/wiki/Secant";
				final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
				startActivity(intent);
			}
		});
		
	}

}
