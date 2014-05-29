package co.edu.eafit.solver.interpolation;

import org.json.JSONException;
import org.json.JSONObject;

import co.edu.eafit.solver.MockupSolutionFragment;
import co.edu.eafit.solver.R;
import co.edu.eafit.solver.lib.interpolation.EInterpolationMethod;
import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.interpolation.Interpolator;
import co.edu.eafit.solver.lib.interpolation.function.spline.ESplineType;
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

public class InterpolationFragment extends Fragment {
	private JSONObject parameters;
	private Interpolator solver;
	
	public InterpolationFragment(){
		parameters = new JSONObject();
		solver = new Interpolator();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
	        View view  = inflater.inflate(R.layout.interpolation, container, false);
	        
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
                } catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
		
		Button textAdd = (Button) view.findViewById(R.id.btn_text);
		textAdd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				TextView textField = (TextView) view.findViewById(R.id.txt_text);
				String text = textField.getText().toString();
				try{
                	checkTextParameter(text);
                	String key = ((Spinner)view.findViewById(R.id.spn_text)).getSelectedItem().toString();
                	parameters.put(key, text);
                	textField.setText("");
                } catch (Exception e) {
                	// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Button solve = (Button) view.findViewById(R.id.btn_solve);
		solve.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String selected = ((Spinner)view.findViewById(R.id.spn_method)).getSelectedItem().toString();
				EInterpolationMethod m = EInterpolationMethod.valueOf(selected);
				try {
					TextView matrixField = (TextView) view.findViewById(R.id.txt_matrix);
					double[][] points = MatrixUtility.parseMatrix(matrixField.getText().toString().trim());
					parameters.put(EInterpolationParameter.Points.toString(),
							MatrixUtility.matrix2Json(points));
					solver.setMethod(m);
					solver.setParameters(parameters);
					showResults(solver.interpolate());
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

	protected void checkTextParameter(String text) throws Exception{
		try{
			ESplineType.valueOf(text);
			return;
		} catch (IllegalArgumentException e){
			
		}
		throw new Exception("Invalid parameter for text");
	}

	private void initializeSpinners(View view) {
		
		ArrayAdapter<EInterpolationMethod> methodAdapter = new ArrayAdapter<EInterpolationMethod>(getActivity(),
		android.R.layout.simple_dropdown_item_1line, EInterpolationMethod.values());
		
		Spinner methodSpinner = (Spinner) view.findViewById(R.id.spn_method);
		methodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		methodSpinner.setAdapter(methodAdapter);
		
		EInterpolationParameter[] numbers = new EInterpolationParameter[]{EInterpolationParameter.Rule,
				EInterpolationParameter.FixedValue, EInterpolationParameter.X};
		populateSpinner(R.id.spn_number, numbers, view);
		
		EInterpolationParameter[] text = new EInterpolationParameter[]{EInterpolationParameter.SplineType};
		populateSpinner(R.id.spn_text, text, view);
		
	}
	
	private void populateSpinner(int id, EInterpolationParameter[] values, View view){
		System.out.println(id);
		ArrayAdapter<EInterpolationParameter> adapter = new ArrayAdapter<EInterpolationParameter>(getActivity(),
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
