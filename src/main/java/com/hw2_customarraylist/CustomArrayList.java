package com.hw2_customarraylist;

import java.util.*;

public class CustomArrayList <E> extends AbstractList<E> implements RandomAccess  {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};
    private Object[] array;
    private int size;
    public CustomArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
    }
    public CustomArrayList(int capacity) {
        if (capacity > 0){
            array = new Object[capacity];
        } else if ( capacity == 0){
            array = EMPTY_ARRAY;
        }
        else throw new IllegalArgumentException("Illegal capacity " + capacity);
    }
    @Override
    public int size() {
        return size;
    }
    public void add(int index, E element) {
        if (index > size || index < 0){
            throw new IndexOutOfBoundsException("Index is out of range " + index);
        }
        modCount++;
        if (size == array.length){
            array = grow();
        }
        System.arraycopy(array, index,
                array, index + 1,
                size - index);
        array[index] = element;
        size++;
    }
    public boolean addAll(Collection<? extends E> c) {
        Object[] objects = c.toArray();
        int addCount = objects.length;
        modCount++;
        if (addCount == 0) {
            return false;
        }
        if (addCount > (array.length - size)){
            array = grow(size + addCount);
        }
        System.arraycopy(objects, 0, array, size, addCount);
        size += addCount;
        return true;
    }
    private Object[] grow() {
        int oldCapacity = array.length;
        int newCapacity =  oldCapacity * 3 / 2 + 1;
        return array = Arrays.copyOf(array, newCapacity);
    }
    private Object[] grow(int minCapacity) {
        int oldCapacity = array.length;
        int newCapacity =  oldCapacity * 3 / 2 + 1;
        return array = Arrays.copyOf(array, Math.max(newCapacity, minCapacity));
    }
    @Override
    public void clear() {
        modCount++;
        int to = size;
        for (int i = 0; i < to; i++) {
            array[i] = null;
        }
        size = 0;
    }
    @Override
    public E get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index is out of range " + index);
        }
        return (E) array[index];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public E remove(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index is out of range " + index);
        }
        E removeValue = (E) array[index];
        removeElement(array, index);
        return removeValue;
    }
    public boolean  remove(Object o) {
        int i = 0;
        if (o == null){
            for (; i < size; i++){
                if (array[i] == null){
                    removeElement(array, i);
                    return true;
                }
            }
        }
        for (; i < size; i++) {
            if (o.equals(array[i])) {
                removeElement(array, i);
                return true;
            }
        }
        return false;
    }
    private void removeElement(Object[] array, int index){
        modCount++;
        int newSize;
        if((newSize = size - 1) > index){
            System.arraycopy(array, index + 1, array, index, newSize - index);
        }
        array[size = newSize] = null;
    }
    public void sort(Comparator<? super E> c) {
        final int expectedModCount = modCount;
        quickSort(c,(E[]) array, 0, size-1);
        if (modCount != expectedModCount){
            throw new ConcurrentModificationException();
        }
        modCount++;
    }
    private void quickSort(Comparator<? super E> c, E arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(c, arr, begin, end);
            quickSort(c, arr, begin, partitionIndex-1);
            quickSort(c, arr, partitionIndex+1, end);
        }
    }
    private int partition(Comparator<? super E> c, E arr[], int begin, int end) {
        E pivot = arr[end];
        int i = (begin-1);
        for (int j = begin; j < end; j++) {
            if (c.compare( arr[j], pivot) <= 0) {
                i++;
                E swap = arr[i];
                arr[i] = arr[j];
                arr[j] = swap;
            }
        }
        E swap = arr[i+1];
        arr[i+1] = arr[end];
        arr[end] = swap;
        return i+1;
    }
}
