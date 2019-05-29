package ru.job4j.tree;

import java.util.*;

/**
 * @version $Id$
 * @since 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;
    private int modCount = 0;
    private int counter = 1;

    public Tree(E value) {
        this.root = new Node<>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Node<E> node = this.findBy(parent).orElse(null);
        Node<E> copyChild = this.findBy(child).orElse(null);
        if (copyChild == null) {
            if (node == null) {
                node = new Node<>(parent);
                node.add(root);
                root = node;
                this.counter++;
            }
            node.add(new Node<>(child));
            result = true;
            this.modCount++;
            this.counter++;
        }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator(this.root);
    }

    private class TreeIterator implements Iterator<E> {
        private int expectedModCount = modCount;
        private List<Node<E>> list = new ArrayList(counter);
        private int position = 0;

        public TreeIterator(Node<E> node) {
            this.list.add(node);
        }

        @Override
        public boolean hasNext() {
            if (modCount > expectedModCount) {
                throw new ConcurrentModificationException();
            }
            return this.position < counter;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> result = this.list.get(this.position++);
            this.list.addAll(result.leaves());
            return result.getValue();
        }
    }
}
