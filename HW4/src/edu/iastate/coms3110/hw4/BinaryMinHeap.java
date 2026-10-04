package edu.iastate.coms3110.hw4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class BinaryMinHeap<T> extends PurePriorityQueue<T> {
    private ArrayList<T> heap = new ArrayList<T>();
    private HashMap<T, Integer> location = new HashMap<T, Integer>();
    private Comparator<T> comp;

    public BinaryMinHeap(Comparator<T> comp) {
        super(comp);
        this.comp = comp;
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
        // Step 1: Add the item to the end of the heap
        heap.add(item);

        // Debugging: Check if the comparator is null
        if (comp == null) {
            // System.out.println("Comparator is null!");
            return;  // Exit early if comp is not initialized
        }

        // Debugging: Print the heap after adding the item
        // System.out.println("Added item: " + item);
        // System.out.println("Heap before bubbling up: " + heap);

        // Step 2: Get the index of the newly added element
        int currentIndex = heap.size() - 1;

        // Step 3: Update the location map with the index of the added item
        location.put(item, currentIndex);  // Store the index of the item in the location map

        // Debugging: Print the location map
        // System.out.println("Location map after adding " + item + ": " + location);

        // Step 4: Bubbling up to restore the heap property
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2; // Parent index in a binary heap

            // Debugging: Print the indices of the current item and its parent
            // System.out.println("Comparing item at index " + currentIndex + " with its parent at index " + parentIndex);

            // Compare current element with its parent
            if (comp.compare(heap.get(currentIndex), heap.get(parentIndex)) < 0) {
                // Swap the current element with its parent
                T temp = heap.get(currentIndex);
                heap.set(currentIndex, heap.get(parentIndex));
                heap.set(parentIndex, temp);

                // Debugging: Print the heap after swapping
                // System.out.println("Swapped: " + heap.get(currentIndex) + " with " + heap.get(parentIndex));
                // System.out.println("Heap after swapping: " + heap);

                // Update the location map after the swap
                location.put(heap.get(currentIndex), currentIndex);
                location.put(heap.get(parentIndex), parentIndex);

                // Move up to the parent
                currentIndex = parentIndex;
            } else {
                // No need to bubble up further, heap property restored
                break;
            }
        }

        // Debugging: Print the final state of the heap after bubbling up
        // System.out.println("Heap after bubbling up: " + heap);
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
        if (heap.isEmpty()) {
            return null; // If the heap is empty, return null
        }

        // Step 1: Get the minimum element (root)
        T minElement = heap.get(0);

        // Step 2: Swap the root with the last element added
        T lastElement = heap.get(heap.size() - 1);
        heap.set(0, lastElement);
        location.replace(lastElement, 0);


        // Step 3: Remove the last element from the heap
        heap.remove(heap.size() - 1);
        location.remove(minElement);

        // Step 4: Bubble down the new root element with its smaller child to restore the heap property
        int currentIndex = 0;
        int leftChildIndex;
        int rightChildIndex;
        int smallerChildIndex;

        while (true) {
            leftChildIndex = 2 * currentIndex + 1;  // Left child index: since the currentIndex starts from 0 rather than 1
            rightChildIndex = 2 * currentIndex + 2; // Right child index: since the currentIndex starts from 0 rather than 1

            // Find the smaller child
            if (leftChildIndex < heap.size()) {
                if (rightChildIndex < heap.size()) {
                    // Both children exist, compare them to find the smaller one
                    smallerChildIndex = comp.compare(heap.get(leftChildIndex), heap.get(rightChildIndex)) < 0
                            ? leftChildIndex : rightChildIndex;
                } else {
                    // Only left child exists
                    smallerChildIndex = leftChildIndex;
                }

                // If the current element is larger than the smaller child, swap them
                if (comp.compare(heap.get(currentIndex), heap.get(smallerChildIndex)) > 0) {
                    // Swap the current element with the smaller child
                    T temp = heap.get(currentIndex);
                    heap.set(currentIndex, heap.get(smallerChildIndex));
                    heap.set(smallerChildIndex, temp);

                    // Update the location map
                    location.put(heap.get(currentIndex), currentIndex);
                    location.put(heap.get(smallerChildIndex), smallerChildIndex);

                    // Move down to the smaller child
                    currentIndex = smallerChildIndex;
                } else {
                    // Heap property is restored
                    break;
                }
            } else {
                // No children, heap property is restored
                break;
            }
        }

        return minElement;
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
        // Step 1: Find the index of the item whose key has decreased
        int currentIndex = location.get(item);

        // Debugging: Print when keyDecreased is called
        // System.out.println("keyDecreased called for item: " + item);
        // System.out.println("Current index of item: " + currentIndex);
        // System.out.println("Current distance of " + item + ": " + location.get(item));
    
        // Step 2: Bubble up to restore the heap property
        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2; // Parent index in a binary heap
            // System.out.println("Parent index of item: " + parentIndex);

    
            // Compare the item with its parent
            if (comp.compare(heap.get(currentIndex), heap.get(parentIndex)) < 0) {
                // System.out.println("Swapping " + heap.get(currentIndex) + " with " + heap.get(parentIndex));

                // Debugging: Print the heap before the swap
                // System.out.println("Heap before swapping: " + heap);

                // Swap the current element with its parent if it is smaller
                T temp = heap.get(currentIndex);
                heap.set(currentIndex, heap.get(parentIndex));
                heap.set(parentIndex, temp);
    
                // Update the location map after the swap
                location.put(heap.get(currentIndex), currentIndex);
                location.put(heap.get(parentIndex), parentIndex);

                // Debugging: Print the heap after the swap
                // System.out.println("Heap after swapping: " + heap);
    
                // Move up to the parent
                currentIndex = parentIndex;
            } else {
                // Heap property is restored
                break;
            }
        }
        // Debugging: Print final heap state after bubbling up
        // System.out.println("Heap after bubbling up: " + heap);
    }
    
}
