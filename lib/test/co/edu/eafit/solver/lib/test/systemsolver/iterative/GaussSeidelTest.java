package co.edu.eafit.solver.lib.test.systemsolver.iterative;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.DivisionByZeroException;
import co.edu.eafit.solver.lib.systemsolver.exception.MissingParameterException;
import co.edu.eafit.solver.lib.systemsolver.iterative.EVectorErrorType;
import co.edu.eafit.solver.lib.systemsolver.iterative.GaussSeidel;

public class GaussSeidelTest {

	private static double[][] A = {{5, -2, 3},
								   {1, 4, -4},
								   {-5, -2, 4}};
	private static double[] b = {-8, 102, -90};
	private static double tolerance = 0.00001;
	private static int iterations = 30;
	private static double lambda = 0.889;
	private static double[] initial = {8.06, 22.1, -1.3};
	private static EVectorErrorType errorType = EVectorErrorType.Euclidean;
	
	private static int i = 17;
	private static double[] x = {8.06452, 22.12905, -1.35483};
	
	private GaussSeidel gs;
	
	@Before
	public void setUp() throws Exception {
		JSONObject parameters = new JSONObject();
		parameters.put(ESystemSolvingParameter.A.toString(),
				MatrixUtility.matrix2Json(A));
		parameters.put(ESystemSolvingParameter.b.toString(),
				MatrixUtility.vector2Json(b));
		parameters.put(ESystemSolvingParameter.Tolerance.toString(),
				tolerance);
		parameters.put(ESystemSolvingParameter.Iterations.toString(),
				iterations);
		parameters.put(ESystemSolvingParameter.Lambda.toString(), lambda);
		parameters.put(ESystemSolvingParameter.Initial.toString(),
				MatrixUtility.vector2Json(initial));
		parameters.put(ESystemSolvingParameter.Error.toString(), errorType.toString());
		
		gs = new GaussSeidel();
		gs.setParameters(parameters);
	}

	@Test
	public void testRun() throws MissingParameterException, DivisionByZeroException, Exception {
		gs.solve();
	}
	
	@Test (expected = MissingParameterException.class)
	public void testMissingParams() throws MissingParameterException, DivisionByZeroException, Exception{
		gs = new GaussSeidel();
		gs.solve();
	}
	
	@Test
	public void testConvergence() throws MissingParameterException, DivisionByZeroException, Exception{
		gs.solve();
		assertEquals(gs.getI(), i);
	}
	
	@Test
	public void testSolution() throws MissingParameterException, DivisionByZeroException, Exception{
		gs.solve();
		assertTrue(MatrixUtility.compareVector(gs.getX(), x, tolerance));
	}
	
	@Test
	public void testNoLambdaConvergence() throws MissingParameterException, DivisionByZeroException, Exception{
		JSONObject parameters = new JSONObject();
		parameters.put(ESystemSolvingParameter.Lambda.toString(), 1);
		
		gs.setParameters(parameters);
		gs.solve();
		
		assertEquals(gs.getI(), iterations);
	}
}
