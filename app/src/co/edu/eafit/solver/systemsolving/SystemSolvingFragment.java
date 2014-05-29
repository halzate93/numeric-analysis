package co.edu.eafit.solver.systemsolving;

import org.json.JSONException;
import org.json.JSONObject;

import co.edu.eafit.solver.MockupSolutionFragment;
import co.edu.eafit.solver.R;
import co.edu.eafit.solver.lib.systemsolver.EEquationSystemMethod;
import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.EquationSystemSolver;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.EPivotingStrategy;
import co.edu.eafit.solver.lib.systemsolver.iterative.ENorm;
import co.edu.eafit.solver.lib.systemsolver.iterative.EVectorErrorType;
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

public class SystemSolvingFragment extends Fragment {
	
	private JSONObject parameters;
	private EquationSystemSolver solver;
	
	public SystemSolvingFragment(){
		parameters = new JSONObject();
		solver = new EquationSystemSolver();
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
	        View view  = inflater.inflate(R.layout.system_solving, container, false);
	        
	        View root = view.findViewById(R.id.scroll_systemsolving).findViewById(R.id.grid);
	        initializeSpinners(root);
	        
	        initializeButtons(root);
	        
	        return view;
	}

	private void initializeButtons(final View view) {
		Button vectorAdd = (Button) view.findViewById(R.id.btn_addvector);
		vectorAdd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				TextView vectorField = (TextView) view.findViewById(R.id.txt_vector);
				String vectorString = vectorField.getText().toString();
				try{
                	double[] vector = MatrixUtility.parseVector(vectorString.trim());
                	String key = ((Spinner)view.findViewById(R.id.spn_vector)).getSelectedItem().toString();
                	parameters.put(key, MatrixUtility.vector2Json(vector));
                	vectorField.setText("");
                } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
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
				EEquationSystemMethod m = EEquationSystemMethod.valueOf(selected);
				try {
					TextView matrixField = (TextView) view.findViewById(R.id.txt_matrix);
					double[][] A = MatrixUtility.parseMatrix(matrixField.getText().toString().trim());
					parameters.put(ESystemSolvingParameter.A.toString(),
							MatrixUtility.matrix2Json(A));
					solver.setMethod(m);
					solver.setParameters(parameters);
					showResults(solver.solve());
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
			EVectorErrorType.valueOf(text);
			return;
		} catch (IllegalArgumentException e){
			
		}
		
		try{
			EPivotingStrategy.valueOf(text);
			return;
		} catch (IllegalArgumentException e){
			
		}
		
		try{
			ENorm.valueOf(text);
			return;
		} catch (IllegalArgumentException e){
			
		}
		throw new Exception("Invalid parameter for text");
	}

	private void initializeSpinners(View view) {
		
		ArrayAdapter<EEquationSystemMethod> methodAdapter = new ArrayAdapter<EEquationSystemMethod>(getActivity(),
		android.R.layout.simple_dropdown_item_1line, EEquationSystemMethod.values());
		
		Spinner methodSpinner = (Spinner) view.findViewById(R.id.spn_method);
		methodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		methodSpinner.setAdapter(methodAdapter);
		
		ESystemSolvingParameter[] vectors = new ESystemSolvingParameter[]{ESystemSolvingParameter.b, ESystemSolvingParameter.Initial};
		populateSpinner(R.id.spn_vector, vectors, view);
		
		ESystemSolvingParameter[] numbers = new ESystemSolvingParameter[]{ESystemSolvingParameter.Tolerance,
				ESystemSolvingParameter.Iterations, ESystemSolvingParameter.Lambda};
		populateSpinner(R.id.spn_number, numbers, view);
		
		ESystemSolvingParameter[] text = new ESystemSolvingParameter[]{ESystemSolvingParameter.Error,
				ESystemSolvingParameter.Norm, ESystemSolvingParameter.Strategy};
		populateSpinner(R.id.spn_text, text, view);
		
	}
	
	private void populateSpinner(int id, ESystemSolvingParameter[] values, View view){
		System.out.println(id);
		ArrayAdapter<ESystemSolvingParameter> adapter = new ArrayAdapter<ESystemSolvingParameter>(getActivity(),
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
