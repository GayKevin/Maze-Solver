import java.awt.*;

/**
 * Created by Kevin Gay on 09/04/2017.
 */

/**
 * Linked stack class
 */
public class LinkedStack {
    /**
     * Node Class
     */
    private static class Node {
        /**
         * Data node
         */
        private Point data;
        /**
         * Next node
         */
        private Node next;

        /**
         * Node Constructor
         * @param d point
         * @param n Node
         */
        public Node(Point d, Node n ) {
            data = d;
            next = n;
        }

        /**
         * To String
         * @return
         */
        public String toString() {
            return "";
        }
    }

    /**
     * Top Node
     */
    private Node top;

    /**
     * Constructor stack
     */
    public LinkedStack() {
        top = null;
    }

    /**
     * Is empty
     * @return true of false
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Push date to node
     * @param s Point
     */
    public void push( Point s ) {
        top = new Node( s, top );
    }

    /**
     * Remove node
     * @return node delete
     */
    public Point pop() {
        Point retVal = null;
        if( isEmpty() ){
            System.out.println("Nothing to Remove");
        } else {
            retVal = top.data;
            top = top.next;
        }
        return retVal;
    }

    /**
     * Return peek
     * @return peek point
     */
    public Point peek() {
        Point retVal = null;
        if( isEmpty() ){
            System.out.println("Stack is Empty");
        } else {
            retVal = top.data;
        }
        return retVal;
    }

    /**
     * To string node
     * @return string
     */
    public String toString() {
        String s = "";
        Node n = top;
        while( n != null ) {
            s = s + n.data + " ";
            n = n.next;
        }
        return s;
    }
}