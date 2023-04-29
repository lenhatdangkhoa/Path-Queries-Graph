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
            Vertex startVertex = new Vertex(start, new Edge(null, null));
            for (int i = 0; i < graph.vertices.length; i++) {
                if (graph.vertices[i] != null && graph.vertices[i].value.equals(start)) {
                    startVertex = graph.vertices[i];
                } //if
            }
            graph.findAllPaths(startVertex, end);
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } // try-catch

    }
} // class
