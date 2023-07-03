package com.hw2_customarraylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CustomArrayListTest {
    CustomArrayList<String> list = new CustomArrayList<>();
    CustomArrayList<String> emptyList = new CustomArrayList<>();
    @BeforeEach
    public void beforeEach() {
        list.addAll(Arrays.asList("Petrov",
                "Sidorov",
                "Ivanov",
                "Kulikov",
                "Smirnov"));
    }
    @Test
    void testIllegalCapacity(){
        assertThrows(IllegalArgumentException.class, () -> {
            new CustomArrayList<String>(-10);
        });
    }
    @Test
    void testSize() {
        assertEquals(emptyList.size(),0);
        assertEquals(list.size(),5);
    }
    @Test
    void testGetElementWithIndex() {
        assertEquals(list.get(2), "Ivanov");
        assertEquals(list.get(4), "Smirnov");
    }
    @Test
    void testGetElementWithIndexNotFound(){
        assertThrows(IndexOutOfBoundsException.class, () ->{
            list.get(20);
        } );
        assertThrows(IndexOutOfBoundsException.class, () ->{
            list.get(-2);
        } );
    }
    @Test
    void testClear() {
        list.clear();
        assertEquals(list.size(), 0);
    }
    @Test
    void tesIsEmpty() {
        assertTrue(emptyList.isEmpty());
        assertFalse(list.isEmpty());
    }
    @Test
    void testAddElementWithIndex() {
        list.add(2, "Mira");
        assertEquals(list.get(2), "Mira");
        assertEquals(6, list.size());
    }
    @Test
    void testAddElementWithIndexOutOfSize() {
        assertThrows(IndexOutOfBoundsException.class, () ->{
            list.add(20, "Mira");
        } );
    }
    @Test
    void testAddAll() {
        List<String> newList = Arrays.asList("Listov",
                "Kruglov",
                "Smoljacov");
        assertTrue(list.addAll(newList));
        assertEquals(8, list.size());
        List<String> largeList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        assertTrue(list.addAll(largeList));
        assertEquals(32, list.size());
    }
    @Test
    void sort() {
        CustomArrayList<Object> expectedList = new CustomArrayList<>();
        expectedList.addAll(Arrays.asList("Ivanov", "Kulikov", "Petrov", "Sidorov", "Smirnov"));
        list.sort(String::compareTo);
        assertArrayEquals(list.toArray(), expectedList.toArray());

        CustomArrayList<Integer> integerList = new CustomArrayList<>();
        integerList.addAll(Arrays.asList(1, 10, 13, 24, 2, 1, 15, 99, 190, 23, 1000));
        CustomArrayList<Integer> expectedIntegerList = new CustomArrayList<>();
        expectedIntegerList.addAll(Arrays.asList(1, 1, 2, 10, 13, 15, 23, 24, 99, 190, 1000));
        integerList.sort(Integer::compareTo);
        assertArrayEquals(integerList.toArray(), expectedIntegerList.toArray());
    }
    @Test
    void testRemoveElementWithIndex() {
        assertEquals("Ivanov", list.remove(2));
        assertEquals(4, list.size());
        assertThrows(IndexOutOfBoundsException.class, () ->{
            list.remove(20);
        } );
        assertThrows(IndexOutOfBoundsException.class, () ->{
            list.remove(-2);
        } );
    }
    @Test
    void testRemoveObject() {
        assertTrue(list.remove("Ivanov"));
        assertEquals(4, list.size());
        assertFalse(list.remove("Ivanov"));
        list.add(2,null);
        assertTrue(list.remove(null));
    }
}