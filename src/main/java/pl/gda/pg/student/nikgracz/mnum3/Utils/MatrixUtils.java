package pl.gda.pg.student.nikgracz.mnum3.Utils;

import javafx.util.Pair;
import org.apache.commons.lang3.Validate;
import pl.gda.pg.student.nikgracz.mnum3.Math.Matrix;
import pl.gda.pg.student.nikgracz.mnum3.Math.impl.RealMatrix;
import pl.gda.pg.student.nikgracz.mnum3.SNAP.SNAPGraph;

/**
 * Utility class for {@code RealMatrix}.
 */
public class MatrixUtils {

    /**
     * Converts given SNAPGraph to the adjacency matrix.
     *
     * @param graph  the graph to be converted
     * @return  the adjacency matrix for given graph
     */
    public static Matrix convertToMatrix(SNAPGraph graph) {

        RealMatrix matrix = new RealMatrix(graph.getNodes(), graph.getNodes());

        for (Pair<Integer, Integer> pair : graph.getGraph()) {
            matrix.set(pair.getValue(), pair.getKey(), 1);
        }

        return matrix;
    }

    /**
     * Multiplies the given matrices.
     *
     * @param A  the first matrix to multiply
     * @param B  the second matrix to multiply
     * @return  the result of multiplying
     */
    public static Matrix multiplyMatrices(Matrix A, Matrix B) {

        Validate.isTrue(A.getSizeN() == B.getSizeM(), "The number of columns in A must equal the number of rows in B!");

        Matrix result = new RealMatrix(A.getSizeM(), B.getSizeN());

        for (int i = 0; i < A.getSizeM(); i++) {
            for (int j = 0; j < B.getSizeN(); j++) {

                double sum = 0;

                for (int k = 0; k < A.getSizeN(); k++) {
                    sum += A.get(i, k) * B.get(k, j);
                }

                result.set(i, j, sum);
            }
        }

        return result;
    }

    /**
     * Subtracts the second matrix from the first.
     *
     * @param A  the first matrix
     * @param B  the second matrix
     * @return  the result of subtraction
     */
    public static Matrix subtractMatrices(Matrix A, Matrix B) {

        Validate.isTrue(A.getSizeM() == B.getSizeM() && A.getSizeN() == B.getSizeN(), "Matrices must have the same size!");

        RealMatrix result = new RealMatrix(A.getSizeM(), A.getSizeN());

        for (int i = 0; i < A.getSizeM(); i++) {
            for (int j = 0; j < A.getSizeN(); j++) {
                result.set(i, j, A.get(i, j) - B.get(i, j));
            }
        }
        return result;
    }
}
