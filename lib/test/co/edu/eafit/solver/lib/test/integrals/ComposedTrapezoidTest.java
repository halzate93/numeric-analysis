package co.edu.eafit.solver.lib.test.integrals;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.integrals.ComposedTrapezoidMethod;
import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public class ComposedTrapezoidTest {

	private static double[][] points = { { 1, 0.718281828 },
			{ 1.1, 0.804166024 }, { 1.2, 0.920116923 }, { 1.3, 1.069296668 },
			{ 1.4, 1.255199967 }, { 1.5, 1.48168907 }, { 1.6, 1.753032424 },
			{ 1.7, 2.073947392 }, { 1.8, 2.449647464 }, { 1.9, 2.885894442 },
			{ 2, 3.389056099 } };
	private static double y = 1.6746659338;

	private ComposedTrapezoidMethod t;

	@Before
	public void setUp() throws BadParameterException {
		JSONObject parameters = new JSONObject();
		parameters.put(EInterpolationParameter.Points.toString(),
				MatrixUtility.matrix2Json(points));

		t = new ComposedTrapezoidMethod();
		t.setParameters(parameters);
	}

	@Test
	public void testRun() throws Exception {
		t.interpolate();
	}

	@Test
	public void testSolution() throws Exception {
		t.interpolate();
		assertEquals(t.getValue(), y, 0.000001);
	}
}
