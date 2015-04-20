package pl.gda.pg.student.nikgracz.mnum3.Utils;

import org.apache.commons.lang3.Validate;
import pl.gda.pg.student.nikgracz.mnum3.Math.Vector;

/**
 * Utility class for {@code Vector} class.
 */
public class VectorUtils {

    /**
     * Subtracts the second vector from the first.
     *
     * @param A  the first vector
     * @param B  the second vector
     * @return  the result of subtraction
     */
    public static Vector subtract(Vector A, Vector B) {

        Validate.isTrue(A.size() == B.size(), "Vectors muse have the same size!");

        Vector result = new Vector(A.size());

        for (int i = 0; i < result.size(); i++) {
            result.set(i, A.get(i) - B.get(i));
        }

        return result;
    }

}
