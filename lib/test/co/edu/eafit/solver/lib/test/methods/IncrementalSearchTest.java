package co.edu.eafit.solver.lib.test.methods;

import static org.junit.Assert.*;
import net.sourceforge.jeval.EvaluationException;

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

public class IncrementalSearchTest {

	private IncrementalSearch method;
	@Before
	public void setUp() throws Exception {
		method = (IncrementalSearch) MethodFactory.build(EMethod.IncrementalSearch);
	}

	@Test
	public void configurationTest() {
		JSONObject parameters = new JSONObject();
		String expression = "pow(x, 2)";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 1f);
		parameters.put(EParameter.N.toString(), 100);
		parameters.put(EParameter.X0.toString(), 0f);
		
		try {
			method.setup(parameters);
			assertTrue(
					method.getFunction().getExpression().equals(expression)
					&& method.getDx() == 1f
					&& method.getN() == 100
					&& method.getX0() == 0f
					&& method.getMethodDescriptor() == EMethod.IncrementalSearch
					);
		} catch (InvalidParameterException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void findIntervalTest() {
		JSONObject parameters = new JSONObject();
		String expression = "log(x + 0.5)";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 1f);
		parameters.put(EParameter.N.toString(), 10);
		parameters.put(EParameter.X0.toString(), 0f);
		
		try {
			method.setup(parameters);
			JSONObject result = method.run();
			
			boolean interval = result.has(EResults.Interval.toString());
			if(interval){
				JSONArray intervalArray = result.getJSONArray(EResults.Interval.toString());
				interval = (float)intervalArray.getDouble(0) == 0f
						&& (float)intervalArray.getDouble(1) == 1f; 
			}
			assertTrue(interval);
		} catch (InvalidParameterException e) {
			fail(e.getMessage());
		} catch (MissingParametersException e) {
			fail(e.getMessage());
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void findRootOnStartTest(){
		JSONObject parameters = new JSONObject();
		String expression = "pow(x, 2)";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 1f);
		parameters.put(EParameter.N.toString(), 10);
		parameters.put(EParameter.X0.toString(), 0f);
		
		try {
			method.setup(parameters);
			JSONObject response = method.run();
			assertTrue(
					response.has(EResults.Root.toString())
					&& response.getDouble(EResults.Root.toString()) == 0f
					);
		} catch (InvalidParameterException e) {
			fail(e.getMessage());
		} catch (MissingParametersException e) {
			fail(e.getMessage());
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void findRootOnExecutionTest(){
		JSONObject parameters = new JSONObject();
		String expression = "pow(x - 5, 2)";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 1f);
		parameters.put(EParameter.N.toString(), 10);
		parameters.put(EParameter.X0.toString(), 0f);
		
		try {
			method.setup(parameters);
			JSONObject response = method.run();
			assertTrue(
					response.has(EResults.Root.toString())
					&& response.getDouble(EResults.Root.toString()) == 5f
					);
		} catch (InvalidParameterException e) {
			fail(e.getMessage());
		} catch (MissingParametersException e) {
			fail(e.getMessage());
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void failByIterationCountTest(){
		JSONObject parameters = new JSONObject();
		String expression = "log(x - 10)";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 1f);
		parameters.put(EParameter.N.toString(), 10);
		parameters.put(EParameter.X0.toString(), 0f);
		
		try {
			method.setup(parameters);
			JSONObject result = method.run();
			
			assertTrue(
					result.has(EResults.Failure.toString())
					&& result.getString(EResults.Failure.toString())
						.equals(EResultInfo.IterationCount.toString())
					);
			
		} catch (InvalidParameterException e) {
			fail(e.getMessage());
		} catch (MissingParametersException e) {
			fail(e.getMessage());
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void setResultInformationTest(){
		JSONObject parameters = new JSONObject();
		String expression = "log(x - 10)";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 1f);
		parameters.put(EParameter.N.toString(), 10);
		parameters.put(EParameter.X0.toString(), 0f);
		
		try {
			method.setup(parameters);
			JSONObject result = method.run();
			
			assertTrue(
					result.has(EResultInfo.IterationCount.toString())
					&& result.getInt(EResultInfo.IterationCount.toString()) == 10
					&& result.has(EResultInfo.Error.toString())
					&& result.getDouble(EResultInfo.Error.toString()) == 1f
					);
			
		} catch (InvalidParameterException e) {
			fail(e.getMessage());
		} catch (MissingParametersException e) {
			fail(e.getMessage());
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void missingParamsTest() {
		try {
			method.run();
			fail("Should have thrown a missing param exception.");
		} catch (MissingParametersException e) {
			assertTrue(true);
		} catch (EvaluationException e) {
			fail();
		}
	}
	
	@Test
	public void invalidParameterTest() {
		JSONObject parameters = new JSONObject();
		parameters.put(EParameter.X0.toString(), true);
		
		try {
			method.setup(parameters);
			fail("Should have thrown a invalid parameter exception.");
		} catch (InvalidParameterException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void setProcessInformationTest(){
		JSONObject parameters = new JSONObject();
		String expression = "pow(x, 2) - 1";
		parameters.put(EParameter.F.toString(), expression);
		parameters.put(EParameter.Dx.toString(), 0.3f);
		parameters.put(EParameter.N.toString(), 10);
		parameters.put(EParameter.X0.toString(), 0f);
		
		try {
			method.setup(parameters);
			JSONObject result = method.run();
			
			assertTrue(
					result.has(EResultInfo.Proccess.toString())
					&& result.getJSONArray(
							EResultInfo.Proccess.toString()).length() == 5
							);
		} catch (InvalidParameterException e) {
			fail(e.getMessage());
		} catch (MissingParametersException e) {
			fail(e.getMessage());
		} catch (EvaluationException e) {
			fail(e.getMessage());
		}
	}
}
