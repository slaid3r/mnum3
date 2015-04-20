package pl.gda.pg.student.nikgracz.mnum3.Math.SNAP;


import javafx.util.Pair;
import org.junit.BeforeClass;
import pl.gda.pg.student.nikgracz.mnum3.SNAP.SNAPGraph;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;


public class SNAPGraphTest {

    private static SNAPGraph graph;

    File file = new File(getClass().getClassLoader().getResource("testSnapGraph.txt").getFile());

    @BeforeClass
    public static void prepareClass() {

        Set<Pair<Integer, Integer>> values = new LinkedHashSet<>();
        values.add(new Pair<>(0, 1));
        values.add(new Pair<>(0, 3));
        values.add(new Pair<>(1, 0));
        values.add(new Pair<>(2, 0));
        values.add(new Pair<>(2, 1));
        values.add(new Pair<>(3, 2));

        graph = new SNAPGraph(4, values);
    }
}
