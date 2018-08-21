package my.projects.rockpaperscissors.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularIteratorBuilder {

    public static <T> Iterator<T> buildCircularIterator(Collection<T> collection) {
        return new CircularIterator<T>(collection);
    }

    private static class CircularIterator<T> implements Iterator<T> {

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
    }

}
