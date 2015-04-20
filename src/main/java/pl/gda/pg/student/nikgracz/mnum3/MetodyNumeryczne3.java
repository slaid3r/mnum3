package pl.gda.pg.student.nikgracz.mnum3;

import pl.gda.pg.student.nikgracz.mnum3.Math.Equations.GaussSeidel;
import pl.gda.pg.student.nikgracz.mnum3.Math.Equations.Jacobi;
import pl.gda.pg.student.nikgracz.mnum3.Math.Equations.Result;
import pl.gda.pg.student.nikgracz.mnum3.Math.Matrix;
import pl.gda.pg.student.nikgracz.mnum3.Math.Vector;
import pl.gda.pg.student.nikgracz.mnum3.SNAP.SNAPGraph;
import pl.gda.pg.student.nikgracz.mnum3.Search.PageRank;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Application main class.
 */
public class MetodyNumeryczne3 {

    private static Logger LOGGER = Logger.getLogger(MetodyNumeryczne3.class.getName());

    /**
     * Application entry point.
     *
     * @param args  the array of arguments
     */
    public static void main(String[] args) throws IOException {

        if (args.length > 0) {

            System.out.println("Application started with argument. Calculating PageRank.");

            File file = new File(args[0]);

            PageRank page = new PageRank(new SNAPGraph(file), 0.85);

            Result result = page.rank();

            result.print();

        } else {

            System.out.println("Application started without argument. Solving matrix.");

            Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
            int m = scanner.nextInt();
            double epsilon = scanner.nextDouble();

            Matrix matrix = new Matrix(m, m);
            Vector vector = new Vector(m);

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    matrix.set(i, j, scanner.nextDouble());
                }
                vector.set(i, scanner.nextDouble());
            }

            Vector X0 = new Vector(m);

            for (int i = 0; i < m; i++) {
                X0.set(i, scanner.nextDouble());
            }

            Result resultGaussSeidel = GaussSeidel.resolve(matrix, vector, X0, epsilon);
            Result resultJacobi = Jacobi.resolve(matrix, vector, X0, epsilon);

            System.out.println("Results for Gauss-Seidel");
            resultGaussSeidel.print();
            System.out.println();
            System.out.println("Results for Jacobi");
            resultJacobi.print();
        }

    }
}
