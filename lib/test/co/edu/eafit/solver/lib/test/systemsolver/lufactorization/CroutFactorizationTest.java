package co.edu.eafit.solver.lib.test.systemsolver.lufactorization;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.DivisionByZeroException;
import co.edu.eafit.solver.lib.systemsolver.exception.MissingParameterException;
import co.edu.eafit.solver.lib.systemsolver.lufactorization.CroutFactorization;

public class CroutFactorizationTest {

	private static double[][] A = {{36, 3, -4, 5},
								   {5, -45, 10, -2},
								   {6, 8, 57, 5},
								   {2, 3, -8, -42}};
	private static double[] b = {-20, 69, 96, -32};
	
	private static double[][] L = {{36, 0, 0, 0},
								   {5, -45.4166, 0, 0},
								   {6, 7.5, 59.4097, 0},
								   {2, 2.8333, -7.1192, -41.9998}};
	private static double[][] U = {{1, 0.0833, -0.1111, 0.1389},
								   {0, 1, -0.2324, 0.0593},
								   {0, 0, 1, 0.0626},
								   {0, 0, 0, 1}};
	private static double[] z = {-0.555555556, -1.580428135, 1.87151902, 0.311600063};
	private static double[] x = {-0.295682311, -1.168480503, 1.851998905, 0.311600063};
	private CroutFactorization luFact;
	
	@Before
	public void setUp() throws Exception {
		JSONObject parameters = new JSONObject();
		parameters.put(ESystemSolvingParameter.A.toString(),
				MatrixUtility.matrix2Json(A));
		parameters.put(ESystemSolvingParameter.b.toString(),
				MatrixUtility.vector2Json(b));
		
		luFact = new CroutFactorization();
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
