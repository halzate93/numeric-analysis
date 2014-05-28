package co.edu.eafit.solver.lib.test.interpolation.function;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.interpolation.function.spline.CubicSpline;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;

public class CubicSplineTest {

	private static double[][] points = {{0.4, 6.219231},
										{0.6, 7.271719},
										{0.8, 8.824608},
										{1, 11.145858}};
	private static double x = 0.7;
	
	private static double[][] c = {{0, 5.417637, -0.155197, 5.414488},
								   {8.373750, -9.655113, 8.888453, 3.605758},
								   {0, 10.441888, -7.189148, 7.893118}};
	private static double y = 6.337951;
	
	private CubicSpline cs;
	
	@Before
	public void setUp() throws Exception {
		JSONObject parameters = new JSONObject();
		parameters.put(EInterpolationParameter.X.toString(), x);
		parameters.put(EInterpolationParameter.Points.toString(),
				MatrixUtility.matrix2Json(points));
		
		cs = new CubicSpline();
		cs.setParameters(parameters);
	}

	@Test
	public void testRun() throws Exception {
		cs.interpolate();
	}
	
	@Test
	public void testC() throws Exception{
		cs.interpolate();
		assertTrue(MatrixUtility.compareMatrix(c, cs.getC(), 0.000001));
	}
	
	@Test
	public void testSolution() throws Exception{
		cs.interpolate();
		assertEquals(y, cs.getY(), 0.00001);
	}

}
