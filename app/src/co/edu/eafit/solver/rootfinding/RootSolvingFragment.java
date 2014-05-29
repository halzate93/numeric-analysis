package co.edu.eafit.solver.rootfinding;

import org.json.JSONException;
import org.json.JSONObject;

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
import co.edu.eafit.solver.MockupSolutionFragment;
import co.edu.eafit.solver.R;
import co.edu.eafit.solver.lib.rootfinding.NonLinearEquationSolver;
import co.edu.eafit.solver.lib.rootfinding.functions.Function;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EEquationSolvingMethod;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EErrorType;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EParameter;
import expr.SyntaxException;

public class RootSolvingFragment extends Fragment {

	private JSONObject parameters;
	private NonLinearEquationSolver solver;
	
	public RootSolvingFragment() {
		super();
		this.parameters = new JSONObject();
		this.solver = new NonLinearEquationSolver();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	        // Inflate the layout for this fragment
	        View view  = inflater.inflate(R.layout.root_finding, container, false);
	        
	        initializeSpinners(view);
	        
	        initializeButtons(view);
	        
	        return view;
	}

	private void initializeButtons(final View view) {
        
        Button numberAddButton = (Button) view.findViewById(R.id.btn_rootfinding_number);
        numberAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	TextView numberField = (TextView)view.findViewById(R.id.txt_rootfinding_number);
            	String numberString = numberField.getText().toString();
                try{
                	double n = Double.parseDouble(numberString);
                	String key = ((Spinner)view.findViewById(R.id.spn_rootfinding_number)).getSelectedItem().toString();
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
        
        Button functionAddButton = (Button) view.findViewById(R.id.btn_rootfinding_function);
        functionAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView functionField = (TextView)view.findViewById(R.id.txt_rootfinding_function);
                String functionString = functionField.getText().toString();
                try{
                	new Function(functionString);
                	String key = ((Spinner)view.findViewById(R.id.spn_systemsolving_matrix)).getSelectedItem().toString();
                	parameters.put(key, functionString);
                	functionField.setText("");
                }catch(SyntaxException e){
                	// TODO: handle the invalid function exception.
                } catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        Button clearButton = (Button) view.findViewById(R.id.btn_rootfinding_clear);
        clearButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				parameters = new JSONObject();				
			}
		});
        
        Button solveButton = (Button) view.findViewById(R.id.btn_rootfinding_solve);
        solveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String selected = ((Spinner)view.findViewById(R.id.spn_method)).getSelectedItem().toString();
				EEquationSolvingMethod m = EEquationSolvingMethod.valueOf(selected);
				try {
					String error = ((Spinner)view.findViewById(R.id.spn_rootfinding_error))
							.getSelectedItem().toString();
					parameters.put(EParameter.ErrorType.toString(), error);
					solver.setMethodPrototype(m, parameters);
					showResults(solver.solve());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	private void initializeSpinners(View view) {
		ArrayAdapter<EEquationSolvingMethod> methodAdapter = new ArrayAdapter<EEquationSolvingMethod>(getActivity(),
				android.R.layout.simple_dropdown_item_1line, EEquationSolvingMethod.values());
		
		Spinner methodSpinner = (Spinner) view.findViewById(R.id.spn_method);
		methodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		methodSpinner.setAdapter(methodAdapter);
		
		EParameter[] functions = new EParameter[]{EParameter.F, EParameter.G, EParameter.Df, EParameter.D2f};
		populateSpinner(R.id.spn_systemsolving_matrix, functions, view);
		
		EParameter[] numbers = new EParameter[] {EParameter.Dx, EParameter.LastX, EParameter.N, EParameter.Tolerance,
				EParameter.X0, EParameter.Xi, EParameter.Xs};
		populateSpinner(R.id.spn_rootfinding_number, numbers, view);
		
		ArrayAdapter<EErrorType> adapter = new ArrayAdapter<EErrorType>(getActivity(),
				android.R.layout.simple_spinner_item, EErrorType.values());
		
		Spinner errorSpinner = (Spinner) view.findViewById(R.id.spn_rootfinding_error);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		errorSpinner.setAdapter(adapter);
	}
	
	private void populateSpinner(int id, EParameter[] values, View view){
		System.out.println(id);
		ArrayAdapter<EParameter> adapter = new ArrayAdapter<EParameter>(getActivity(),
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
