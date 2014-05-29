package co.edu.eafit.solver.lib.integrals;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public class Integrator {

	private Integral method;
	
	public void setMethod(EIntegrationMethod e){
		switch (e) {
		case Trapezoid:
			method = new TrapezoidMethod();
			break;
		case ComposedTrapezoid:
			method = new ComposedTrapezoidMethod();
			break;
		case Simpson13:
			method = new Simpson13();
			break;
		case ComposedSimpson13:
			method = new Simpson13();
			break;
		case Simpson38:
			method = new Simpson38();
			break;
		default:
			break;
		}
	}
	
	public void setParameters(JSONObject parameters) throws BadParameterException{
		method.setParameters(parameters);
	}
	
	public JSONObject integrate() throws Exception{
		return method.interpolate();
	}
}
