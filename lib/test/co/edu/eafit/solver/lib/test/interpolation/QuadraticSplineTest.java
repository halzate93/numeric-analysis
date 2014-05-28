package co.edu.eafit.solver.lib.test.interpolation;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.interpolation.function.spline.QuadraticSpline;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;

public class QuadraticSplineTest {

	private static double[][] points = {{0.4, 6.219231},
										{0.6, 7.271719},
										{0.8, 8.824608},
										{1, 11.145858}};
	private static double x = 0.7;
	
	private static double[][] c = {{0.0, 5.262440, 4.114255},
								   {12.510025, -9.749590, 8.617864},
								   {6.699000, -0.451950, 4.898808}};
	private static double y = -4.698067;
	
	private QuadraticSpline qs;
	
	@Before
	public void setUp() throws Exception {
		JSONObject parameters = new JSONObject();
		parameters.put(EInterpolationParameter.X.toString(), x);
		parameters.put(EInterpolationParameter.Points.toString(),
				MatrixUtility.matrix2Json(points));
		
		qs = new QuadraticSpline();
		qs.setParameters(parameters);
	}

	@Test
	public void testRun() throws Exception {
		qs.interpolate();
	}
	
	@Test
	public void testC() throws Exception{
		qs.interpolate();
		assertTrue(MatrixUtility.compareMatrix(c, qs.getC(), 0.000001));
	}
	
	@Test
	public void testSolution() throws Exception{
		qs.interpolate();
		assertEquals(y, qs.getY(), 0.000001);
	}

}
