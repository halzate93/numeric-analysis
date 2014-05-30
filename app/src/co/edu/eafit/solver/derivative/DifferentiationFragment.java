package co.edu.eafit.solver.derivative;

import org.json.JSONException;
import org.json.JSONObject;

import co.edu.eafit.solver.MockupSolutionFragment;
import co.edu.eafit.solver.R;
import co.edu.eafit.solver.lib.derivatives.Derivative;
import co.edu.eafit.solver.lib.derivatives.EDerivationRule;
import co.edu.eafit.solver.lib.derivatives.EDerivativeParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class DifferentiationFragment extends Fragment{
	private JSONObject parameters;
	private Derivative derivative;
	
	public DifferentiationFragment(){
		parameters = new JSONObject();
		derivative = new Derivative();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
	        View view  = inflater.inflate(R.layout.differentiation, container, false);
	        
	        View root = view.findViewById(R.id.scroll_systemsolving).findViewById(R.id.grid);
	        initializeSpinners(root);
	        
	        initializeButtons(root);
	        
	        return view;
	}

	private void initializeButtons(final View view) {
		
		Button numberAddButton = (Button) view.findViewById(R.id.btn_addnumber);
		numberAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	TextView numberField = (TextView)view.findViewById(R.id.txt_number);
            	String numberString = numberField.getText().toString();
                try{
                	double n = Double.parseDouble(numberString);
                	String key = ((Spinner)view.findViewById(R.id.spn_number)).getSelectedItem().toString();
                	parameters.put(key, n);
                	numberField.setText("");
                } catch (Exception e) {
                	Context context = getActivity().getApplicationContext();
                	CharSequence text = e.toString();
                	int duration = Toast.LENGTH_SHORT;

                	Toast toast = Toast.makeText(context, text, duration);
                	toast.show();
				} 
            }
        });
		
		Button solve = (Button) view.findViewById(R.id.btn_solve);
		solve.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String selected = ((Spinner)view.findViewById(R.id.spn_method)).getSelectedItem().toString();
				EDerivationRule m = EDerivationRule.valueOf(selected);
				try {
					TextView matrixField = (TextView) view.findViewById(R.id.txt_matrix);
					double[][] points = MatrixUtility.parseMatrix(matrixField.getText().toString().trim());
					parameters.put(EDerivativeParameter.Points.toString(),
							MatrixUtility.matrix2Json(points));
					parameters.put(EDerivativeParameter.Rule.toString(), m);
					derivative.setParameters(parameters);
					showResults(derivative.solve());
				} catch (Exception e) {
                	Context context = getActivity().getApplicationContext();
                	CharSequence text = e.toString();
                	int duration = Toast.LENGTH_SHORT;

                	Toast toast = Toast.makeText(context, text, duration);
                	toast.show();
				} 
			}
		});
		
		Button clearButton = (Button) view.findViewById(R.id.btn_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				parameters = new JSONObject();				
			}
		});
	}

	private void initializeSpinners(View view) {
		
		ArrayAdapter<EDerivationRule> methodAdapter = new ArrayAdapter<EDerivationRule>(getActivity(),
		android.R.layout.simple_dropdown_item_1line, EDerivationRule.values());
		
		Spinner methodSpinner = (Spinner) view.findViewById(R.id.spn_method);
		methodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		methodSpinner.setAdapter(methodAdapter);
		
		EDerivativeParameter[] numbers = new EDerivativeParameter[]{EDerivativeParameter.I};
		populateSpinner(R.id.spn_number, numbers, view);
		
	}
	
	private void populateSpinner(int id, EDerivativeParameter[] values, View view){
		System.out.println(id);
		ArrayAdapter<EDerivativeParameter> adapter = new ArrayAdapter<EDerivativeParameter>(getActivity(),
        		android.R.layout.simple_spinner_item, values);
        
        Spinner parameterSpinner = (Spinner) view.findViewById(id);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    parameterSpinner.setAdapter(adapter);
	}

	private void showResults(JSONObject results) throws JSONException {
		FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
		transaction.addToBackStack(null);
		transaction.replace(R.id.container, MockupSolutionFragment.newInstance(parameters.toString(2), results.toString(2)));
		transaction.commit();
	}
}
