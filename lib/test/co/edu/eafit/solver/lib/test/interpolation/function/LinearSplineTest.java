package co.edu.eafit.solver.lib.test.interpolation.function;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.interpolation.function.spline.LinearSpline;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;

public class LinearSplineTest {

	private static double[][] points = {{0.4, 6.219231},
										{0.6, 7.271719},
										{0.8, 8.824608},
										{1, 11.145858}};
	private static double x = 0.7;
	
	private static double[] m = {5.26244, 7.764445, 11.60625};
	private static double[] b = {4.114255, 2.613052, -0.460392};
	private static double y = 8.048164;
	
	private LinearSpline ls;
	
	@Before
	public void setUp() throws Exception {
		JSONObject parameters = new JSONObject();
		parameters.put(EInterpolationParameter.Points.toString(),
				MatrixUtility.matrix2Json(points));
		parameters.put(EInterpolationParameter.X.toString(), x);
		
		ls = new LinearSpline();
		ls.setParameters(parameters);
	}

	@Test
	public void testRun() throws Exception {
		ls.interpolate();
	}
	
	@Test
	public void testM() throws Exception{
		ls.interpolate();
		assertTrue(
				MatrixUtility.compareVector(m, ls.getM(), 0.000001));
	}
	
	@Test
	public void testB() throws Exception{
		ls.interpolate();
		assertTrue(MatrixUtility.compareVector(
				b, ls.getB(), 0.000001));
	}
	
	@Test
	public void testSolution() throws Exception{
		ls.interpolate();
		assertEquals(y, ls.getY(), 0.000001);
	}

}
