package co.edu.eafit.solver.pc.interpreters;

import java.util.Scanner;

import net.sourceforge.jeval.EvaluationException;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.control.Solver;
import co.edu.eafit.solver.lib.methods.enums.EMethod;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;
import co.edu.eafit.solver.lib.methods.exceptions.MissingParametersException;

public abstract class Interpreter {
	private Scanner inputScanner;
	private Solver solver;
	
	public Interpreter(Scanner inputScanner, EMethod method) throws Exception {
		this.inputScanner = inputScanner;
		this.solver = new Solver();
		solver.setMethodPrototype(method);
	}
	
	/**
	 * Gets the required parameters from the input and configures the
	 * solver correctly. 
	 * @param inputScanner
	 * @return the parsed configuration for this method.
	 */
	public abstract JSONObject configure(Scanner inputScanner);
	
	public void run(){
		try {
			solver.setMethodParams(configure(inputScanner));
			JSONObject response = solver.solve();
			System.out.println(response.toString(2));
		} catch (MissingParametersException e) {
			e.printStackTrace();
		} catch (EvaluationException e) {
			e.printStackTrace();
		} catch (InvalidParameterException e) {
			e.printStackTrace();
		}
	}
	
}
