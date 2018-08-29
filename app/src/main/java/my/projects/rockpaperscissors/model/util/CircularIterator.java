package my.projects.rockpaperscissors.model.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularIterator<T> implements Iterator<T> {

    private Collection<T> collection;
    private Iterator<T> iterator;

    public CircularIterator(Collection<T> collection) {
        this.collection = collection;
        iterator = collection.iterator();
    }

    @Override
    public boolean hasNext() {
        return !collection.isEmpty();
    }

    @Override
    public T next() {
        if (collection.isEmpty()) {
            throw new NoSuchElementException();
        }

        if (!iterator.hasNext()) {
            iterator = collection.iterator();
        }
        return iterator.next();
    }

    public static <U> CircularIterator<U> build(Collection<U> collection) {
        return new CircularIterator<U>(collection);
    }
}