package p4.src;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.io.File;

class Vertex {
    String value;
    Edge edge;
    boolean isVisited = false;

    // Constructor
    public Vertex() {
        value = "";
        edge = new Edge();
    } // vertex
    public Vertex(String value, Edge edge) {
        this.value = value;
        this.edge = edge;
    } //


} // class


/**
 * Class Edge.
 */
class Edge {

    String edgeValue;
    Vertex next;

    public Edge() {
        edgeValue = "";
        next = new Vertex();
    } // edge()

    public Edge(String value, Vertex next) {
        edgeValue = value;
        this.next = next;
    } // edge(val)
} //

public class Graph {
    Vertex[] vertices;
    Vertex currentPos;
    Stack<Vertex> path;
    Stack[] allPaths;
    public Graph() {
        vertices = new Vertex[500];
        path = new Stack<>();
        allPaths = new Stack[500];
    } // graph()

    public Graph(int size) {
        vertices = new Vertex[size];
    } // graph(size)


    public void initializeGraph(File file) throws FileNotFoundException {
        Scanner read = new Scanner(file);
        int size = 0;
        while (read.hasNextLine()) {
            size++;
            read.nextLine();
        } // while
        read = new Scanner(file);
        vertices = new Vertex[size];
        int index= 0;
        while (read.hasNextLine()) {
            String[] path = read.nextLine().split(" ");
            Vertex vertex = new Vertex(path[0], new Edge(null, null));
            if (!vertexInArray(vertex)) {
                vertices[index] = vertex;
                vertex.edge.edgeValue = path[1];
                vertex.edge.next = new Vertex(path[2], new Edge(null, null));
                index++;
            } else {
                for (int i = 0; i < vertices.length; i++) {
                    if (vertices[i] != null && vertices[i].value.equals(vertex.value)) {
                        Vertex tempPointer = vertices[i];
                        while (tempPointer.edge.next != null) {
                            tempPointer = tempPointer.edge.next;
                            if (tempPointer.edge.next == null) {
                                tempPointer.edge.next = new Vertex(path[2], new Edge(null, null));
                                tempPointer.edge.edgeValue = path[1];
                                break;
                            } // if


                        } // while
                    } // for
                } // if-else
            } // else
        } // while
        read = new Scanner(file);
        while (read.hasNextLine()) {
            String[] path = read.nextLine().split(" ");
            Vertex vertex = new Vertex(path[2], new Edge(null, null));
            if (!vertexInArray(vertex)) {
                vertices[index] = vertex;
                index++;
            } // if
        } // while

    } // initializeGraph

    public void printGraph() {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null)
                printHelper(i);
        } // for
    } // printGraph

    private void printHelper(int index) {
        Vertex pointer = vertices[index];
        if (pointer.edge.next == null) {
            System.out.println(pointer.value);
        } //
        while (pointer.edge.next != null) {
            System.out.print(pointer.value +
            " -> [" + pointer.edge.edgeValue + "] -> ");
            pointer = pointer.edge.next;
            if (pointer.edge.next == null) {
                System.out.println(pointer.value);
            } //if
        } // while
    } //
    /**
     * A helper method for initializeGraph to check for duplicate vertices.
     * @param vertex the vertex
     * @return whether the vertex is in the array
     */
    private boolean vertexInArray(Vertex vertex) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == null) return false;
            else if (vertices[i] != null && vertices[i].value.equals(vertex.value)) {
                return true;
            } // if
        } // for
        return false;
    } // vertexInArray

    public void findAllPaths(Vertex startVertex, String end) {
        path.push(startVertex);
        System.out.println(startVertex.value);
        if (startVertex.edge.next == null) {
            path.pop();
            System.out.println("test");
            return;
        }
        if (startVertex.value.equals(end)) {
            Stack<Vertex> tempPath = new Stack<>();
            while (!path.empty()) {
                tempPath.push(path.pop());
            }
            while (!tempPath.empty()) {
                Vertex temp = tempPath.pop();
                System.out.println(temp.value + " -> ");
                path.push(temp);
            } // while
        } //if
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null && vertices[i].value.equals(startVertex.value)) {
                currentPos = vertices[i];
                while (currentPos != null && currentPos.edge.next != null) {
                    for (int j = 0; j < vertices.length; j++) {
                        if (vertices[j] != null && vertices[j].value.equals(vertices[i].value)) {
                            currentPos = vertices[j];
                        } // if
                    } //for
                    findAllPaths(currentPos.edge.next, end);
                    //System.out.println(currentPos.edge.next.value);
                    System.out.println("Curr: -> " + currentPos.edge.edgeValue);
                    currentPos = currentPos.edge.next;
                    //System.out.println(currentPos.value);
                }// while
                path.pop();
            } // if
        } // for

    } // findAllPaths

} // class
