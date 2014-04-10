package co.edu.eafit.solver.lib.test.methods;

import static org.junit.Assert.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.methods.MethodFactory;
import co.edu.eafit.solver.lib.methods.enums.EErrorType;
import co.edu.eafit.solver.lib.methods.enums.EMethod;
import co.edu.eafit.solver.lib.methods.enums.EParameter;
import co.edu.eafit.solver.lib.methods.enums.EResults;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;
import co.edu.eafit.solver.lib.methods.exceptions.MissingParametersException;
import co.edu.eafit.solver.lib.methods.open.MultipleRoots;
import co.edu.eafit.solver.lib.methods.open.Newton;
import co.edu.eafit.solver.lib.methods.open.Secant;

public class MultipleRootsTest {

	private static final String f = "x * exp(x) - exp(x) + 1";
	private static final String df = "exp(x) * x";
	private static final String d2f = "exp(x) * (x + 1)";
	private static final float tolerance = 0.000001f;
	private static final float x0 = 0.5f;
	private static final int n = 5;
	private static final EErrorType errorType = EErrorType.Relative;
	
	private static final float root = -0.0000002712011338543152f;
	private static final int i = 3;
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
