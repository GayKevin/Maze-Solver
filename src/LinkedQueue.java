import java.awt.*;

/**
 * Created by Kevin Gay on 09/04/2017.
 */

/**
 * LinkedQueue class
 */
public class LinkedQueue {

    /**
     * Class node
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
         * Node Consctrucor
         * @param d
         */
        public Node( Point d ) {
            data = d;
            next = null;
        }

        /**
         * To string
         * @return string
         */
        public String toString() {
            return "";
        }
    }

    /**
     * First node
     */
    private Node first;
    /**
     * Last Node
     */
    private Node last;

    /**
     * Constructor queue
     */
    public LinkedQueue(){
        first = null;
        last = null;
    }

    /**
     * Is empty
     * @return true or false
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Add node
     * @param s Point
     */
    public void add( Point s ) {
        if( isEmpty() ) {
            first = new Node(s);
            last = first;
        } else {
            Node n = new Node(s);
            last.next = n;
            last = n;
        }
    }

    /**
     * Remove node
     * @return node remove
     */
    public Point remove() {
        Point ret = null;
        if( isEmpty() ) {
            System.out.println("Nothing to Remove");
        } else {
            ret = first.data;
            first = first.next;
            if( first == null ) {
                last = null;
            }
        }
        return ret;
    }

    /**
     * Return peek Point
     * @return point
     */
    public Point peek(){
        Point ret = null;
        if( isEmpty() ) {
            System.out.println("Queue is Empty");
        } else {
            ret = first.data;
        }
        return ret;
    }

    /**
     * Return size list
     * @return int size
     */
    public int size() {
        int count = 0;
        Node n = first;
        while( n != null ) {
            count++;
            n = n.next;
        }
        return count;
    }

    /**
     * To string
     * @return String
     */
    public String toString() {
        String s = "";
        Node n = first;
        while( n != null ) {
            s = s + n.data;
            n = n.next;
        }
        return s;
    }
}