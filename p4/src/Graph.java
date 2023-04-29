package p4.src;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

class Vertex {
    String value;
    Edge edge;

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

    public Graph() {
        vertices = new Vertex[100];
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
            //System.out.println(read.nextLine());
            String[] path = read.nextLine().split(" ");
            //System.out.println(path[0]);
            Vertex vertex = new Vertex(path[0], new Edge(null, null));

            if (!vertexInArray(vertex)) {
                //System.out.println(vertex.value);
                vertices[index] = vertex;
                vertex.edge.edgeValue = path[1];
                vertex.edge.next = new Vertex(path[2], new Edge(null, null));
                index++;
            } else {
                for (int i = 0; i < vertices.length; i++) {
                    if (vertices[i] != null && vertices[i].value.equals(vertex.value)) {
                        //System.out.println(vertices[i].value);
                        //System.out.println(path[1]);
                        Vertex tempPointer = vertices[i];
                        //System.out.println("Checking " + vertices[i].value);
                        while (tempPointer.edge.next != null) {
                            //System.out.println("has next");
                            //System.out.println(tempPointer.edge.next.value);
                            tempPointer = tempPointer.edge.next;
                            if (tempPointer.edge.next == null) {
                                tempPointer.edge.next = new Vertex(path[2], new Edge(null, null));
                                tempPointer.edge.edgeValue = path[1];
                                break;
                            } // if


                        } // while
                        /*
                        while (vertices[i].edge.next != null) {
                            System.out.print(vertices[i].value + " -> " + vertices[i].edge.edgeValue);
                            System.out.println(" -> " + vertices[i].edge.next.value + " -> ");
                        //System.out.println(" -> " + vertices[i].edge.next.edge.edgeValue);
                            vertices[i] = vertices[i].edge.next;
                            } */
                    }
                } // for
            } // if-else

        } // while

/*
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] != null) {
                System.out.println(vertices[i].value);

            }
        } // for
*/

        while (vertices[1].edge.next != null) {
            System.out.print(vertices[1].value + " -> " + vertices[1].edge.edgeValue + " -> ");
            vertices[1] = vertices[1].edge.next;
            if (vertices[1].edge.next == null) {
                System.out.println(vertices[1].value);
            }
        } // while

    } // initializeGraph

    /**
     * A helper method for initializeGraph to check for duplicate vertices.
     * @param vertex the vertex
     * @return whether the vertex is in the array
     */
    private boolean vertexInArray(Vertex vertex) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == null) return false;
            else if (vertices[i] != null && vertices[i].value.equals(vertex.value)) {
                //System.out.println(vertices[i].value);
                return true;
            } // if
        } // for
        return false;
    } // vertexInArray

} // class
