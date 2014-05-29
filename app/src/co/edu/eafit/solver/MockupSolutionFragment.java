package co.edu.eafit.solver;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MockupSolutionFragment extends Fragment {

	 public static MockupSolutionFragment newInstance(String input, String result) {
		 MockupSolutionFragment f = new MockupSolutionFragment();
		 
	     Bundle args = new Bundle();
	     args.putString("input", input);
	     args.putString("result", result);
	     f.setArguments(args);
	     
	     return f;
	 }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View view  = inflater.inflate(R.layout.mockup_results, container, false);
		 String result = getArguments().getString("result");
		 
		 TextView text = (TextView) view.findViewById(R.id.txt_mockup_results);
		 text.setText(result);
		 
		 return view;
	}
	
}
