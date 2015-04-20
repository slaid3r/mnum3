package pl.gda.pg.student.nikgracz.mnum3.Math.Equations;

import pl.gda.pg.student.nikgracz.mnum3.Math.Matrix;
import pl.gda.pg.student.nikgracz.mnum3.Math.Vector;
import pl.gda.pg.student.nikgracz.mnum3.Math.impl.RealMatrix;

/**
 * Abstract class for algorithms using iteration method, storing common methods.
 */
public abstract class IterationMethod {

    protected static double calculateR(double q) {
        return q / (1 - q);
    }

    protected static Matrix transform(Matrix matrix) {
        Matrix result = new RealMatrix(matrix.getSizeM(), matrix.getSizeN());

        for (int i = 0; i < matrix.getSizeM(); i++) {

            for (int j = 0; j < i; j++) {
                result.set(i, j, - matrix.get(i, j) / matrix.get(i, i));
            }

            for (int j = i + 1; j < matrix.getSizeM(); j++) {
                result.set(i, j, - matrix.get(i, j) / matrix.get(i, i));
            }
        }

        return result;
    }

    protected static Vector transform(Vector vector, Matrix matrix) {

        Vector result = new Vector(vector.size());

        for (int i = 0; i < vector.size(); i++) {
            result.set(i, vector.get(i) / matrix.get(i, i));
        }

        return result;
    }

}
