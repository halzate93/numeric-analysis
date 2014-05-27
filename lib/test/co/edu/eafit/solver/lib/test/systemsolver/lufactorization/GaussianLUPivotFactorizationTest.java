package co.edu.eafit.solver.lib.test.systemsolver.lufactorization;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.MissingParameterException;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.EGaussianEliminationParameter;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.GaussianElimination;
import co.edu.eafit.solver.lib.systemsolver.lufactorization.ELUResults;
import co.edu.eafit.solver.lib.systemsolver.lufactorization.LUFactorizationGaussianPartialPivot;

public class GaussianLUPivotFactorizationTest {

	private static double[][] A = {{-7, 2, -3, 4},
								   {5, -1, 14, -1},
								   {1, 9, -7, 5},
								   {-12, 13, -8, -4}};
	private static double[] b = {-12, 13, 31, -32};
	
	private static double[][] L = {{1, 0, 0, 0},
		   						   {-0.083333333, 1, 0, 0},
		   						   {-0.416666667, 0.438016529, 1, 0},
		   						   {0.583333333, -0.553719008, -0.18385386, 1}};
	private static double[][] U = {{-12, 13, -8, -4},
								   {0, 10.08333333, -7.666666667, 4.666666667},
								   {0, 0, 14.02479339, -4.710743802},
								   {0, 0, 0, 8.051266942}};
	private static double[] x = {3.624387031, 1.603454585, -0.073775891, 2.485618093};
	private static double[] z = {-32, 28.33333333, -12.74380165, 20.01237478};
	
	private LUFactorizationGaussianPartialPivot luFact;
	
	@Before
	public void setUp() throws Exception {
		JSONObject params = new JSONObject();
		params.put(EGaussianEliminationParameter.A.toString(),
				MatrixUtility.matrix2Json(A));
		params.put(EGaussianEliminationParameter.b.toString(),
				MatrixUtility.vector2Json(b));
		
		luFact = new LUFactorizationGaussianPartialPivot();
		luFact.setParameters(params);
	}
	
	@Test
	public void AConfigurationTest() {
		assertTrue(MatrixUtility.compareMatrix(luFact.getA(), A, Double.MIN_VALUE));
	}
	
	@Test
	public void bConfigurationTest() {
		assertTrue(MatrixUtility.compareVector(luFact.getB(), b, Double.MIN_VALUE));
	}
	
	@Test
	public void missingParametersTest() throws Exception{
		luFact = new LUFactorizationGaussianPartialPivot();
		try{
			luFact.solve();
			fail();
		}catch(MissingParameterException m){
			assertEquals(m.getMissingParameters().length, 2);
		}
	}
	
	@Test
	public void run() throws Exception {
		luFact.solve();
	}
	
	@Test
	public void testL() throws Exception {
		luFact.solve();
		assertTrue(MatrixUtility.compareMatrix(L, luFact.getL(), 0.000000001));
	}
	
	@Test
	public void testU() throws Exception {
		luFact.solve();
		assertTrue(MatrixUtility.compareMatrix(U, luFact.getU(), 0.00000001));
	}
	
	@Test
	public void testZ() throws Exception {
		luFact.solve();
		assertTrue(MatrixUtility.compareVector(z, luFact.getZ(), 0.00000001));
	}
	
	@Test
	public void testSolve() throws Exception {
		luFact.solve();
		assertTrue(MatrixUtility.compareVector(x, luFact.getX(), 0.00000001));
	}
	
	@Test
	public void testJSON() throws Exception{
		JSONObject result = luFact.solve();
		assertTrue(
				result.has(ELUResults.Steps.toString())&&
				result.has(ELUResults.X.toString())&&
				result.has(ELUResults.Z.toString()));
	}
}
