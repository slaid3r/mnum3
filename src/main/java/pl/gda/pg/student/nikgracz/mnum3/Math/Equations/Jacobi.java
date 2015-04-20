package pl.gda.pg.student.nikgracz.mnum3.Math.Equations;

import org.apache.commons.lang3.Validate;
import pl.gda.pg.student.nikgracz.mnum3.Math.Matrix;
import pl.gda.pg.student.nikgracz.mnum3.Math.Vector;
import pl.gda.pg.student.nikgracz.mnum3.Utils.MatrixUtils;
import pl.gda.pg.student.nikgracz.mnum3.Utils.VectorUtils;

/**
 * Class for resolving simultaneous equations using Jacobi iteration method.
 */
public class Jacobi extends IterationMethod {

    /**
     * Resolves the given simultaneous equations represented by the matrix and the vector.
     *
     * @param matrix  the main system matrix
     * @param vector  the free terms vector
     * @param X0  the custom vector
     * @param epsilon  the accuracy epsilon
     * @return  the vector of solutions
     */
    public static Result resolve(Matrix matrix, Vector vector, Vector X0, double epsilon) {

        long start = System.nanoTime();

        double q = calculateP(matrix);

        Validate.isTrue(q < 1, "Gauss-Seidel method is not convergent for this matrix!");

        double r = calculateR(q);

        Vector prevX;
        Vector X = X0;

        Matrix H = transform(matrix);
        Vector G = transform(vector, matrix);

        int i = 0;

        do {

            prevX = X;
            X = calculateX(prevX, H, G);
            i++;

        } while (r * VectorUtils.subtract(X, prevX).norm() > epsilon);

        return new Result(X, i, System.nanoTime() - start);
    }

    private static double calculateP(Matrix matrix) {

        Matrix D = Matrix.diagonalMatrix(matrix);
        Matrix MPlusN = MatrixUtils.subtractMatrices(matrix, D);

        Matrix inverted = MatrixUtils.inverseMatrix(D);
        inverted.multiplyBy(-1);

        Matrix result = MatrixUtils.multiplyMatrices(inverted, MPlusN);

        double firsNorm = result.firstNorm();
        double infiniteNorm = result.infiniteNorm();

        return firsNorm < infiniteNorm ? firsNorm : infiniteNorm;
    }

    private static Vector calculateX(Vector prevX, Matrix matrix, Vector vector) {

        Vector result = new Vector(prevX.size());

        for (int i = 0; i < result.size(); i++) {

            double sum = 0;

            for (int j = 0; j < i; j++) {
                sum += matrix.get(i, j) * prevX.get(j);
            }

            for (int j = i + 1; j < result.size(); j++) {
                sum += matrix.get(i, j) * prevX.get(j);
            }

            sum += vector.get(i);

            result.set(i, sum);
        }

        return result;
    }
}
