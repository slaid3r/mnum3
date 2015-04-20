package pl.gda.pg.student.nikgracz.mnum3.Math.impl;

import org.apache.commons.lang3.Validate;
import pl.gda.pg.student.nikgracz.mnum3.Math.Matrix;

import java.util.Arrays;

/**
 * Represents MxN matrix.
 */
public class RealMatrix extends AbstractMatrix implements Matrix {

    private double[][] matrix;

    public RealMatrix(Matrix matrix) {
        this(matrix.getSizeM(), matrix.getSizeN());

        for (int i = 0; i < getSizeM(); i++) {
            for (int j = 0; j < getSizeN(); j++) {
                set(i, j, matrix.get(i, j));
            }
        }
    }

    /**
     * Creates MxN matrix full of zeros.
     *
     * @param sizeM  the number of rows
     * @param sizeN  the number of columns
     */
    public RealMatrix(int sizeM, int sizeN) {
        this(new double[sizeM][sizeN]);
    }

    /**
     * Creates MxN matrix with values stored in given array.
     *
     * @param matrix  the values of the matrix
     */
    public RealMatrix(double[][] matrix) {
        sizeM = matrix.length;
        sizeN = matrix[0].length;
        this.matrix = copyArray(matrix);
    }

    public double get(int m, int n) {
        validateCoords(m, n);
        return matrix[m][n];
    }

    public void set(int m, int n, double value) {
        validateCoords(m, n);
        matrix[m][n] = value;
    }

    public Matrix multiplyBy(Matrix matrix) {
        Validate.isTrue(getSizeN() == matrix.getSizeM(), "The number of columns in A must equal the number of rows in B!");

        Matrix result = new RealMatrix(getSizeM(), matrix.getSizeN());

        if (matrix instanceof DiagonalMatrix) {
            for (int i = 0; i < getSizeM(); i++) {
                for (int j = 0; j < getSizeN(); j++) {
                    result.set(i, j, get(i, j) * matrix.get(j, j));
                }
            }
        } else {
            for (int i = 0; i < getSizeM(); i++) {
                for (int j = 0; j < matrix.getSizeN(); j++) {

                    double sum = 0;

                    for (int k = 0; k < getSizeN(); k++) {
                        sum += get(i, k) * matrix.get(k, j);
                    }

                    result.set(i, j, sum);
                }
            }
        }

        return result;
    }

    public Matrix inverse() {

        Matrix matrix = new RealMatrix(this);

        Validate.isTrue(matrix.getSizeN() == matrix.getSizeM(), "RealMatrix must be square!");

        Matrix inverted = RealMatrix.identityMatrix(matrix.getSizeN());

        for (int i = 0; i < matrix.getSizeN(); i++) {

            double aii = matrix.get(i, i);

            for (int j = 0; j < matrix.getSizeN(); j++) {
                matrix.set(i, j, matrix.get(i, j) / aii);
                inverted.set(i, j, inverted.get(i, j) / aii);
            }

            for (int j = 0; j < i; j++) {

                double aij = matrix.get(j, i);

                for (int k = 0; k < matrix.getSizeN(); k++) {
                    matrix.set(j, k, matrix.get(j, k) - matrix.get(i, k) * aij);
                    inverted.set(j, k, inverted.get(j, k) - inverted.get(i, k) * aij);
                }
            }

            for (int j = i + 1; j < matrix.getSizeN(); j++) {

                double aij = matrix.get(j, i);

                for (int k = 0; k < matrix.getSizeN(); k++) {
                    matrix.set(j, k, matrix.get(j, k) - matrix.get(i, k) * aij);
                    inverted.set(j, k, inverted.get(j, k) - inverted.get(i, k) * aij);
                }
            }
        }

        return inverted;
    }

    /**
     * Creates lower triangular matrix from the given matrix.
     *
     * @param matrix  the source matrix
     * @return  the lower triangular matrix
     */
    public static Matrix lowerTriangularMatrix(Matrix matrix) {

        Matrix result = new RealMatrix(matrix.getSizeM(), matrix.getSizeN());

        for (int i = 0; i < matrix.getSizeM(); i++) {
            for (int j = 0; j <= i; j++) {
                result.set(i, j, matrix.get(i, j));
            }
        }

        return result;
    }

    /**
     * Creates upper triangular matrix from the given matrix.
     *
     * @param matrix  the source matrix
     * @return  the upper triangular matrix
     */
    public static Matrix upperTriangularMatrix(Matrix matrix) {

        Matrix result = new RealMatrix(matrix.getSizeM(), matrix.getSizeN());

        for (int i = 0; i < matrix.getSizeM(); i++) {
            for (int j = i; j < matrix.getSizeM(); j++) {
                result.set(i, j, matrix.get(i, j));
            }
        }

        return result;
    }

    /**
     * Creates identity matrix of size n.
     *
     * @param n  the size of the matrix
     * @return  the identity matrix
     */
    public static RealMatrix identityMatrix(int n) {
        RealMatrix matrix = new RealMatrix(n, n);
        for (int i = 0; i < n; i++) {
            matrix.set(i, i, 1);
        }
        return matrix;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RealMatrix)) {
            return false;
        }

        RealMatrix other = (RealMatrix) o;

        boolean result;

        result = sizeM == other.sizeM;

        if (result) {
            result = sizeN == other.sizeN;
        }

        if (result) {
            result = Arrays.deepEquals(matrix, other.matrix);
        }

        return result;
    }

    private double[][] copyArray(double[][] matrix) {
        double[][] result = new double[sizeM][sizeN];
        for (int i = 0; i < sizeM; i++) {
            result[i] = Arrays.copyOf(matrix[i], sizeN);
        }
        return result;
    }

    private void validateCoords(int m, int n) {
        Validate.isTrue(m < sizeM, "Given m " + m + " is out of bound!");
        Validate.isTrue(n < sizeN, "Given n " + n + " is out of bound!");
    }
}
