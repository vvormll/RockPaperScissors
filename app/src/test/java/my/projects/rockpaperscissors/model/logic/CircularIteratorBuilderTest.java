package my.projects.rockpaperscissors.model.logic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import my.projects.rockpaperscissors.model.util.CircularIterator;

import static org.junit.Assert.assertEquals;

public class CircularIteratorBuilderTest {

    private List<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
        list.add("This");
        list.add("is");
        list.add("a");
        list.add("list");
    }

    @Test
    public void circlesAroundCollection() {
        Iterator<String> iterator = CircularIterator.build(list);

        String s;
        assertEquals("This", iterator.next());
        assertEquals("is", iterator.next());
        assertEquals("a", iterator.next());
        assertEquals("list", iterator.next());
        assertEquals("This", iterator.next());
        assertEquals("is", iterator.next());
        assertEquals("a", iterator.next());
        assertEquals("list", iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void throwsExceptionOnEmptyCollection() {
        Iterator<String> iterator = CircularIterator.build(Collections.<String>emptyList());
        iterator.next();
    }

    @Test
    public void hasNextReturnsFalseOnEmptyCollection() {
        Iterator<String> iterator = CircularIterator.build(Collections.<String>emptyList());
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void hasNextReturnsTrueOnNonEmptyCollection() {
        Iterator<String> iterator = CircularIterator.build(list);

        assertEquals(true, iterator.hasNext());
    }

    @Test
    public void hasNextReturnsTrueAtTheEndOfCollection() {
        Iterator<String> iterator = CircularIterator.build(list);

        for (int i = 0; i < list.size(); i++) {
            iterator.next();
        }

        assertEquals(true, iterator.hasNext());
    }
}
