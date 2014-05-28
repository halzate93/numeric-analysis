package co.edu.eafit.solver.lib.test.interpolation.function;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.interpolation.function.EFunctionInterpolationResult;
import co.edu.eafit.solver.lib.interpolation.function.SystemInterpolation;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;

public class SystemInterpolationTest {

	private static double[][] points = {{-2, 12.13533528},
										{-1, 6.367879441},
										{2, -4.610943901},
										{3, 2.085536923}};
	private static double x = 0;
	
	private static double[][] A = {{-8, 4, -2, 1},
								   {-1, 1, -1, 1},
								   {8, 4, 2, 1},
								   {27, 9, 3, 1}};
	private static double y = 0.0046995;
	private static double[] p = {0.412412, 0.939374, -5.836218, 0.004700}; 
	
	private SystemInterpolation sys;
	
	@Before
	public void setUp() throws Exception {
		JSONObject parameters = new JSONObject();
		parameters.put(EInterpolationParameter.Points.toString(),
				MatrixUtility.matrix2Json(points));
		parameters.put(EInterpolationParameter.X.toString(), x);
		
		sys = new SystemInterpolation();
		sys.setParameters(parameters);
	}

	@Test
	public void testRun() throws Exception {
		sys.interpolate();
	}
	
	@Test
	public void testA() throws Exception{
		sys.interpolate();
		assertTrue(MatrixUtility.compareMatrix(A, sys.getA(), 0));
	}

	@Test
	public void testSolution() throws Exception {
		sys.interpolate();
		assertEquals(y, sys.getY(), 0.0000001);
	}
	
	@Test
	public void testP() throws Exception{
		sys.interpolate();
		assertTrue(MatrixUtility.compareVector(p, sys.getP(), 0.000001));
	}
	
	@Test
	public void testJSON() throws Exception{
		JSONObject result = sys.interpolate();
		assertTrue(result.has(EFunctionInterpolationResult.A.toString()));
	}
}
