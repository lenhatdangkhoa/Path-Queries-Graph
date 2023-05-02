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
            Scanner keyboard = new Scanner(System.in);
            String command = "";
            String start = "";
            String end = "";
            do {
                graph.resetPaths();
                System.out.println("1. Find all directed paths between A and B");
                System.out.println("2. Find directed paths between A and B with a given length");
                System.out.println("3. Find shortest paths between A and B");
                System.out.println("4. Find paths that match a given pattern between A and B");
                System.out.println("Type print to print out the graph. Type quit to exit the program");
                System.out.print("Please enter a command : ");
                command = keyboard.nextLine();
                switch (command) {
                case "1":
                    System.out.print("Please enter a starting node: ");
                    start = keyboard.nextLine();
                    System.out.print("Please enter an ending node: ");
                    end = keyboard.nextLine();
                    if (!graph.graph.containsKey(start) || !graph.graph.containsKey(end)) {
                        System.out.println("No path is found.");
                    } else {
                        graph.findAllPaths(start, end);
                        System.out.println("All possible paths from <" + start + "> to <" + end + ">:");
                        graph.printAllPaths(start);
                    } // if-else
                    break;
                case "2":
                    System.out.print("Please enter a starting node: ");
                    start = keyboard.nextLine();
                    System.out.print("Please enter an ending node: ");
                    end = keyboard.nextLine();
                    System.out.print("Please enter the length: ");
                    int length = Integer.parseInt(keyboard.nextLine());
                    if (!graph.graph.containsKey(start) || !graph.graph.containsKey(end)) {
                        System.out.println("No path is found.");
                    } else {
                        System.out.println("All paths with length " + length + " from <"+ start + "> to " + end + ">: ");
                        graph.findAllPaths(start, end);
                        graph.printAllPaths(start, length);
                    }
                    break;
                case "3":
                    System.out.print("Please enter a starting node: ");
                    start = keyboard.nextLine();
                    System.out.print("Please enter an ending node: ");
                    end = keyboard.nextLine();
                    if (!graph.graph.containsKey(start) || !graph.graph.containsKey(end)) {
                        System.out.println("No path is found.");
                    } else {
                        System.out.println("The shortest path(s) from <" + start + "> to <" + end + ">: ");
                        graph.findAllPaths(start,end);
                        graph.printMinPaths(start);
                    } // if-else
                    break;
                case "4":
                    System.out.print("Please enter a starting node: ");
                    start = keyboard.nextLine();
                    System.out.print("Please enter an ending node: ");
                    end = keyboard.nextLine();
                    if (!graph.graph.containsKey(start) || !graph.graph.containsKey(end)) {
                        System.out.println("No path is found.");
                    } else {
                        graph.findAllPaths(start,end);
                        System.out.print("Please enter a pattern: ");
                        String regex = keyboard.nextLine();
                        String replaceDot = "";
                        for (String node : graph.graph.keySet()) {
                            replaceDot = "";
                        } // for
                        regex = regex.replace(".", "(" + replaceDot + ")");
                        graph.findPathWithPattern(start, regex);
                    } // if-else
                    break;
                case "print":
                    graph.printGraph();
                    break;
                case "quit":
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
                } // switch
            } while(!command.equals("quit"));
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe);
        } // try-catch

    }
} // class
