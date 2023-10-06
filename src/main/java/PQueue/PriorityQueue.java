package PQueue;

import java.util.ArrayList;

/**
 * Priority queue implementation using a heap, and a custom data type for storing element and its priority.
 * In this case, the element with the highest priority will be returned when calling offer() or dequeue().
 * As we have to compare priorities, the type S of priority must extend Comparable.
 * @param <T> Generic type of element
 * @param <S> Generic type of priority, must extend Comparable
 */
public class PriorityQueue <T, S extends Comparable<S>>{
    protected ArrayList<Pair<T,S>> heap;

    /**
     * Basic constructor for the priority queue.
     */
    public PriorityQueue(){
        heap = new ArrayList<>();
    }

    /**
     * Adds an element into the queue with its priority
     * @param item element to be inserted
     * @param priority associated priority
     */
    public void enqueue(T item, S priority){
        Pair<T,S> hold = new Pair<>(item, priority); //we hold this value throughout
        heap.add(hold);
        if(heap.size() > 1){ //shift-up
            int nextidx = heap.size()-1;
            while(nextidx != 0 && hold.getRight().compareTo(heap.get((nextidx-1)/2).getRight()) > 0){
                //progressive swapping
                heap.set(nextidx, heap.get((nextidx-1)/2));
                nextidx = (nextidx-1)/2;
            }
            heap.set(nextidx, hold); //put in correct place
        }
    }

    /**
     * Returns the element and priority of the highest priority in the queue, and removes it.
     * @return Pair containing element of the highest priority in left and priority in right
     */
    public Pair<T,S> dequeue(){
        if(heap.isEmpty()) throw new ArrayIndexOutOfBoundsException("Empty Priority Queue");
        Pair<T,S> max = heap.get(0);
        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        if(heap.size()<=1) return max;
        //shift-down
        Pair<T,S> hold = heap.get(0);
        int curr = 0, left = 1, right = 2; //current element, left and right nodes
        int next;

        if(((left > heap.size()-1) && (right > heap.size()-1)) || (heap.get(left) == null && heap.get(right) == null))
            next = heap.size(); //don't go into loop since leaf node
        else if((right > heap.size()-1) || heap.get(right) == null) next = left; //no right option so we go left
        else if (heap.get(left).getRight().compareTo(heap.get(right).getRight())>0) next = left; //left>right
        else next = right; //right>left

        while((next < heap.size()) && heap.get(next).getRight().compareTo(hold.getRight()) > 0){
            //shift the greater element up
            heap.set(curr, heap.get(next));
            //update current, left and right nodes for progression
            curr = next;
            left = curr*2+1;
            right = curr*2+2;
            //do node selection checks as above
            if(((left > heap.size()-1) && (right > heap.size()-1)) || (heap.get(left) == null && heap.get(right) == null))
                next = heap.size(); //don't go into loop since leaf node
            else if((right > heap.size()-1) || heap.get(right) == null) next = left; //no right option so we go left
            else if (heap.get(left).getRight().compareTo(heap.get(right).getRight())>0) next = left; //left>right
            else next = right; //right>left
        }
        //curr should be the rightful place for hold
        heap.set(curr, hold);
        return max;
    }

    /**
     * Returns the element and priority of the highest priority without removing it.
     * @return Pair containing element of the highest priority in left and priority in right
     */
    public Pair<T,S> offer(){
        if(heap.isEmpty()) throw new ArrayIndexOutOfBoundsException("Empty Priority Queue");
        return heap.get(0);
    }

    /**
     * Returns true if the priority queue is empty.
     * @return false if priority queue has elements, true if it is empty.
     */
    public boolean isEmpty(){
        return heap.isEmpty();
    }

    /**
     * Finds the size of the priority queue.
     * @return Priority queue size.
     */
    public int size(){
        return heap.size();
    }

    /**
     * Removes all elements in the priority queue.
     */
    public void clear(){
        heap.clear();
    }

    public String toString(){
        return heap.toString();
    }
}
