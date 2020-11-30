package dataStructures;
import dataStructures.BinarySearchTree.BSTNode;

public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K, V>> {

    private Stack<BSTNode<K,V>> stack;
    private BSTNode<K,V> root;
    public BSTKeyOrderIterator(BinarySearchTree.BSTNode<K, V> root) {
        stack = new StackInList<>();
        this.root = root;
        searchForNext(this.root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {

        if(stack.isEmpty()) throw new NoSuchElementException();

        BSTNode<K,V> node = stack.pop();
        Entry<K, V> result = node.getEntry();
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
