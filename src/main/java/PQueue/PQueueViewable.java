package PQueue;

import java.util.ArrayList;

public class PQueueViewable<T, S extends Comparable<S>> extends PriorityQueue<T, S>{
    /**
     * Returns deep copy of priority queue contents for possible viewing
     * @return deep copy of current queue state
     */
    public ArrayList<Pair<T,S>> getQueue(){
        ArrayList<Pair<T,S>> temp = new ArrayList<>();
        while(!super.isEmpty()){
            Pair<T,S> i = super.dequeue();
            temp.add(new Pair<>(i.getLeft(), i.getRight()));
        }
        for(Pair<T,S> i: temp){
            super.enqueue(i.getLeft(),i.getRight());
        }
        return temp;
    }
}
