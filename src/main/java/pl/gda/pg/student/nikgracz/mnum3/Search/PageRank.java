package pl.gda.pg.student.nikgracz.mnum3.Search;


import pl.gda.pg.student.nikgracz.mnum3.Math.Equations.Jacobi;
import pl.gda.pg.student.nikgracz.mnum3.Math.Equations.Result;
import pl.gda.pg.student.nikgracz.mnum3.Math.Matrix;
import pl.gda.pg.student.nikgracz.mnum3.Math.Vector;
import pl.gda.pg.student.nikgracz.mnum3.Math.impl.RealMatrix;
import pl.gda.pg.student.nikgracz.mnum3.SNAP.SNAPGraph;
import pl.gda.pg.student.nikgracz.mnum3.Utils.MatrixUtils;
import pl.gda.pg.student.nikgracz.mnum3.Utils.PageRankUtils;

import java.util.Arrays;

/**
 * Class for Page Rank algorithm.
 */
public class PageRank {

    private final SNAPGraph graph;
    private final double d;

    public PageRank(SNAPGraph graph, double d) {
        this.graph = graph;
        this.d = d;
    }

    /**
     * Calculates the page rank for stored graph.
     *
     * @return  the vector of PageRank values for stored graph.
     */
    public Result rank() {

        long start = System.nanoTime();


        System.out.println("Creating adjacency matrix.");
        Matrix adjacencyMatrix = MatrixUtils.convertToMatrix(graph);

        System.out.println("Handling pages without links.");
        PageRankUtils.handlePagesWithoutLinks(adjacencyMatrix);
        System.out.println("Preparing matrix with links counts.");
        Matrix diagonal = PageRankUtils.prepareMatrixWithLinksCount(adjacencyMatrix);

        System.out.println("Multiplying matrix by scalar.");
        adjacencyMatrix.multiplyBy(d);
        System.out.println("Multiplying matrices.");
        Matrix multiplied = adjacencyMatrix.multiplyBy(diagonal);
        System.out.println("Subtracting matrices.");
        Matrix equationsSystem = MatrixUtils.subtractMatrices(RealMatrix.identityMatrix(graph.getNodes()), multiplied);

        Result result = Jacobi.resolve(equationsSystem, prepareVector(), new Vector(new double[graph.getNodes()]), 0.00001);

        return new Result(result.getResults(), result.getIterations(), System.nanoTime() - start);
    }

    private Vector prepareVector() {
        double[] column = new double[graph.getNodes()];
        Arrays.fill(column, (1 - d) / graph.getNodes());
        return new Vector(column);
    }
}
