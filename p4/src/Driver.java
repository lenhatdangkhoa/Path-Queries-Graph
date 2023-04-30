package p4.src;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Driver {

    public static void main(String[] args) {
        try {
            Graph graph = new Graph();
            graph.initializeGraph(new File(args[0]));
            graph.printGraph();
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Please enter the starting node: ");
            String start = keyboard.nextLine();
            System.out.print("Please enter the ending node: ");
            String end = keyboard.nextLine();
            graph.findAllPaths(start, end);
            System.out.println("All paths from \"" + start + "\" to \"" + end + "\" :");
            graph.printAllPaths(start);
            graph.printAllPaths(start, 1);
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } // try-catch

    }
} // class
