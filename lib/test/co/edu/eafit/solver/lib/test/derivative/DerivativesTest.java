package co.edu.eafit.solver.lib.test.derivative;

import static org.junit.Assert.*;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import co.edu.eafit.solver.lib.derivatives.Derivative;
import co.edu.eafit.solver.lib.derivatives.EDerivationRule;
import co.edu.eafit.solver.lib.derivatives.EDerivativeParameter;
import co.edu.eafit.solver.lib.derivatives.MissingParameterException;
import co.edu.eafit.solver.lib.derivatives.NotEnoughPointsException;
import co.edu.eafit.solver.lib.systemsolver.MatrixUtility;
import co.edu.eafit.solver.lib.systemsolver.exception.BadParameterException;

public class DerivativesTest {

	private static double[][] points = {{2, -8.721968},
										{2.2, -11.39094},
										{2.4, -14.34523},
										{2.6, -17.583432},
										{2.8, -21.104092}};
	
	private Derivative d;
	
	@Before
	public void setUp() throws BadParameterException{
		JSONObject p = new JSONObject();
		p.put(EDerivativeParameter.Points.toString(),
				MatrixUtility.matrix2Json(points));
		
		d = new Derivative();
		d.setParameters(p);
	}
	
	@Test (expected = MissingParameterException.class)
	public void testMissing() throws Exception {
		d = new Derivative();
		d.solve();
	}
	
	@Test (expected = NotEnoughPointsException.class)
	public void fail2Backward() throws Exception{
		JSONObject p = new JSONObject();
		p.put(EDerivativeParameter.Rule.toString(),
				EDerivationRule.Backward2.toString());
		p.put(EDerivativeParameter.I.toString(), 0);
		
		d.setParameters(p);
		d.solve();
	}
	
	@Test
	public void test2Forward() throws Exception{
		JSONObject p = new JSONObject();
		p.put(EDerivativeParameter.Rule.toString(),
				EDerivationRule.Forward2.toString());
		p.put(EDerivativeParameter.I.toString(), 0);
		
		d.setParameters(p);
		d.solve();
		
		assertEquals(d.getDx(), -13.34486, 0.000001);
	}
	
	@Test
	public void test2Backward() throws Exception{
		JSONObject p = new JSONObject();
		p.put(EDerivativeParameter.Rule.toString(),
				EDerivationRule.Backward2.toString());
		p.put(EDerivativeParameter.I.toString(), 2);
		
		d.setParameters(p);
		d.solve();
		
		assertEquals(d.getDx(), -14.77145, 0.000001);
	}
	
	@Test
	public void test3Forward() throws Exception{
		JSONObject p = new JSONObject();
		p.put(EDerivativeParameter.Rule.toString(),
				EDerivationRule.Forward3.toString());
		p.put(EDerivativeParameter.I.toString(), 2);
		
		d.setParameters(p);
		d.solve();
		
		assertEquals(d.getDx(), -15.484865, 0.000001);
	}
	
	@Test
	public void test3Backward() throws Exception{
		JSONObject p = new JSONObject();
		p.put(EDerivativeParameter.Rule.toString(),
				EDerivationRule.Backward3.toString());
		p.put(EDerivativeParameter.I.toString(), 3);
		
		d.setParameters(p);
		d.solve();
		
		assertEquals(d.getDx(), -16.90079, 0.000001);
	}
	
	@Test
	public void test3Center() throws Exception{
		JSONObject p = new JSONObject();
		p.put(EDerivativeParameter.Rule.toString(),
				EDerivationRule.Center3.toString());
		p.put(EDerivativeParameter.I.toString(), 2);
		
		d.setParameters(p);
		d.solve();
		
		assertEquals(d.getDx(), -15.48123, 0.000001);
	}
	
	@Test
	public void test5Forward() throws Exception{
		JSONObject p = new JSONObject();
		p.put(EDerivativeParameter.Rule.toString(),
				EDerivationRule.Forward5.toString());
		p.put(EDerivativeParameter.I.toString(), 0);
		
		d.setParameters(p);
		d.solve();
		
		assertEquals(d.getDx(), -12.62928167, 0.00000001);
	}
	
	@Test
	public void test5Backward() throws Exception{
		JSONObject p = new JSONObject();
		p.put(EDerivativeParameter.Rule.toString(),
				EDerivationRule.Backward5.toString());
		p.put(EDerivativeParameter.I.toString(), 4);
		
		d.setParameters(p);
		d.solve();
		
		assertEquals(d.getDx(), -18.3069616667, 0.00000001);
	}
	
	@Test
	public void test5Center() throws Exception{
		JSONObject p = new JSONObject();
		p.put(EDerivativeParameter.Rule.toString(),
				EDerivationRule.Center5.toString());
		p.put(EDerivativeParameter.I.toString(), 2);
		
		d.setParameters(p);
		d.solve();
		
		assertEquals(d.getDx(), -15.4824216667, 0.00000001);
	}

}
