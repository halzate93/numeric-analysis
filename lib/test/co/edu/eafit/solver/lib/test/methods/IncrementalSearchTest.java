package co.edu.eafit.solver.lib.test.methods;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.methods.MethodFactory;
import co.edu.eafit.solver.lib.methods.enums.EMethod;
import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.enums.EResultInfo;
import co.edu.eafit.solver.lib.methods.enums.EResults;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;
import co.edu.eafit.solver.lib.methods.exceptions.MissingParametersException;
import co.edu.eafit.solver.lib.methods.preliminary.IncrementalSearch;
import expr.SyntaxException;

public class IncrementalSearchTest {

	private IncrementalSearch method;
	@Before
	public void setUp() throws Exception {
		method = (IncrementalSearch) MethodFactory.build(EMethod.IncrementalSearch);
	}

	@Test
	public void configurationTest() throws InvalidParameterException {
		JSONObject parameters = new JSONObject();
		String expression = "x^2";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 1f);
		parameters.put(EParameter.N.toString(), 100);
		parameters.put(EParameter.X0.toString(), 0f);
		
		method.setup(parameters);
		assertTrue(
				method.getFunction().getExpression().equals(expression)
				&& method.getDx() == 1f
				&& method.getN() == 100
				&& method.getX0() == 0f
				&& method.getMethodDescriptor() == EMethod.IncrementalSearch
				);
	}
	
	@Test
	public void findIntervalTest() throws MissingParametersException, SyntaxException, InvalidParameterException {
		JSONObject parameters = new JSONObject();
		String expression = "log(x + 0.5)";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 1f);
		parameters.put(EParameter.N.toString(), 10);
		parameters.put(EParameter.X0.toString(), 0f);
			
		method.setup(parameters);
		JSONObject result = method.run();
		
		boolean interval = result.has(EResults.Interval.toString());
		if(interval){
			JSONArray intervalArray = result.getJSONArray(EResults.Interval.toString());
			interval = (float)intervalArray.getDouble(0) == 0f
					&& (float)intervalArray.getDouble(1) == 1f; 
		}
		assertTrue(interval);
	}
	
	@Test
	public void findRootOnStartTest() throws InvalidParameterException, MissingParametersException, SyntaxException{
		JSONObject parameters = new JSONObject();
		String expression = "x^2";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 1f);
		parameters.put(EParameter.N.toString(), 10);
		parameters.put(EParameter.X0.toString(), 0f);
		
		method.setup(parameters);
		JSONObject response = method.run();
		assertTrue(
				response.has(EResults.Root.toString())
				&& response.getDouble(EResults.Root.toString()) == 0f
				);
	}
	
	@Test
	public void findRootOnExecutionTest() throws InvalidParameterException, MissingParametersException, SyntaxException{
		JSONObject parameters = new JSONObject();
		String expression = "(x - 5)^2";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 1f);
		parameters.put(EParameter.N.toString(), 10);
		parameters.put(EParameter.X0.toString(), 0f);
		
		method.setup(parameters);
		JSONObject response = method.run();
		assertTrue(
				response.has(EResults.Root.toString())
				&& response.getDouble(EResults.Root.toString()) == 5f
				);
	}
	
	@Test
	public void failByIterationCountTest() throws MissingParametersException, SyntaxException, InvalidParameterException{
		JSONObject parameters = new JSONObject();
		String expression = "log(x - 10)";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 1f);
		parameters.put(EParameter.N.toString(), 10);
		parameters.put(EParameter.X0.toString(), 0f);
		
		method.setup(parameters);
		JSONObject result = method.run();
		
		assertTrue(
				result.has(EResults.Failure.toString())
				&& result.getString(EResults.Failure.toString())
					.equals(EResultInfo.IterationCount.toString())
				);
	}
	
	@Test
	public void setResultInformationTest() throws MissingParametersException, SyntaxException, InvalidParameterException{
		JSONObject parameters = new JSONObject();
		String expression = "log(x - 10)";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 1f);
		parameters.put(EParameter.N.toString(), 10);
		parameters.put(EParameter.X0.toString(), 0f);
		
		method.setup(parameters);
		JSONObject result = method.run();
		
		assertTrue(
				result.has(EResultInfo.IterationCount.toString())
				&& result.getInt(EResultInfo.IterationCount.toString()) == 10
				&& result.has(EResultInfo.Error.toString())
				&& result.getDouble(EResultInfo.Error.toString()) == 1f
				);
	}

	@Test(expected = MissingParametersException.class)
	public void missingParamsTest() throws MissingParametersException, SyntaxException {
			method.run();
	}
	
	@Test(expected = InvalidParameterException.class)
	public void invalidParameterTest() throws InvalidParameterException {
		JSONObject parameters = new JSONObject();
		parameters.put(EParameter.X0.toString(), true);
		
		method.setup(parameters);
	}
	
	@Test
	public void setProcessInformationTest() throws MissingParametersException, SyntaxException, InvalidParameterException{
		JSONObject parameters = new JSONObject();
		String expression = "x^2 - 1";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 0.3f);
		parameters.put(EParameter.N.toString(), 10);
		parameters.put(EParameter.X0.toString(), 0f);
		
	
		method.setup(parameters);
		JSONObject result = method.run();
		
		assertTrue(
				result.has(EResultInfo.Proccess.toString())
				&& result.getJSONArray(
						EResultInfo.Proccess.toString()).length() == 5
						);
	}
}
