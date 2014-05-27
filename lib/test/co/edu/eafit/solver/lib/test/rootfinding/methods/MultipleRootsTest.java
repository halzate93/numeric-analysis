package co.edu.eafit.solver.lib.test.rootfinding.methods;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.rootfinding.methods.MethodFactory;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EErrorType;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EMethod;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EParameter;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EResultInfo;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EResultProcess;
import co.edu.eafit.solver.lib.rootfinding.methods.enums.EResults;
import co.edu.eafit.solver.lib.rootfinding.methods.exceptions.InvalidParameterException;
import co.edu.eafit.solver.lib.rootfinding.methods.exceptions.MissingParametersException;
import co.edu.eafit.solver.lib.rootfinding.methods.open.MultipleRoots;
import expr.SyntaxException;

public class MultipleRootsTest {

	private static final String f = "x * exp(x) - exp(x) + 1";
	private static final String df = "exp(x) * x";
	private static final String d2f = "exp(x) * (x + 1)";
	private static final float tolerance = 0.000001f;
	private static final float x0 = 0.5f;
	private static final int n = 5;
	private static final EErrorType errorType = EErrorType.Relative;
	
	private static final float root = -0.0000002712011338543152f;
	private static final int i = 4;
	private static final float MAXERROR = 0.00000000000001f;
	
	private MultipleRoots method;
	
	@Before
	public void setUp() throws Exception {
		method = (MultipleRoots) MethodFactory.build(EMethod.MultipleRoots);
		JSONObject params = new JSONObject();
		params.put(EParameter.F.toString(), f);
		params.put(EParameter.Df.toString(), df);
		params.put(EParameter.D2f.toString(), d2f);
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
	public void configureDfTest() {
		assertEquals(df, method.getDerivative().getExpression());
	}
	
	@Test
	public void configureD2fTest() {
		assertEquals(d2f, method.getSecondDerivative().getExpression());
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
		assertEquals(2.9029745187564293E-12, method.getLastResult()
				.getJSONArray(EResultInfo.Proccess.toString()).getJSONObject(4)
				.getDouble(EResultProcess.Denominator.toString()), MAXERROR);
	}

	@Test
	public void setDerivativeProcessInformation() throws JSONException, Exception{
		JSONObject moreIterations = new JSONObject();
		moreIterations.put(EParameter.N.toString(), 6);
		moreIterations.put(EParameter.Tolerance.toString(), 0.00000000001f);
		method.setup(moreIterations);
		
		method.run();
		assertEquals(-2.4095584194583353E-6, method.getLastResult()
				.getJSONArray(EResultInfo.Proccess.toString()).getJSONObject(4)
				.getDouble(EResultProcess.Dfx.toString()), MAXERROR);
	}
	
	@Test
	public void setSecondDerivativeProcessInformation() throws JSONException, Exception{
		JSONObject moreIterations = new JSONObject();
		moreIterations.put(EParameter.N.toString(), 6);
		moreIterations.put(EParameter.Tolerance.toString(), 0.00000000001f);
		method.setup(moreIterations);
		
		method.run();
		assertEquals(0.9999951720237732, method.getLastResult()
				.getJSONArray(EResultInfo.Proccess.toString()).getJSONObject(4)
				.getDouble(EResultProcess.D2fx.toString()), MAXERROR);
	}
	
	@Test(expected = MissingParametersException.class)
	public void missingParamsTest() throws Exception{
		method = (MultipleRoots) MethodFactory.build(EMethod.MultipleRoots);
		method.run();
	}
	
	
	@Test(expected = InvalidParameterException.class)
	public void invalidParametersTest() throws InvalidParameterException{
		JSONObject badParam = new JSONObject();
		badParam.put(EParameter.Df.toString(), "sox(x)");
		
		method.setup(badParam);
	}
}
