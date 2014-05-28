package co.edu.eafit.solver.lib.integrals;

public class ComposedTrapezoidMethod extends TrapezoidMethod {
	@Override
	protected double evaluate(){
		double acum = 0;
		for (int i = 0; i < points.length - 1; i++) {
			acum += trapezoidArea(i, i+1);
		}
		return acum;
	}
}
