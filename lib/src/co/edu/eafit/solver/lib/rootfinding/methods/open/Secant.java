package co.edu.eafit.solver.lib.rootfinding.methods.open;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import co.edu.eafit.solver.lib.rootfinding.methods.enums.EFailureCauses;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EParameter;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EResultProcess;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EResults;
import co.edu.eafit.solver.lib.rootfinding.methods.exceptions.InvalidParameterException;
import expr.SyntaxException;

/**
 * Secant method is a derivative from FixedPoint, as Newton's it has a supplementary G function
 * to calculate approximations for the next xa value; but it works a little different, instead of
 * using the first derivative for fx, we will use an approximation to it, exactly we will use
 * secants instead of tangents, this method convergence is 1.7, it is almost as fast as Newton's
 * but you don't have to derive.
 * @author halzate93
 * https://github.com/halzate93/solver/wiki/Secant
 */
public class Secant extends FixedPoint {

	/**
	 * The first approximation for x.
	 */
	private float x0;
	
	protected float denominator;
	protected float lastX;
	protected float lastY;
	
	/**
	 * Sets the denominator for the first time.
	 */
	@Override
	protected void firstStep() throws SyntaxException{
		super.firstStep();
		lastX = x0;
		lastY = getFunction().evaluate(lastX);
		denominator = y - lastY;
	}
	
	@Override
	protected float getNextApproximation(JSONObject[] info) throws SyntaxException{
		info[0].put(EResultProcess.Denominator.toString(), Float.toString(denominator));
		return xn - y * (xn - lastX)/denominator;
	}
	
	/**
	 * Updates the lastX value before calling the super update method.
	 */
	@Override
	protected void updateStatus() throws SyntaxException{
		lastX = xn;
		lastY = y;
		super.updateStatus();
		denominator = y - lastY;
	}
	
	/**
	 * If the denominator reaches zero, we have a failure and the
	 * method should end.
	 */
	@Override
	protected boolean checkExit(){
		if(!super.checkExit())
			return false;
		else if(denominator == 0){
			result = new SimpleEntry<>(EResults.Failure,
					EFailureCauses.Denominator.toString());
			return false;
		} else if(lastY == 0){
			result = new SimpleEntry<>(EResults.Root, Float.toString(lastX));
			error = 0;
			return false;
		} else
			return true;
	}
	
	@Override
 	public void setup(JSONObject parameters) throws InvalidParameterException {
		super.setup(parameters);
		if(parameters.has(EParameter.LastX.toString())){
			try{
				x0 = (float) parameters.getDouble(EParameter.LastX.toString());
			}catch (JSONException e){
				throw new InvalidParameterException(EParameter.LastX,
						parameters.getString(EParameter.LastX.toString()));
			}
		}
	}
	
	@Override
	public EParameter[] getRequiredParameters(){
		ArrayList<EParameter> required = new ArrayList<>(
				Arrays.asList(super.getRequiredParameters()));
		required.remove(EParameter.G);
		required.add(EParameter.LastX);
		return required.toArray(new EParameter[]{});
	}
	
	@Override
	public EParameter[] checkParameters(){
		ArrayList<EParameter> missing = new ArrayList<>(
				Arrays.asList(super.checkParameters()));
		missing.remove(EParameter.G);
		return missing.toArray(new EParameter[]{});
	}

	public float getFirstX() {
		return x0;
	}
}
