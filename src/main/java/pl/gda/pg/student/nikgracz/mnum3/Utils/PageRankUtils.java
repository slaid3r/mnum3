package pl.gda.pg.student.nikgracz.mnum3.Utils;


import pl.gda.pg.student.nikgracz.mnum3.Math.Matrix;
import pl.gda.pg.student.nikgracz.mnum3.Math.impl.DiagonalMatrix;

/**
 * Utility class for {@code PageRank}.
 */
public class PageRankUtils {

    /**
     * Utility class, should not be instantiated.
     */
    private PageRankUtils() {
    }

    /**
     * Handles the pages without links by changing them to link to every other website.
     *
     * @param adjacencyMatrix  the adjacency matrix
     */
    public static void handlePagesWithoutLinks(Matrix adjacencyMatrix) {

        for (int i = 0; i < adjacencyMatrix.getSizeN(); i++) {
            if (emptyColumn(i, adjacencyMatrix)) {
                for (int j = 0; j < adjacencyMatrix.getSizeM(); j++) {
                    if (j != i) {
                        adjacencyMatrix.set(j, i, 1);
                    }
                }
            }
        }
    }

    /**
     * Prepares diagonal matrix with the number of link on every website.
     *
     * @param matrix  the adjacency matrix
     * @return  the diagonal matrix with number of links
     */
    public static Matrix prepareMatrixWithLinksCount(Matrix matrix) {

        Matrix diagonal = new DiagonalMatrix(matrix.getSizeM());

        for (int i = 0; i < matrix.getSizeM(); i++) {
            double connections = 0;
            for (int j = 0; j < matrix.getSizeN(); j++) {
                connections += matrix.get(j, i);
            }
            diagonal.set(i, i, 1 / connections);
        }

        return diagonal;
    }

    private static boolean emptyColumn(int k, Matrix adjacencyMatrix) {

        for (int i = 0; i < adjacencyMatrix.getSizeM(); i++) {
            if (adjacencyMatrix.get(i, k) != 0) {
                return false;
            }
        }

        return true;
    }
}
