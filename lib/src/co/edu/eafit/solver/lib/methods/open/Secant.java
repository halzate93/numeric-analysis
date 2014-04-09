package co.edu.eafit.solver.lib.methods.open;

import net.sourceforge.jeval.EvaluationException;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;

public class Secant extends FixedPoint {

	private float x0;
	private float denominator;
	
	
	@Override
	protected float getNextApproximation(JSONObject[] info) throws EvaluationException{
	/*	float response = x1 - getFunction().evaluate(x1) * (x1 - x0)/denominator;
		x0 = x1;
		
		return response;*/
		return 0f;
	}
	
	
	/*@Override
 	public void setup(JSONObject parameters) throws InvalidParameterException {
		
	}
	
	@Override
	public EParameter[] checkParameters(){
		
	}
	
	@Override
	public EParameter[] getRequiredParameters(){
		
	}*/
}
