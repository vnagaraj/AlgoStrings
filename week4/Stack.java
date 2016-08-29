package week4;

/**
 * Stack data structure using link list used to print out the contents of the suffix tree to avoid recursion
 * @param <Item>
 */
class Stack<Item>{

    private Node first;
    private int size;


    private class Node{
        Node next;
        Item item;

        Node(Item item){
            this.item = item;
        }
    }

    public void push(Item item){
        if (item == null){
            throw new RuntimeException("Item cannot be null");
        }
        Node oldFirst = first;
        first = new Node(item);
        first.next = oldFirst;
        size += 1;
    }

    public Item pop(){
        if (first == null){
            throw new RuntimeException("Cannot pop from empty queue");
        }
        Item item = first.item;
        first = first.next;
        size -= 1;
        return item;
    }

    public boolean isEmpty(){
        return size == 0;
    }
}
