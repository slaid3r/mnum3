package pl.gda.pg.student.nikgracz.mnum3.Math.Math;

import org.junit.Test;
import pl.gda.pg.student.nikgracz.mnum3.Math.Matrix;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class MatrixTest {

    private static final double[][] MATRIX_ONE = new double[][] {{1.2,  2.6, -0.1,  1.5,  13.15},
                                                                 {4.5,  9.8, -0.4,  5.7,  49.84},
                                                                 {0.1, -0.1, -0.3, -3.5, -14.08},
                                                                 {4.5, -5.2,  4.2, -3.4, -46.51}};

    private static final double[][] TRANSPOSED_MATRIX = new double[][] {{  1.2 ,   4.5 ,   0.1 ,   4.5 },
                                                                        {  2.6 ,   9.8 , - 0.1 , - 5.2 },
                                                                        {- 0.1 , - 0.4 , - 0.3 ,   4.2 },
                                                                        {  1.5 ,   5.7 , - 3.5 , - 3.4 },
                                                                        { 13.15,  49.84, -14.08, -46.51}};

    private static final double[][] IDENTITY_MATRIX = new double[][] {{1, 0, 0, 0},
                                                                      {0, 1, 0, 0},
                                                                      {0, 0, 1, 0},
                                                                      {0, 0, 0, 1}};

    private static final double[][] MATRIX_TWO = new double[][] {{1, 2, 3, 4},
                                                                 {2, 3, 4, 5},
                                                                 {4, 5, 6, 7}};

    private static final double[][] MULTIPLIED_MATRIX = new double[][] {{ 2,  4,  6,  8},
                                                                        { 4,  6,  8, 10},
                                                                        { 8, 10, 12, 14}};

    private static final double[][] EXPANDED_MATRIX = new double[][] {{1.2,  2.6, -0.1,  1.5,  13.15, 1},
                                                                      {4.5,  9.8, -0.4,  5.7,  49.84, 2},
                                                                      {0.1, -0.1, -0.3, -3.5, -14.08, 3},
                                                                      {4.5, -5.2,  4.2, -3.4, -46.51, 4}};

    private static final double[] COLUMN = new double[] {1, 2, 3, 4};

    private static final List<Double> RESULT = Arrays.asList(-1.29983, 3.19989, -2.40029, 4.10003);
    private static final double EPSILON = 0.001;

    @Test
    public void shouldGetSize() {
        Matrix matrix = new Matrix(MATRIX_ONE);
        assertEquals(MATRIX_ONE.length, matrix.getSizeM());
    }

    @Test
    public void shouldMultiplyBy() {

        Matrix matrix = new Matrix(MATRIX_TWO);
        Matrix multiplied = new Matrix(MULTIPLIED_MATRIX);

        matrix.multiplyBy(2);

        assertEquals(multiplied, matrix);
    }

    @Test
    public void shouldCreateIdentityMatrix() {

        Matrix expected = new Matrix(IDENTITY_MATRIX);

        assertEquals(expected, Matrix.identityMatrix(IDENTITY_MATRIX.length));

    }

    @Test
    public void shouldGetFirstForm() {

        Matrix matrix = new Matrix(MATRIX_TWO);

        assertEquals(16.0, matrix.firstNorm());
    }

    @Test
    public void shouldGetInfiniteForm() {

        Matrix matrix = new Matrix(MATRIX_TWO);

        assertEquals(22.0, matrix.infiniteNorm());
    }
}
