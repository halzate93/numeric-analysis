package co.edu.eafit.solver.lib.systemsolver;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.GaussianElimination;


public class EquationSystemSolver {
	private LinearSystemMethod method;
	
	public void setMethod(EEquationSystemMethod methodEnum){
		switch (methodEnum) {
		case GaussianElimination:
			method = new GaussianElimination();
			break;

		default:
			break;
		}
	}
	
	public void setParameters(JSONObject parameters) throws BadParameterException{
		method.setParameters(parameters);
	}
	
	public JSONObject solve() throws Exception{
		return method.solve();
	}
}
