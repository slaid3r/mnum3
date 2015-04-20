package pl.gda.pg.student.nikgracz.mnum3.Math;

import pl.gda.pg.student.nikgracz.mnum3.Utils.MathUtils;

/**
 * Simple class representing vector.
 */
public class Vector {

    double[] vector;

    public Vector(int size) {
        this(new double[size]);
    }

    public Vector(double[] vector) {
        this.vector = vector;
    }

    /**
     * Returns the nth element of the vector.
     *
     * @param n  the number of the element
     * @return  the nth element of the vector
     */
    public double get(int n) {
        return vector[n];
    }

    /**
     * Calculates the norm of the vector.
     *
     * @return  the norm of the vector
     */
    public double norm() {

        double result = 0;

        for (double number : vector) {
            result += MathUtils.abs(number);
        }

        return result;
    }

    /**
     * Sets the nth element of the vector to the given value.
     *
     * @param n  the number of the element
     * @param value  the new value
     */
    public void set(int n, double value) {
        vector[n] = value;
    }

    /**
     * Returns the size of the vector.
     *
     * @return  the size of the vector
     */
    public int size() {
        return  vector.length;
    }
}
