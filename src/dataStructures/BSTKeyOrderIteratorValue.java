package dataStructures;
import dataStructures.BinarySearchTree.BSTNode;
import network.Exceptions.NoSuchElementException;

public class BSTKeyOrderIteratorValue<K,V> implements Iterator<V> {
    private Stack<BSTNode<K,V>> stack;
    private BSTNode<K,V> root;
    public BSTKeyOrderIteratorValue(BinarySearchTree.BSTNode<K, V> root) {
        stack = new StackInList<>();
        this.root = root;
        searchForNext(this.root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public V next() throws NoSuchElementException {

        if(stack.isEmpty()) throw new NoSuchElementException();

        BSTNode<K,V> node = stack.pop();
        V result = node.getValue();
        if (node.getRight() != null) searchForNext(node.getRight());


        return result;
    }

    @Override
    public void rewind() {
        stack = new StackInList<>();
        searchForNext(root);
    }

    private void searchForNext(BSTNode<K,V> node){
        BSTNode<K,V> aux = node;
        while (aux != null){
            stack.push(aux);
            aux = aux.getLeft();
        }
    }
}
