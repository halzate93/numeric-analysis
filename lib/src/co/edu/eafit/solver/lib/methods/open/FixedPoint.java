package co.edu.eafit.solver.lib.methods.open;

import java.util.ArrayList;
import java.util.Arrays;

import net.sourceforge.jeval.EvaluationException;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.functions.Function;
import co.edu.eafit.solver.lib.methods.Method;
import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;

/**
 * This is the first one of the open methods, here we don't have
 * an interval where we will look further, instead we have just an
 * starting approximation that could or could not be near a root.
 * 
 * The trick is having a function called g(x), where we will replace
 * our last approximation and get a better one, hoping to move closer
 * to a root.
 * 
 * The method will keep refining the approximation until the error is
 * less than a given tolerance or a max count of iterations is reached.
 * 
 * The better our g(x) function the faster will this method be.
 * @author halzate93
 * https://github.com/halzate93/solver/wiki/Fixed-Point
 */
public class FixedPoint extends Method {

	private float x0;
	private Function g;
	private float tolerance;

	@Override
	protected JSONObject solve() throws EvaluationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EParameter[] checkParameters() {
		ArrayList<EParameter> missing = new ArrayList<EParameter>();
		if(g == null) missing.add(EParameter.G);
		if(tolerance <= 0) missing.add(EParameter.Tolerance);
		missing.addAll(Arrays.asList(super.checkParameters()));
		return (EParameter[])missing.toArray();
	}

	@Override
	public EParameter[] getRequiredParameters(){
		EParameter[] required = new EParameter[]{EParameter.G, EParameter.Tolerance,
				EParameter.X0};
		ArrayList<EParameter> complete = new ArrayList<EParameter>(Arrays.asList(required));
		complete.addAll(Arrays.asList(super.getRequiredParameters()));
		return complete.toArray(required);
	}
	
	@Override
	public void setup(JSONObject parameters) throws InvalidParameterException{
		
	}
	
	public float getX0() {
		return x0;
	}

	public Function getG() {
		return g;
	}

	public float getTolerance() {
		return tolerance;
	}

}
