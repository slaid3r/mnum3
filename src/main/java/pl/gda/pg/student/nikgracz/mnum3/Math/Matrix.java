package pl.gda.pg.student.nikgracz.mnum3.Math;

public interface Matrix {

    /**
     * Sets value at a(m)(n) to the given value.
     *
     * @param m  the number of the row
     * @param n  the number of the column
     * @param value  the value to be set
     */
    void set(int m, int n, double value);

    /**
     * Returns value at a(m)(n).
     *
     * @param m  the number of the row
     * @param n  the number of the column
     * @return  the value stored at a(m)(n)
     */
    double get(int m, int n);

    /**
     * Multiplies this matrix by given scalar.
     *
     * @param scalar  the scalar to multiply by
     */
    void multiplyBy(double scalar);

    /**
     * Returns the result of multiplying this matrix by the given one.
     *
     * @param matrix  the matrix to multiply by
     */
    Matrix multiplyBy(Matrix matrix);

    /**
     *
     */
    Matrix inverse();

    /**
     * Returns the first norm of the matrix.
     *
     * @return  the first norm
     */
    double firstNorm();

    /**
     * Returns the infinite norm of the matrix.
     *
     * @return  the first norm
     */
    double infiniteNorm();

    int getSizeM();

    int getSizeN();

}
