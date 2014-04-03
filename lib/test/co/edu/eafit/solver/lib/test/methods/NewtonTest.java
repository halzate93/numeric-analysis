package co.edu.eafit.solver.lib.test.methods;

import static org.junit.Assert.*;
import net.sourceforge.jeval.EvaluationException;

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
import co.edu.eafit.solver.lib.methods.enums.EResults;
import co.edu.eafit.solver.lib.methods.exceptions.InvalidParameterException;
import co.edu.eafit.solver.lib.methods.exceptions.MissingParametersException;
import co.edu.eafit.solver.lib.methods.open.FixedPoint;
import co.edu.eafit.solver.lib.methods.open.Newton;

public class NewtonTest {

	private Newton method;
	private static final String f = "exp(-x) - pow(x, 2) * cos(2*x - 4) + 6*x + 3";
	private static final String df = "-exp(-x) - 2*x*cos(2*x - 4) + 2*pow(x, 2)*sin(2*x - 4) + 6";
	private static final int n = 5;
	private static final float tolerance = 0.00001f;
	private static final EErrorType errorType = EErrorType.Absolute;
	private static final float x0 = -1;
	private static final float root = 0.0000015458f;
	
	private static float MAXERROR = 0.00000000001f;
	
	@Before
	public void setUp() throws Exception {
		method = (Newton) MethodFactory.build(EMethod.Newton);
		JSONObject params = new JSONObject();
		params.put(EParameter.F.toString(), f);
		params.put(EParameter.Df.toString(), df);
		params.put(EParameter.N.toString(), n);
		params.put(EParameter.Tolerance.toString(), tolerance); //Tolerance 1x10^-5
		params.put(EParameter.ErrorType.toString(), errorType.toString());
		params.put(EParameter.X0.toString(), x0);
		method.setup(params);
	}

	@Test
	public void configureFtionTest() {
		assertEquals(f, method.getFunction().getExpression());
	}
	
	@Test
	public void configureDftionTest() {
		assertEquals(df, method.getDerivative().getExpression());
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
		System.out.print("This -> ");
		method.run();
		assertEquals(root, method.getLastResult().getDouble(EResults.Root.toString()), MAXERROR);
	}
	
	@Test
	public void convergenceTest(){
		fail("Not implemented.");
	}
	
	@Test
	public void failIterationCountTest(){
		fail("Not implemented.");
	}
	
	@Test
	public void findRootTest(){
		fail("Not implemented.");
	}
	
	@Test
	public void setProcessInformationTest(){
		fail("Not implemented.");
	}
	
	@Test(expected = MissingParametersException.class)
	public void missingParamsTest() throws Exception{
		method = (Newton) MethodFactory.build(EMethod.Newton);
		method.run();
	}
	
	@Test(expected = InvalidParameterException.class)
	public void invalidParametersTest() throws InvalidParameterException{
		JSONObject badParam = new JSONObject();
		badParam.put(EParameter.Df.toString(), "sox(x)");
		
		method.setup(badParam);
	}

}
