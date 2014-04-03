package co.edu.eafit.solver.lib.test.methods;

import static org.junit.Assert.*;
import net.sourceforge.jeval.EvaluationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.methods.MethodFactory;
import co.edu.eafit.solver.lib.methods.enums.EErrorType;
import co.edu.eafit.solver.lib.methods.enums.EMethod;
import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.enums.EResultInfo;
import co.edu.eafit.solver.lib.methods.enums.EResults;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;
import co.edu.eafit.solver.lib.methods.exceptions.MissingParametersException;
import co.edu.eafit.solver.lib.methods.open.FixedPoint;

public class FixedPointTest {

	private FixedPoint method;
	private static final String f = "x * exp(x) - pow(x, 2) - 5*x - 3";
	private static final String g = "(x * exp(x)- pow(x, 2) - 3)/5";
	@Before
	public void setUp() throws Exception {
		method = (FixedPoint) MethodFactory.build(EMethod.FixedPoint);
		JSONObject params = new JSONObject();
		params.put(EParameter.X0.toString(), -0.5f);
		params.put(EParameter.F.toString(), f);
		params.put(EParameter.Tolerance.toString(), 0.0001f); //Tolerance 1x10^-4
		params.put(EParameter.ErrorType.toString(), EErrorType.Relative.toString());
		params.put(EParameter.G.toString(), g);
		params.put(EParameter.N.toString(), 10);
		method.setup(params);
	}

	@Test
	public void configurationTest() {
		assertTrue(
				method.getFunction().getExpression().equals(f) &&
				method.getG().getExpression().equals(g) &&
				method.getX0() == -0.5f &&
				method.getTolerance() == 0.0001f &&
				method.getErrorType() == EErrorType.Relative
				);
	}
	
	@Test
	public void findApproximationTest() throws MissingParametersException, EvaluationException{
		JSONObject response = method.run();
		assertEquals(-0.7998f, (float)response.getDouble(EResults.Root.toString()), 0.0001f);
	}
	
	@Test
	public void convergenceTest() throws MissingParametersException, EvaluationException{	
		JSONObject response = method.run();
		assertEquals(9, response.getInt(EResultInfo.IterationCount.toString()));
	}
	
	@Test
	public void failIterationCountTest() throws InvalidParameterException, MissingParametersException, EvaluationException{
		JSONObject extraParams = new JSONObject();
		extraParams.put(EParameter.N.toString(), 8);
		method.setup(extraParams);
		JSONObject response = method.run();
		assertEquals(EResultInfo.IterationCount.toString(), 
				response.getString(EResults.Failure.toString()));
	}
	
	@Test
	public void findRootTest() throws MissingParametersException, EvaluationException, InvalidParameterException{
		JSONObject params = new JSONObject();
		params.put(EParameter.G.toString(), "x+1");
		params.put(EParameter.F.toString(), "x");
		params.put(EParameter.X0.toString(), -1);
		
		method.setup(params);
		JSONObject response = method.run();
		assertTrue(
				response.getDouble(EResults.Root.toString()) == 0 &&
				Float.parseFloat(response.getString(EResultInfo.Error.toString())) == 0
				);
	}
	
	@Test
	public void setProcessInformationTest() throws MissingParametersException, EvaluationException{
		JSONObject result = method.run();
		JSONArray process = result.getJSONArray(EResultInfo.Proccess.toString());
		assertEquals(9, process.length());
	}
	
	@Test(expected = MissingParametersException.class)
	public void missingParamsTest() throws Exception{
		
		method = (FixedPoint) MethodFactory.build(EMethod.FixedPoint);
		method.run();
		
	}
	
	@Test(expected = InvalidParameterException.class)
	public void invalidParametersTest() throws InvalidParameterException{
		JSONObject badParameter = new JSONObject();
		badParameter.put(EParameter.ErrorType.toString(), true);
		
		method.setup(badParameter);
	}

}
