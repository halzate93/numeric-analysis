package co.edu.eafit.solver.lib.integrals;

public class ComposedSimpson13 extends Simpson13 {
	@Override
	protected double evaluate() throws Exception{
		double acum = 0;
		for (int i = 0; i < points.length - 2; i+=2) {
			acum += simpson13(i, i+1, i+2);
		}
		return acum;
	}
}
