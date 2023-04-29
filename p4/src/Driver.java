package p4.src;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Driver {

    public static void main(String[] args) {
        try {
            Graph graph = new Graph();
            graph.initializeGraph(new File(args[0]));
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } // try-catch

    }
} // class
