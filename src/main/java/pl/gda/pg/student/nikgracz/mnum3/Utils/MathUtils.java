package pl.gda.pg.student.nikgracz.mnum3.Utils;

/**
 * Utility class for summing elements.
 */
public class MathUtils {

    /**
     * Utility class, should not be instantiated.
     */
    private MathUtils() {

    }

    /**
     * Returns the sum of elements stored in given array.
     *
     * @param row  the array of elements to sum up
     * @return  the sum of given elements
     */
    public static double sum(double[] row) {
        double result = 0;
        for (double value : row) {
            result += value;
        }
        return result;
    }

    /**
     * Returns the absolute of the number.
     *
     * @param number  the number
     * @return  the absolute of the number
     */
    public static double abs(double number) {
        return number < 0 ? -number : number;
    }
}
