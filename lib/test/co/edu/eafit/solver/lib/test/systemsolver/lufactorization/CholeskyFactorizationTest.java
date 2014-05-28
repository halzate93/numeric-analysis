package co.edu.eafit.solver.lib.test.systemsolver.lufactorization;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.DivisionByZeroException;
import co.edu.eafit.solver.lib.systemsolver.exception.MissingParameterException;
import co.edu.eafit.solver.lib.systemsolver.lufactorization.CholeskyFactorization;

public class CholeskyFactorizationTest {

	private static double[][] A = {{36, 3, -4, 5},
								   {5, -45, 10, -2},
								   {6, 8, 57, 5},
								   {2, 3, -8, -42}};
	private static double[] b = {-20, 69, 96, -32};
	
	private static double[][] L = {{6, 0, 0, 0},
								   {0.8333, -6.7392, 0, 0},
								   {1, 1.1129, 7.7078, 0},
								   {0.3333, 0.4204, -0.9236, -6.4807}};
	private static double[][] U = {{6, 0.5, -0.6667, 0.8333},
								   {0, 6.7392, -1.5663, 0.3998},
								   {0, 0, 7.7078, 0.4829},
								   {0, 0, 0, 6.4807}};
	private static double[] z = {-3.333333333, -10.650803699, 14.425246358, 2.019396485};
	private static double[] x = {-0.295682311, -1.168480503, 1.851998905, 0.311600063};
	private CholeskyFactorization luFact;
	
	@Before
	public void setUp() throws Exception {
		JSONObject parameters = new JSONObject();
		parameters.put(ESystemSolvingParameter.A.toString(),
				MatrixUtility.matrix2Json(A));
		parameters.put(ESystemSolvingParameter.b.toString(),
				MatrixUtility.vector2Json(b));
		
		luFact = new CholeskyFactorization();
		luFact.setParameters(parameters);
	}

	@Test
	public void run() throws MissingParameterException, DivisionByZeroException, Exception {
		luFact.solve();
	}
	
	@Test
	public void testL() throws MissingParameterException, DivisionByZeroException, Exception{
		luFact.solve();
		assertTrue(MatrixUtility.compareMatrix(L, luFact.getL(), 0.0001));
	}
	
	@Test
	public void testU() throws MissingParameterException, DivisionByZeroException, Exception{
		luFact.solve();
		assertTrue(MatrixUtility.compareMatrix(U, luFact.getU(), 0.0001));
	}

	@Test
	public void testZ() throws MissingParameterException, DivisionByZeroException, Exception{
		luFact.solve();
		assertTrue(MatrixUtility.compareVector(z, luFact.getZ(), 0.000000001));
	}
	
	@Test
	public void testX() throws MissingParameterException, DivisionByZeroException, Exception{
		luFact.solve();
		assertTrue(MatrixUtility.compareVector(x, luFact.getX(), 0.000000001));
	}
}
