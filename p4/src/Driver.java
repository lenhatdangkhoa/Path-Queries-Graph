package p4.src;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Driver {

    public static void main(String[] args) {
        try {
            Graph graph = new Graph();
            graph.initializeGraph(new File(args[0]));
            //graph.printGraph();
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Please enter the starting node: ");
            String start = keyboard.nextLine();
            System.out.print("Please enter the ending node: ");
            String end = keyboard.nextLine();
            graph.findAllPaths(start, end);
            System.out.print("Please enter a pattern: ");
            String regex = keyboard.nextLine();
            regex = regex.replace(".", "\\w*");
            System.out.println(regex);
            Pattern pattern = Pattern.compile(regex);


            //System.out.println(matcher.matches());
            System.out.println(graph.findPathWithPattern(start));
            String test = graph.findPathWithPattern(start);
            System.out.println(test);
            Matcher matcher = pattern.matcher(test);
            System.out.println(matcher.matches());
            //System.out.println("All paths from \"" + start + "\" to \"" + end + "\" :");
            graph.printAllPaths(start);
            //graph.printAllPaths(start, 1);
            //System.out.println("Minimum number of paths from \"" + start +"\" to \"" + end + "\" :");
            //graph.printMinPaths(start);
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } // try-catch

    }
} // class
