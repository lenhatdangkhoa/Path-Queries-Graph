package p4.src;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.File;

/**
 * Class Edge.
 */
class Edge {

    String edgeValue;
    String nextValue;

    public Edge() {
        edgeValue = "";
        nextValue = "";
    } // edge()

    public Edge(String value, String nextValue) {
        edgeValue = value;
        this.nextValue = nextValue;
    } // edge(val)

    @Override
    public String toString() {
        return edgeValue + " -> " + nextValue;
    } // void
} //

public class Graph {
    HashMap<String, LinkedList<Edge>> graph;
    Stack<Edge> path;
    ArrayList<Stack<Edge>> allPaths;
    public Graph() {
        graph = new HashMap<>();
        path = new Stack<>();
        allPaths = new ArrayList<>();
    } // graph()

    public void initializeGraph(File file) throws FileNotFoundException {
        Scanner read = new Scanner(file);
        while (read.hasNextLine()) {
            String[] path = read.nextLine().split(" ");
            if (!graph.containsKey(path[0])) {
                LinkedList<Edge> list = new LinkedList<>();
                list.add(new Edge(path[1], path[2]));
                graph.put(path[0], list);
            } else {
                graph.get(path[0]).add(new Edge(path[1], path[2]));
            }
        } // while
        read = new Scanner(file);
        while (read.hasNextLine()) {
            String[] path = read.nextLine().split(" ");
            if (!graph.containsKey(path[2])) {
                graph.put(path[2], new LinkedList<>());
            } // if
        } // while
    } // initializeGraph

    public void printGraph() {
        for (String key: graph.keySet()) {
            System.out.print(key + ": ");
            for (Edge edge : graph.get(key)) {
                    System.out.print(edge.edgeValue + " -> " + edge.nextValue + " | ");
            } // for
            System.out.println();
        } // for
    } // printGraph

    public void resetPaths() {
        allPaths = new ArrayList<>();
        path = new Stack<>();
    } // resetPaths
    public void findAllPaths(String start, String end) {
        if (start.equals(end)) {
            Stack<Edge> tempPath = (Stack<Edge>) path.clone();
            allPaths.add(tempPath);
            return;
        }
        for (Edge edge : graph.get(start)) {
            path.push(edge);
            if (edge.nextValue != null) findAllPaths(edge.nextValue,  end);
            path.pop();
        } // for


    } // findAllPaths
    public void printAllPaths(String start) {
        for (Stack<Edge> stack : allPaths) {
            Stack<Edge> replicate = (Stack<Edge>) stack.clone();
            Stack<Edge> actualPath = new Stack<>();
            while (!replicate.empty()) {
                actualPath.push(replicate.pop());
            } // while
            System.out.print(start + " -> ");
            while (!actualPath.empty()) {
                Edge temp = actualPath.pop();
                System.out.print("[" + temp.edgeValue + "] -> "+ temp.nextValue + " -> ");
            } // while
            System.out.println("{endOfPath}");
        } // for

    } // void

    public void printAllPaths(String start, int count) {
        for (Stack<Edge> stack : allPaths) {
            if (stack.size() == count) {
                Stack<Edge> replicate = (Stack<Edge>) stack.clone();
                Stack<Edge> actualPath = new Stack<>();
                while (!replicate.empty()) {
                    actualPath.push(replicate.pop());
                } // while
                System.out.print(start + " -> ");
                while (!actualPath.empty()) {
                    Edge temp = actualPath.pop();
                    System.out.print("[" + temp.edgeValue + "] -> "+ temp.nextValue + " -> ");
                } // while
                System.out.println("{endOfPath}");
            } // if
        } // for
    } // void

    private int findMinPath() {
        int min = allPaths.get(0).size();
        for (int i = 0; i < allPaths.size(); i++) {
            if (allPaths.get(i).size() <= min) {
                min = allPaths.get(i).size();
            } // if
        } // for
        return min;
    } // findMinPath

    public void printMinPaths(String start) {
        int min = findMinPath();
        for (Stack<Edge> stack : allPaths) {
            if (stack.size() == min) {
                Stack<Edge> replicate = (Stack<Edge>) stack.clone();
                Stack<Edge> actualPath = new Stack<>();
                while (!replicate.empty()) {
                    actualPath.push(replicate.pop());
                } // while
                System.out.print(start + " -> ");
                while (!actualPath.empty()) {
                    Edge temp = actualPath.pop();
                    System.out.print("[" + temp.edgeValue + "] -> "+ temp.nextValue + " -> ");
                } // while
                System.out.println("{endOfPath}");
            } // if
        } // for

    } // printMinPaths

    public void findPathWithPattern(String start, String regex) {
        Pattern pattern = Pattern.compile(regex);
        for (Stack<Edge> stack : allPaths) {
            Stack<Edge> replicate = (Stack<Edge>) stack.clone();
            Stack<Edge> actualPath = new Stack<>();
            while (!replicate.empty()) {
                actualPath.push(replicate.pop());
            } // while
            String matchPath = start;
            HashMap<String, String> possiblePath = new HashMap<>();
            ArrayList<String> edgeVal = new ArrayList<>();
            ArrayList<String> nextVal = new ArrayList<>();
            while (!actualPath.empty()) {
                Edge temp = actualPath.pop();
                //possiblePath.put(temp.edgeValue, temp.nextValue);
                edgeVal.add(temp.edgeValue);
                nextVal.add(temp.nextValue);
                matchPath += temp.nextValue;
                Matcher matcher = pattern.matcher(matchPath);
                if (matcher.matches()) {
                    System.out.println("Possible Match: " + matchPath);
                    System.out.print(start + " -> ");
                    for (int i = 0; i < edgeVal.size(); i++) {
                        System.out.print("[" + edgeVal.get(i) + "] -> " + nextVal.get(i) + " ->  ");
                    } // for
                    System.out.println();
                } // if
            } // while
            //return matchPath;
        } // for
    } // findPathWithPattern
} // class
