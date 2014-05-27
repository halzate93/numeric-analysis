package co.edu.eafit.solver.lib.systemsolver;

import org.json.JSONArray;

/**
 * Class that holds many utility methods to work with matrixes of multiple sizes.
 * @author halzate93
 *
 */
public class MatrixUtility {
	public static double[][] json2Matrix(JSONArray jsonMatrix) {
		double[][] matrix = new double[jsonMatrix.length()][jsonMatrix.getJSONArray(0).length()];
		for (int i = 0; i < jsonMatrix.length(); i++) {
			JSONArray row = jsonMatrix.getJSONArray(i);
			for (int j = 0; j < row.length(); j++) {
				matrix[i][j] = row.getDouble(j);
			}
		}
		return matrix;
	}

	public static double[] json2Vector(JSONArray jsonArray) {
		double[] vector = new double[jsonArray.length()];
		for (int i = 0; i < jsonArray.length(); i++) {
			vector[i] = jsonArray.getDouble(i);
		}
		return vector;
	}

	public static double[][] augmentedMatrix(double[][] A, double[] b) {
		double[][] Ab = new double[A.length][A[0].length +1];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				Ab[i][j] = A[i][j];
			}
			Ab[i][A[0].length] = b[i];
		}
		return Ab;
	}
	
	public static double[][] decomposeMatrix2A(double[][] Ab){
		double[][] A = new double[Ab.length][Ab.length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				A[i][j] = Ab[i][j];
			}
		}
		return A;
	}
	
	public static double[] decomposeMatrix2b(double[][] Ab) {
		double[] b = new double[Ab.length];
		for (int i = 0; i < b.length; i++) {
			b[i] = Ab[i][Ab.length - 1];
		}
		
		return b;
	}

	public static JSONArray matrix2Json(double[][] matrix) {
		JSONArray jsonMatrix = new JSONArray();
		for(double[] row : matrix){
			jsonMatrix.put(vector2Json(row));
		}
		return jsonMatrix;
	}

	public static JSONArray vector2Json(double[] vector) {
		JSONArray jsonArray = new JSONArray();
		for(double v : vector){
			jsonArray.put(v);
		}
		return jsonArray;
	}
	
	public static boolean compareMatrix(double[][] m, double[][] n, double tolerance){
		boolean equals = m.length == n.length;
		for (int i = 0; equals && i < n.length; i++) {
			equals = compareVector(m[i], n[i], tolerance);
		}
		return equals;
	}
	
	public static boolean compareVector(double[] m, double[] n, double tolerance){
		boolean equals = m.length == n.length;
		for (int i = 0; equals && i < n.length; i++) {
			equals = Math.abs(m[i]-n[i]) < tolerance;
		}
		return equals;
	}

	public static void printVector(double[] vector){
		for (double d : vector) {
			System.out.print(d + " ");
		}
		System.out.println();
	}
	
	public static void printMatrix(double[][] matrix){
		for (double[] ds : matrix) {
			printVector(ds);
		}
	}
	
	public static double[] regresiveSustitution(double[][] Ab){
		double[] values = new double[Ab.length];
		for(int i = Ab.length -1; i >= 0; i--){
			values[i] = Ab[i][Ab[i].length - 1];
			for (int j = i + 1; j < values.length; j++) {
				values[i] -= Ab[i][j] * values[j];
			}
			values[i] /= Ab[i][i];
		}
		return values;
	}
	
	public static double[] regresiveSustitution(double[][] A, double[] b){
		return regresiveSustitution(augmentedMatrix(A, b));
	}
	
	public static double[] progresiveSustitution(double[][] A, double[] b){
		double values[] = new double[b.length];
		
		for (int i = 0; i < values.length; i++) {
			values[i] = b[i];
			for (int j = 0; j < i; j++) {
				values[i] -= A[i][j] * values[j];
			}
			values[i] /= A[i][i];
		}
		
		return values;
	}
	
	public static void swapRows(double[][] Ab, int k, int i) {
		double[] aux = Ab[i];
		Ab[i] = Ab[k];
		Ab[k] = aux;
	}

	public static double[][] matrixProduct(double[][] m, double[][] n) throws Exception {
		if(m[0].length != n.length) throw new Exception("Can't do the requested product");

		double[][] p = new double[m.length][n.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < n[0].length; j++) {
				p[i][j] = pointProduct(m[i], getColumn(n, j));
			}
		}
		return p;
	}
	
	public static double pointProduct(double[] m, double[] n) throws Exception{
		if(m.length != n.length) throw new Exception("Can't do the requested product");
		double acum = 0;
		for (int i = 0; i < n.length; i++) {
			acum += m[i]*n[i];
		}
		return acum;
	}
	
	public static double[] getColumn(double[][] m, int j){
		double[] column = new double[m.length];
		for (int i = 0; i < column.length; i++) {
			column[i] = m[i][j];
		}
		return column;
	}

	public static double[][] transpose(double[][] m) {
		double[][] mt = new double[m[0].length][m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				mt[j][i] = m[i][j];
			}
		}
		return mt;
	}

}
