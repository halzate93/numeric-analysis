package co.edu.eafit.solver.lib.methods.open;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;

import expr.SyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import co.edu.eafit.solver.lib.functions.Function;
import co.edu.eafit.solver.lib.methods.enums.EFailureCauses;
import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.enums.EResultProcess;
import co.edu.eafit.solver.lib.methods.enums.EResults;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;

/**
 * Multiple Roots is a derivative of Fixed Point, it is intended to work where Newton's and
 * Secant could fail, given an equation that can be expressed in the form f(x) = (x-xv)^m q(x)
 * we can tell that xv is a multiple root.
 * 
 * As the root value can be repeated in some cases, we would have denominators aproaching zero,
 * here is where this method comes handy, it finds a root of a given multiplicity using the 
 * g function g(x) = x - f(x)*f'(x) / (f'(x)^2 - f(x)*f''(x)).
 * 
 * In this method we have to give two derivatives, and it's convergence time isn't great, but
 * it is useful when it comes to solve problems where Secant and Newton have failed.
 * @author halzate93
 * https://github.com/halzate93/solver/wiki/Multiple-Roots
 */
public class MultipleRoots extends FixedPoint {

	private Function df;
	private Function d2f;
	
	protected float denominator;
	protected float dy;
	protected float d2y;
	
	@Override
	protected void firstStep() throws SyntaxException{
		super.firstStep();
		dy = df.evaluate(xn);
		d2y = d2f.evaluate(xn);
		denominator = ((float) Math.pow(dy, 2f)) - y*d2y;
	}
	
	protected float getNextApproximation(JSONObject[] info) throws SyntaxException{
		info[0].put(EResultProcess.Dfx.toString(), dy);
		info[0].put(EResultProcess.D2fx.toString(), d2y);
		info[0].put(EResultProcess.Denominator.toString(), denominator);
		return xn - (y*dy)/denominator;
	}
	
	@Override
	protected void updateStatus() throws SyntaxException{
		super.updateStatus();
		dy = df.evaluate(xn);
		d2y = d2f.evaluate(xn);
		denominator = ((float) Math.pow(dy, 2f)) - y*d2y;
	}

	@Override
	protected boolean checkExit(){
		if(!super.checkExit())
			return false;
		else if(denominator == 0){
			result = new SimpleEntry<EResults, String>(EResults.Failure,
					EFailureCauses.Denominator.toString());
			return false;
		} else
			return true;
	}
	
	@Override
	public void setup(JSONObject params) throws InvalidParameterException{
		super.setup(params);
		if(params.has(EParameter.Df.toString())){
			String value = params.getString(EParameter.Df.toString());
			try {
				df = new Function(value);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (SyntaxException e) {
				throw new InvalidParameterException(EParameter.Df, value);
			}
		}
		if(params.has(EParameter.D2f.toString())){
			String value = params.getString(EParameter.D2f.toString());
			try {
				d2f = new Function(value);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (SyntaxException e) {
				throw new InvalidParameterException(EParameter.D2f, value);
			}
		}
	}
	
	@Override
	public EParameter[] getRequiredParameters(){
		ArrayList<EParameter> required = new ArrayList<>( Arrays.asList(
				super.getRequiredParameters()));
		required.remove(EParameter.G);
		required.add(EParameter.Df);
		required.add(EParameter.D2f);
		return required.toArray(new EParameter[]{});
	}
	
	@Override
	public EParameter[] checkParameters(){
		ArrayList<EParameter> missing = new ArrayList<>(Arrays.asList(super.checkParameters()));
		missing.remove(EParameter.G);
		if(df == null){
			missing.add(EParameter.Df);
		}
		if(d2f == null){
			missing.add(EParameter.D2f);
		}
		return missing.toArray(new EParameter[]{});
	}

	public Function getDerivative() {
		return df;
	}

	public Function getSecondDerivative() {
		return d2f;
	}
}
