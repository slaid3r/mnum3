package pl.gda.pg.student.nikgracz.mnum3.Math;

import org.apache.commons.lang3.Validate;
import pl.gda.pg.student.nikgracz.mnum3.Utils.MathUtils;

import java.util.Arrays;

/**
 * Represents MxN matrix.
 */
public class Matrix {

    private double[][] matrix;
    private int sizeM;
    private int sizeN;

    /**
     * Creates MxN matrix full of zeros.
     *
     * @param sizeM  the number of rows
     * @param sizeN  the number of columns
     */
    public Matrix(int sizeM, int sizeN) {
        this(new double[sizeM][sizeN]);
    }

    /**
     * Creates MxN matrix with values stored in given array.
     *
     * @param matrix  the values of the matrix
     */
    public Matrix(double[][] matrix) {
        sizeM = matrix.length;
        sizeN = matrix[0].length;
        this.matrix = copyArray(matrix);
    }

    /**
     * Returns value at a(m)(n).
     *
     * @param m  the number of the row
     * @param n  the number of the column
     * @return  the value stored at a(m)(n)
     */
    public double get(int m, int n) {
        validateCoords(m, n);
        return matrix[m][n];
    }

    /**
     * Sets value at a(m)(n) to the given value.
     *
     * @param m  the number of the row
     * @param n  the number of the column
     * @param value  the value to be set
     */
    public void set(int m, int n, double value) {
        validateCoords(m, n);
        matrix[m][n] = value;
    }

    /**
     * Multiplies this matrix by given scalar.
     *
     * @param scalar  the scalar to multiply by
     */
    public void multiplyBy(double scalar) {
        for (int i = 0; i < sizeM; i++) {
            for (int j = 0; j < sizeN; j++) {
                matrix[i][j] *= scalar;
            }
        }
    }

    /**
     * Returns the first norm of the matrix.
     *
     * @return  the first norm
     */
    public double firstNorm() {

        double norm = 0;

        for (int i = 0; i < sizeN; i++) {

            double sum = 0;

            for (int j = 0; j < sizeM; j++) {
                sum += MathUtils.abs(matrix[j][i]);
            }

            if (sum > norm) {
                norm = sum;
            }
        }

        return norm;
    }

    /**
     * Returns the infinite norm of the matrix.
     *
     * @return  the first norm
     */
    public double infiniteNorm() {

        double norm = 0;

        for (int i = 0; i < sizeM; i++) {

            double sum = 0;

            for (int j = 0; j < sizeN; j++) {
                sum += MathUtils.abs(matrix[i][j]);
            }

            if (sum > norm) {
                norm = sum;
            }
        }

        return norm;
    }

    /**
     * Creates identity matrix of size n.
     *
     * @param n  the size of the matrix
     * @return  the identity matrix
     */
    public static Matrix identityMatrix(int n) {
        Matrix matrix = new Matrix(n, n);
        for (int i = 0; i < n; i++) {
            matrix.set(i, i, 1);
        }
        return matrix;
    }

    /**
     * Creates lower triangular matrix from the given matrix.
     *
     * @param matrix  the source matrix
     * @return  the lower triangular matrix
     */
    public static Matrix lowerTriangularMatrix(Matrix matrix) {

        Matrix result = new Matrix(matrix.getSizeM(), matrix.getSizeN());

        for (int i = 0; i < matrix.getSizeM(); i++) {
            for (int j = 0; j <= i; j++) {
                result.set(i, j, matrix.get(i, j));
            }
        }

        return result;
    }

    /**
     * Creates diagonal matrix from the given matrix.
     *
     * @param matrix  the source matrix
     * @return  the diagonal matrix
     */
    public static Matrix diagonalMatrix(Matrix matrix) {

        Matrix result = new Matrix(matrix.getSizeM(), matrix.getSizeN());

        for (int i = 0; i < matrix.getSizeM(); i++) {
            result.set(i, i, matrix.get(i, i));
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

        Matrix result = new Matrix(matrix.getSizeM(), matrix.getSizeN());

        for (int i = 0; i < matrix.getSizeM(); i++) {
            for (int j = i; j < matrix.getSizeM(); j++) {
                result.set(i, j, matrix.get(i, j));
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Matrix)) {
            return false;
        }

        Matrix other = (Matrix) o;

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

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public int getSizeM() {
        return sizeM;
    }

    public void setSizeM(int sizeM) {
        this.sizeM = sizeM;
    }

    public int getSizeN() {
        return sizeN;
    }

    public void setSizeN(int sizeN) {
        this.sizeN = sizeN;
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
