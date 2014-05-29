package co.edu.eafit.solver.integrals;

import org.json.JSONException;
import org.json.JSONObject;

import co.edu.eafit.solver.MockupSolutionFragment;
import co.edu.eafit.solver.R;
import co.edu.eafit.solver.lib.integrals.EIntegrationMethod;
import co.edu.eafit.solver.lib.integrals.Integrator;
import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class IntegrationFragment extends Fragment {
	private JSONObject parameters;
	private Integrator integrator;
	
	public IntegrationFragment(){
		parameters = new JSONObject();
		integrator = new Integrator();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
	        View view  = inflater.inflate(R.layout.integration, container, false);
	        
	        View root = view.findViewById(R.id.scroll_systemsolving).findViewById(R.id.grid);
	        initializeSpinners(root);
	        
	        initializeButtons(root);
	        
	        return view;
	}

	private void initializeButtons(final View view) {
		
		Button solve = (Button) view.findViewById(R.id.btn_solve);
		solve.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String selected = ((Spinner)view.findViewById(R.id.spn_method)).getSelectedItem().toString();
				EIntegrationMethod m = EIntegrationMethod.valueOf(selected);
				try {
					TextView matrixField = (TextView) view.findViewById(R.id.txt_matrix);
					double[][] points = MatrixUtility.parseMatrix(matrixField.getText().toString().trim());
					parameters.put(EInterpolationParameter.Points.toString(),
							MatrixUtility.matrix2Json(points));
					
					integrator.setMethod(m);
					integrator.setParameters(parameters);
					showResults(integrator.integrate());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		
		ArrayAdapter<EIntegrationMethod> methodAdapter = new ArrayAdapter<EIntegrationMethod>(getActivity(),
		android.R.layout.simple_dropdown_item_1line, EIntegrationMethod.values());
		
		Spinner methodSpinner = (Spinner) view.findViewById(R.id.spn_method);
		methodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		methodSpinner.setAdapter(methodAdapter);
		
	}

	private void showResults(JSONObject results) throws JSONException {
		FragmentTransaction transaction = getActivity().getFragmentManager().beginTransaction();
		transaction.addToBackStack(null);
		transaction.replace(R.id.container, MockupSolutionFragment.newInstance(parameters.toString(2), results.toString(2)));
		transaction.commit();
	}
}
