package p4.src;

class Vertex {

    Vertex next;
    String value;
    Edge edge;

    // Constructor
    public Vertex() {
        next = new Vertex();
        value = "";
        edge = new Edge();
    } // vertex


} // class


/**
 * Class Edge.
 */
class Edge {
    String edgeValue;

    public Edge() {
        edgeValue = "";
    } // edge()

    public Edge(String value) {
        edgeValue = value;
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


    public void initializeGraph(String data) {

    } // initializeGraph

} // class
