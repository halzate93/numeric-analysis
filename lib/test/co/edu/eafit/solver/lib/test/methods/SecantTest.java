package co.edu.eafit.solver.lib.test.methods;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.methods.MethodFactory;
import co.edu.eafit.solver.lib.methods.enums.EErrorType;
import co.edu.eafit.solver.lib.methods.enums.EMethod;
import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.enums.EResultInfo;
import co.edu.eafit.solver.lib.methods.enums.EResultProcess;
import co.edu.eafit.solver.lib.methods.enums.EResults;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;
import co.edu.eafit.solver.lib.methods.exceptions.MissingParametersException;
import co.edu.eafit.solver.lib.methods.open.Secant;
import expr.SyntaxException;

public class SecantTest {

	private static final String f = "exp(x) - 5*x + 2";
	private static final float lastX = 0.5f;
	private static final int n = 10;
	private static final float tolerance = 0.00001f;
	private static final EErrorType errorType = EErrorType.Relative;
	private static final float x0 = 1;
	
	private static final float root = 0.88421814f;
	private static final int i = 4;
	private static float MAXERROR = 0.0000001f;
	
	private Secant method;
	
	@Before
	public void setUp() throws Exception {
		method = (Secant) MethodFactory.build(EMethod.Secant);
		JSONObject params = new JSONObject();
		params.put(EParameter.F.toString(), f);
		params.put(EParameter.LastX.toString(), lastX);
		params.put(EParameter.N.toString(), n);
		params.put(EParameter.Tolerance.toString(), tolerance); //Tolerance 1x10^-5
		params.put(EParameter.ErrorType.toString(), errorType.toString());
		params.put(EParameter.X0.toString(), x0);
		method.setup(params);
	}

	@Test
	public void configureFTest() {
		assertEquals(f, method.getFunction().getExpression());
	}
	
	@Test
	public void configureLastXTest() {
		assertEquals(lastX, method.getFirstX(), MAXERROR);
	}
	
	@Test
	public void configureNTest() {
		assertEquals(n, method.getN());
	}
	
	@Test
	public void configureToleranceTest() {
		assertEquals(tolerance, method.getTolerance(), MAXERROR);
	}
	
	@Test
	public void configureErrorTypeTest() {
		assertEquals(errorType, method.getErrorType());
	}
	
	@Test
	public void configureX0Test() {
		assertEquals(x0, method.getX0(), MAXERROR);
	}
	
	@Test
	public void findApproximationTest() throws JSONException, Exception{
		method.run();
		assertEquals(root, method.getLastResult().getDouble(EResults.Root.toString()), tolerance);
	}
	
	@Test
	public void convergenceTest() throws JSONException, Exception{
		method.run();
		assertEquals(i, method.getLastResult().get(EResultInfo.IterationCount.toString()));
	}
	
	@Test
	public void failIterationCountTest() throws JSONException, Exception{
		JSONObject lessIterations = new JSONObject();
		lessIterations.put(EParameter.N.toString(), 2);
		
		method.setup(lessIterations);
		method.run();
		
		assertEquals(EResultInfo.IterationCount.toString(), 
				method.getLastResult().get(EResults.Failure.toString()));
	}
	
	@Test
	public void findRootTest() throws NumberFormatException, JSONException, Exception{
		JSONObject moreIterations = new JSONObject();
		moreIterations.put(EParameter.N.toString(), 6);
		moreIterations.put(EParameter.Tolerance.toString(), 0.00000000001f);
		
		method.setup(moreIterations);
		method.run();
		assertEquals(0f, Float.parseFloat(method.getLastResult()
				.getString(EResultInfo.Error.toString())), MAXERROR);
	}
	
	@Test
	public void setProcessInformationTest() throws MissingParametersException, SyntaxException{
		JSONObject result = method.run();
		JSONArray process = result.getJSONArray(EResultInfo.Proccess.toString());
		assertEquals(i+1, process.length());
	}

	@Test
	public void setDenominatorProcessInformation() throws JSONException, Exception{
		JSONObject moreIterations = new JSONObject();
		moreIterations.put(EParameter.N.toString(), 6);
		moreIterations.put(EParameter.Tolerance.toString(), 0.00000000001f);
		method.setup(moreIterations);
		
		method.run();
		assertEquals(0.000022281685f, method.getLastResult()
				.getJSONArray(EResultInfo.Proccess.toString()).getJSONObject(5)
				.getDouble(EResultProcess.Denominator.toString()), MAXERROR);
	}
	
	@Test(expected = MissingParametersException.class)
	public void missingParamsTest() throws Exception{
		method = (Secant) MethodFactory.build(EMethod.Secant);
		method.run();
	}
	
	@Test(expected = InvalidParameterException.class)
	public void invalidParametersTest() throws InvalidParameterException{
		JSONObject badParam = new JSONObject();
		badParam.put(EParameter.LastX.toString(), "sox(x)");
		
		method.setup(badParam);
	}
}
