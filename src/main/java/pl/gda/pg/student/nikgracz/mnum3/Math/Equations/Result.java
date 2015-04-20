package pl.gda.pg.student.nikgracz.mnum3.Math.Equations;

import pl.gda.pg.student.nikgracz.mnum3.Math.Vector;

/**
 * Result of the simultaneous equations.
 */
public class Result {

    private Vector results;
    private int iterations;
    private long time;

    public Result(Vector results, int iterations, long time) {
        this.results = results;
        this.iterations = iterations;
        this.time = time;
    }

    public Vector getResults() {
        return results;
    }

    public int getIterations() {
        return iterations;
    }

    public long getTime() {
        return time;
    }

    /**
     * Prints the stored results.
     */
    public void print() {
        System.out.println("Solutions");
        for (int i = 0; i < results.size(); i++) {
            System.out.println("\tx" + i + " = " + results.get(i));
        }
        System.out.println("Iterations: \t\t" + iterations);
        System.out.println("Time(in seconds):\t" + time / 1000000000.0);
    }
}
