package co.edu.eafit.solver.lib.test.systemsolver.lufactorization;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.DivisionByZeroException;
import co.edu.eafit.solver.lib.systemsolver.exception.MissingParameterException;
import co.edu.eafit.solver.lib.systemsolver.lufactorization.DolittleFactorization;

public class DolittleFactorizationTest {

	private static double[][] A = {{36, 3, -4, 5},
								   {5, -45, 10, -2},
								   {6, 8, 57, 5},
								   {2, 3, -8, -42}};
	private static double[] b = {-20, 69, 96, -32};
	
	private static double[][] L = {{1, 0, 0, 0},
								   {0.1389, 1, 0, 0},
								   {0.1667, -0.1651, 1, 0},
								   {0.0556, -0.0624, -0.1198, 1}};
	private static double[][] U = {{36, 3, -4, 5},
								   {0, -45.4167, 10.5556, -2.6944},
								   {0, 0, 59.4098, 3.7217},
								   {0, 0, 0, -41.9999}};
	private static double[] z = {-20, 71.777777777, 111.186544346, -13.087167344};
	private static double[] x = {-0.295682311, -1.168480503, 1.851998905, 0.311600063};
	private DolittleFactorization luFact;
	
	@Before
	public void setUp() throws Exception {
		JSONObject parameters = new JSONObject();
		parameters.put(ESystemSolvingParameter.A.toString(),
				MatrixUtility.matrix2Json(A));
		parameters.put(ESystemSolvingParameter.b.toString(),
				MatrixUtility.vector2Json(b));
		
		luFact = new DolittleFactorization();
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
		assertTrue(MatrixUtility.compareVector(z, luFact.getZ(), 0.00000001));
	}
	
	@Test
	public void testX() throws MissingParameterException, DivisionByZeroException, Exception{
		luFact.solve();
		assertTrue(MatrixUtility.compareVector(x, luFact.getX(), 0.000000001));
	}
}
