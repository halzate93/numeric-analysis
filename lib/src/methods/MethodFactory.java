package methods;

import org.json.*;
/**
 * Creates and configures instances of Method, serves as a place to place similar
 * Method construction logic and also isolates it from other code.
 * @author halzate93
 *
 */
public class MethodFactory {

	/**
	 * Builds a Method instance with all the configuration parameters blank.
	 * @param method The specified method to build.
	 * @return a Method instance with all the configuration parameters blank.
	 */
	public static Method build(EMethod method) {
		return new IncrementalSearch(); // TODO: This serves as a mockup.
	}
	
	/**
	 * Builds a Method instance and sets the given parameters, they could not be
	 * complete anyway.
	 * @param method The specified method to build.
	 * @param params The starting configuration parameters.
	 * @return A Method instance with it's configuration parameters completely or
	 * partially set.
	 */
	public static Method build(EMethod method, JSONObject params){
		return new IncrementalSearch(); // TODO: This serves as a mockup.
	}
	
	/**
	 * Sets the given parameters to the Method instance.
	 * @param method The Method prototype to fill.
	 * @param params The given parameters to configure the prototype with.
	 */
	public static void set(Method method, JSONObject params){
		// TODO Auto-generated method stub
	}
}
