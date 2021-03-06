package co.edu.eafit.solver.lib.test.systemsolver.gaussianelimination;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.systemsolver.ESystemSolvingParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.DivisionByZeroException;
import co.edu.eafit.solver.lib.systemsolver.exception.MissingParameterException;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.EGaussianEliminationResult;
import co.edu.eafit.solver.lib.systemsolver.gaussianelimination.GaussianElimination;

public class GaussianEliminationTest {

	private static final double[][] A = {{14, 6, -2, 3},
		 								{3, 15, 2, -5},
		 								{-7, 4, -23, 2},
		 								{1, -3, -2, 16}};
	private static final double[] b = {12, 32, -24, 14};
	private static final double[][] answer = {{14, 6, -2, 3, 12},
												{0, 13.71, 2.42, -5.64, 29.42},
												{0, 0, -25.23, 6.38, -33.02},
												{0, 0, 0, 14.05, 22.13}};
	private static final double[] xvalues = {-0.304271246,
											 2.491501541,
											 1.706296786,
											 1.57446059};
	private static final double[][] badA = {{0, 0, 0, 0},
											{0, 0, 0, 0},
											{0, 0, 0, 0},
											{0, 0, 0, 0}};
	
	private GaussianElimination gaussianElimination;
	
	@Before
	public void setUp() throws Exception {
		gaussianElimination = new GaussianElimination();
		JSONObject params = new JSONObject();
		params.put(ESystemSolvingParameter.A.toString(),
				MatrixUtility.matrix2Json(A));
		params.put(ESystemSolvingParameter.b.toString(),
				MatrixUtility.vector2Json(b));
		
		gaussianElimination.setParameters(params);
	}

	@Test
	public void AConfigurationTest() {
		assertTrue(MatrixUtility.compareMatrix(gaussianElimination.getA(), A, Double.MIN_VALUE));
	}
	
	@Test
	public void bConfigurationTest() {
		assertTrue(MatrixUtility.compareVector(gaussianElimination.getB(), b, Double.MIN_VALUE));
	}
	
	@Test
	public void missingParametersTest() throws Exception{
		gaussianElimination = new GaussianElimination();
		try{
			gaussianElimination.solve();
			fail();
		}catch(MissingParameterException m){
			assertEquals(m.getMissingParameters().length, 2);
		}
	}
	
	@Test
	public void kIterationsTest() throws Exception {
		JSONObject result = gaussianElimination.solve();
		assertEquals(
				result.getJSONArray(EGaussianEliminationResult.Steps.toString()).length(),
				A.length - 1);
	}
	
	@Test
	public void gaussianElimination() throws Exception{
		JSONObject result = gaussianElimination.solve();

		double[][] solution = MatrixUtility.json2Matrix(
				result.getJSONArray(EGaussianEliminationResult.Steps.toString())
				.getJSONArray(A.length - 2));
		
		assertTrue(MatrixUtility.compareMatrix(solution, answer, 0.01));
	}
	
	@Test
	public void regresiveSustitution() throws Exception{
		JSONObject result = gaussianElimination.solve();
		
		double[] solution = MatrixUtility.json2Vector(
				result.getJSONArray(EGaussianEliminationResult.X.toString()));
		
		assertTrue(MatrixUtility.compareVector(solution, xvalues, 0.000000001));
	}
	
	@Test (expected = DivisionByZeroException.class)
	public void badSystem() throws Exception{
		JSONObject params = new JSONObject();
		params.put(ESystemSolvingParameter.A.toString(), 
				MatrixUtility.matrix2Json(badA));
		gaussianElimination.setParameters(params);
		
		gaussianElimination.solve();
	}

}
