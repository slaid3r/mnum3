package pl.gda.pg.student.nikgracz.mnum3.Math.impl;

import pl.gda.pg.student.nikgracz.mnum3.Math.Matrix;
import pl.gda.pg.student.nikgracz.mnum3.Utils.MathUtils;

public abstract class AbstractMatrix implements Matrix {

    protected int sizeM;
    protected int sizeN;

    public int getSizeM() {
        return sizeM;
    }

    public void setSizeM(int sizeM) {
        this.sizeM = sizeM;
    }

    public int getSizeN() {
        return sizeN;
    }

    public void setSizeN(int sizeN) {
        this.sizeN = sizeN;
    }

    public void multiplyBy(double scalar) {
        for (int i = 0; i < sizeM; i++) {
            for (int j = 0; j < sizeN; j++) {
                set(i, j, get(i, j) * scalar);
            }
        }
    }

    public double firstNorm() {

        double norm = 0;

        for (int i = 0; i < sizeN; i++) {

            double sum = 0;

            for (int j = 0; j < sizeM; j++) {
                sum += MathUtils.abs(get(j, i));
            }

            if (sum > norm) {
                norm = sum;
            }
        }

        return norm;
    }

    public double infiniteNorm() {

        double norm = 0;

        for (int i = 0; i < sizeM; i++) {

            double sum = 0;

            for (int j = 0; j < sizeN; j++) {
                sum += MathUtils.abs(get(i, j));
            }

            if (sum > norm) {
                norm = sum;
            }
        }

        return norm;
    }
}
