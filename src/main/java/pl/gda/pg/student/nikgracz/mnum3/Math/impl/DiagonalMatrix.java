package pl.gda.pg.student.nikgracz.mnum3.Math.impl;

import org.apache.commons.lang3.Validate;
import pl.gda.pg.student.nikgracz.mnum3.Math.Matrix;
import pl.gda.pg.student.nikgracz.mnum3.Utils.MathUtils;

public class DiagonalMatrix extends AbstractMatrix implements Matrix {

    private double[] diagonal;

    public DiagonalMatrix(Matrix matrix) {
        this(matrix.getSizeM());

        for (int i = 0; i < matrix.getSizeM(); i++) {
            diagonal[i] = matrix.get(i, i);
        }
    }

    public DiagonalMatrix(int size) {
        this(new double[size]);
        this.sizeM = size;
        this.sizeN = size;
    }

    public DiagonalMatrix(double[] diagonal) {
        this.diagonal = diagonal;
    }

    @Override
    public void set(int m, int n, double value) {

        Validate.isTrue(m == n, "Value must be put at diagonal!");

        diagonal[m] = value;
    }

    @Override
    public double get(int m, int n) {

        if (m == n) {
            return diagonal[m];
        } else {
            return 0;
        }
    }

    @Override
    public void multiplyBy(double scalar) {
        for (int i = 0; i < diagonal.length; i++) {
            diagonal[i] *= scalar;
        }
    }

    @Override
    public Matrix multiplyBy(Matrix matrix) {

        Matrix result = new RealMatrix(matrix.getSizeM(), matrix.getSizeN());

        for (int i = 0; i < result.getSizeM(); i++) {
            for (int j = 0; j < result.getSizeN(); j++) {
                result.set(i, j, diagonal[i] * matrix.get(i, j));
            }
        }

        return result;
    }

    @Override
    public Matrix inverse() {

        Matrix result = new DiagonalMatrix(sizeM);

        for (int i = 0; i < sizeM; i++) {
            result.set(i, i, 1 / get(i, i));
        }

        return result;
    }

    @Override
    public double firstNorm() {
        return maxAbs();
    }

    @Override
    public double infiniteNorm() {
        return maxAbs();
    }

    private double maxAbs() {

        double result = 0;

        for (double number : diagonal) {
            if (MathUtils.abs(number) > result) {
                result = MathUtils.abs(number);
            }
        }

        return result;
    }
}
