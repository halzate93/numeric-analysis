package co.edu.eafit.solver.pc.interpreters;

import java.util.Scanner;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.methods.enums.EMethod;
import co.edu.eafit.solver.lib.methods.enums.EParameter;

public class IncrementalSearchInterpreter extends Interpreter {

	public IncrementalSearchInterpreter(Scanner inputScanner, EMethod method)
			throws Exception {
		super(inputScanner, method);
	}

	@Override
	public JSONObject configure(Scanner inputScanner) {
		System.out.println("Input f(x):");
		inputScanner.nextLine(); //Dismiss one line.
		String f = inputScanner.nextLine();
		System.out.println("Input dx:");
		float dx = inputScanner.nextFloat();
		System.out.println("Input x0:");
		float x0 = inputScanner.nextFloat();
		System.out.println("Input n:");
		int n = inputScanner.nextInt();
		
		JSONObject parameters = new JSONObject();
		parameters.put(EParameter.F.toString(), f);
		parameters.put(EParameter.Dx.toString(), dx);
		parameters.put(EParameter.X0.toString(), x0);
		parameters.put(EParameter.N.toString(), n);
		
		return parameters;
	}

}
