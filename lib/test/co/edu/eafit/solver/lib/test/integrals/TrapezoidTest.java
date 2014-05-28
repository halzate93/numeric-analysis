package co.edu.eafit.solver.lib.test.integrals;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.integrals.TrapezoidMethod;
import co.edu.eafit.solver.lib.interpolation.EInterpolationParameter;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public class TrapezoidTest {

	private static double[][] points = {{1, 0.718281}, {2, 3.389056}};
	private static double y = 2.0536685;
	
	private TrapezoidMethod t;
	@Before
	public void setUp() throws BadParameterException{
		JSONObject params = new JSONObject();
		params.put(EInterpolationParameter.Points.toString(),
				MatrixUtility.matrix2Json(points));
		
		t = new TrapezoidMethod();
		t.setParameters(params);
	}
	
	@Test
	public void testRun() throws Exception {
		t.interpolate();
	}

	@Test
	public void testSolution() throws Exception{
		t.interpolate();
		assertEquals(y, t.getValue(), 0.000001);
	}
}
