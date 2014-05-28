package co.edu.eafit.solver.lib.derivatives;

import java.util.ArrayList;

import org.json.JSONObject;

import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public class Derivative {
	private EDerivationRule rule;
	private double[][] points;
	private int i = 0;
	
	private double dx;
	
	public void setParameters(JSONObject parameters) throws BadParameterException{
		try{
			if(parameters.has(EDerivativeParameter.Points.toString())){
				points = MatrixUtility.json2Matrix(
						parameters.getJSONArray(EDerivativeParameter.Points.toString()));
			}
			if(parameters.has(EDerivativeParameter.I.toString())){
				i = parameters.getInt(EDerivativeParameter.I.toString());
			}
			if(parameters.has(EDerivativeParameter.Rule.toString())){
				rule = EDerivationRule.valueOf(
						parameters.getString(EDerivativeParameter.Rule.toString()));
			}
		}catch(Exception e){
			throw new BadParameterException(e);
		}
	}
	
	public JSONObject solve() throws Exception{
		checkParameters();
		
		try{
			switch(rule){
			case Backward2:
				 dx = backWard2();
				 break;
			case Backward3:
				dx = backward3();
				break;
			case Center3:
				dx = center3();
				break;
			case Center5:
				dx = center5();
				break;
			case Forward2:
				dx = forward2();
				break;
			case Forward3:
				dx = forward3();
				break;
			case Backward5:
				dx = backward5();
				break;
			case Forward5:
				dx = forward5();
				break;
			default:
				throw new Exception();
			}
		}catch(IndexOutOfBoundsException e){
			throw new NotEnoughPointsException(i, points.length, rule, e);
		}
		JSONObject answer = new JSONObject();
		answer.put(EDerivativeResult.Dx.toString(), dx);
		return answer;
	}


	private double backWard2() {
		return (points[i][1] - points[i - 1][1])/(points[i][0] - points[i - 1][0]); 
	}
	
	private double forward2() {
		return (points[i + 1][1] - points[i][1])/(points[i + 1][0] - points[i][0]);
	}
	
	private double forward3() {
		return (1/(2*(points[i + 1][0] - points[i][0]))) * (-3 * points[i][1] + 4 * points[i + 1][1] - points[i + 2][1]);
	}
	
	private double backward3() {
		return (1/(2*(points[i][0] - points[i - 1][0]))) * (points[i - 2][1] - 4 * points[i - 1][1] + 3 * points[i][1]);
	}

	private double center3() {
		return (points[i + 1][1] - points[i - 1][1])/(points[i + 1][0] - points[i - 1][0]);
	}

	private double forward5() {
		return (1/(12*(points[i + 1][0] - points[i][0]))) * 
				(-25 * points[i][1] + 48 * points[i + 1][1] - 
						36 * points[i + 2][1] + 16 * points[i + 3][1]
								- 3 * points[i + 4][1]);
	}
	
	private double backward5() {
		return (1/(12*(points[i][0] - points[i - 1][0]))) * 
				(25 * points[i][1] - 48 * points[i - 1][1] + 
						36 * points[i - 2][1] - 16 * points[i - 3][1]
								+ 3 * points[i - 4][1]);
	}
	
	private double center5() {
		return (points[i - 2][1] - 8 * points[i - 1][1] +
				8 * points[i + 1][1] - points[i + 2][1])/
				(12*(points[i][0] - points[i - 1][0]));
	}

	private void checkParameters() throws MissingParameterException {
		ArrayList<EDerivativeParameter> missing = new ArrayList<>();
		if(points == null)
			missing.add(EDerivativeParameter.Points);
		if(rule == null)
			missing.add(EDerivativeParameter.Rule);
		if(missing.size() > 0)
			throw new MissingParameterException(
					missing.toArray(new EDerivativeParameter[]{}));
	}

	public double getDx() {
		return dx;
	}

}
