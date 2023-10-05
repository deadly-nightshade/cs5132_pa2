package PQueue;

/**
 * Helper class for the priority queue
 * Stores a left and right element
 * @param <L> Generic type of left element
 * @param <R> Generic type of right element
 */
public class Pair<L,R>{
    private L left;
    private R right;

    /**
     * Constructs a pair from specified left and right elements
     * @param left left element
     * @param right right element
     */
    public Pair(L left, R right){
        this.left = left;
        this.right = right;
    }

    /**
     * Returns left element of pair
     * @return left element
     */
    public L getLeft() {
        return left;
    }

    /**
     * Returns right element of pair
     * @return right element
     */
    public R getRight() {
        return right;
    }

    /**
     * String representation of the pair
     * @return string of the left and right elements, stylised to represent an array
     */
    public String toString(){
        return "[" + left.toString() + ", " + right.toString() + "]";
    }

}
