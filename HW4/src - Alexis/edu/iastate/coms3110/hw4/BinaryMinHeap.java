package edu.iastate.coms3110.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class BinaryMinHeap<T> extends PurePriorityQueue<T> {
    private ArrayList<T> heap = new ArrayList<T>();
    private HashMap<T, Integer> location = new HashMap<T, Integer>();

    public BinaryMinHeap(Comparator<T> comp) {
        super(comp);
    }

    /**
     * 
     *
     * @return The number of elements in the heap
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Adds an element to the heap.
     *
     * @param item An element not in the heap that will be added to it.
     */
    @Override
    public void add(T item) {
        /* TODO */
        heap.add(item);
        location.put(item, heap.size()-1);
        keyDecreased(item);
    }

    /**
     * 
     *
     * @return Returns the minimum element of the heap without removing it.
     */
    @Override
    public T getMin() {
        return heap.get(0);
    }

    /**
     * Removes the minimum element from the heap and returns it.
     *
     * @return The minimum element that was in the heap when the method was invoked.
     */
    @Override
    public T extractMin() {
        /* TODO */
        if (heap.isEmpty()){
            return null;
        }

        T min = heap.get(0);
        int lastInt = heap.size()-1;
        heap.set(0, heap.get(lastInt));
        location.replace(heap.get(lastInt), 0);
        location.remove(min);
        heap.remove(lastInt);


        int index = 0;
        int swapIndex= index;
        while(2*index <= heap.size() && !heap.isEmpty()) {
            if (2 * index < heap.size()) {
                if (heap.size() > 2*index +1 && comp.compare(heap.get(2 * index), heap.get(2 * index + 1)) > 0) {
                    swapIndex = 2 * index + 1;
                } else if (heap.size() > 2*index && comp.compare(heap.get(0), heap.get(2 * index)) > 0) {
                    swapIndex = 2 * index;
                }
            }
            if (comp.compare(heap.get(index), heap.get(swapIndex)) > 0) {
                T temp = heap.get(index);
                heap.set(index, heap.get(swapIndex));
                location.replace(temp, swapIndex);
                location.replace(heap.get(swapIndex), index);
                heap.set(swapIndex, temp);
                index =swapIndex;
            }else{
                break;
            }
        }
        return min;
    }


    /**
     * Anytime the key decreases for an element in the heap, this method must be
     * invoked to restored the heap property. Here, key refers to the value
     * determining the ordering of heap elements as used in the Comparator.
     *
     * @param item An item in the heap that has had its key decreased.
     */
    @Override
    public void keyDecreased(T item) {
        /* TODO */
        if(item != null) {
            int index = location.get(item);
            int parent = index / 2;
            int comparison = comp.compare(item, heap.get(parent));
            if (comparison < 0) {
                heap.set(index, heap.get(parent));
                location.put(heap.get(parent), index);
                heap.set(parent, item);
                location.put(item, parent);
                keyDecreased(item);
            }
        }
    }
}
